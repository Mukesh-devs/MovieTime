package com.movietime.features.user;

import com.movietime.db.UserDb;
import com.movietime.dto.User;
import com.movietime.features.ticketbooking.TicketBookingView;
import com.movietime.util.Util;
//import com.movietime.MovieTimeBookingSystem;
import java.util.Scanner;

public class UserView {

    private final UserModel model;
    Scanner scanner = new Scanner(System.in);
    public UserView() {
        model = new UserModel(this);
    }

    private void registerView() {
        String name = getName();
        String userName = getUserName();
        String mobile = getMobileNo();
        String email = getEmail();
        String pass;
        while (true) {
            pass = getPassword();
            String repass = getRePassword();
            if (pass.equals(repass)) {
                break;
            } else {
                Util.printError("Password and Re-password do not match. Try again.");
            }
        }
        model.userRegisterModel(name,userName,mobile,email,pass);
    }

    public String getName() {
        String name;
        while (true) {
            Util.prompt("Enter your Full Name : ");
            name = Util.readLine().trim();
            if ( name.matches("[a-zA-Z ]{3,}")) {
                return name;
            }
            else {
                Util.printError("Name must be minimum of 3 letters contains only Alphabets");
            }
        }
    }
    public String getUserName() {
        String username;
        while (true) {
            Util.prompt("Enter your UserName : ");
            username = Util.readLine().trim();
            if ( username.matches("[a-zA-Z0-9 ]{3,}")) {
                return username;
            }
            else {
                Util.printError("UserName must be minimum of 3 letters contains only alphaNumeric.");
            }
        }
    }
    public String getMobileNo() {
        String mobileno;
        while (true) {
            Util.prompt("Enter your Mobile No : ");
            mobileno = Util.readLine().trim();
            if ( mobileno.matches("[6-9][0-9]{9}")) {
                return mobileno;
            }
            else {
                Util.printError("Mobile Number must be length of 10." );
            }
        }
    }

    public String getEmail() {
        String email;
        while (true) {
            Util.prompt("Enter the Email : ");
            email = Util.readLine().trim();
            if ( email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }
            else {
                Util.printError("Email is not valid. Please try again");
            }
        }
    }

    public String getPassword() {
        String pass;
        while (true) {
            Util.prompt("Enter the Password : ");
            pass = Util.readLine().trim();
            if ( pass.matches("[a-zA-Z0-9@#!$%^&*()_+={};:'<,>.?/]{5,}")) {
                return pass;
            }
            else {
                Util.printError("Password must be minimum of 5 letters. Please try again");
            }
        }
    }

    public String getRePassword() {
        String repass;
        while (true) {
            Util.prompt("Enter the RePassword : ");
            repass = Util.readLine().trim();
            if ( repass.matches("[a-zA-Z0-9@#!$%^&*()_+={};:'<,>.?/]{5,}")) {
                return repass;
            }
            else {
                Util.printError("RePassword must be minimum of 5 letters. Please try again");
            }
        }
    }

    public void loginSuccess(int userId, User user) {
        Util.message("Login Successful..!");
        Util.loggedInUserId = userId;
        Util.loggedInUser = user;
        userMenu();
    }

    public void registrationSuccess() {
        Util.printSuccess("Registration Successful..!");
        Util.message("Login to continue");
        userSignUpMenu();
//        loginView();
    }

    public void userMenu() {
        Util.prompt("\n---- User Menu ----" +
                "\n1. Your Profile" +
                "\n2. Ticket Booking" +
                "\n3. Change Password" +
                "\n4. Logout");
        int choice = Util.choice();
        switch (choice) {
            case 1 -> {
                yourProfileView();
            }
            case 2 -> {
                // call bookTicket();
                new TicketBookingView().init();
            }
            case 3 -> {
                changePasswordView();
            }
            case 4 -> {
                Util.loggedInUserId = -1;
                init();
            }
            default -> {
                Util.printError("Invalid choice");
                userMenu();
            }
        }
    }

    private void yourProfileView() {
        User user = Util.loggedInUser;
        Util.message("\n---- My Profile ----" +
                "\nUser ID : " + user.getUserId() +
                "\nFull Name : " + user.getName() +
                "\nUserName : " + user.getUserName() +
                "\nEmail : " + user.getEmail() +
                "\nMobile Number : " + user.getMobileNo()
        );
        userMenu();
    }

    private void changePasswordView() {
        while (true) {
            Util.prompt("Enter Your Old Password : ");
            String oldPass = Util.readLine();
            if (UserDb.getInstance().checkOldPassword(Util.loggedInUserId, oldPass)) {
                break;
            }
            else {
                Util.printError("Please check your old Password and try Again..");
            }
        }
        Util.prompt("Enter the New Password : ");
        String newPass;
        String reNewPass;
        while (true) {
            Util.message("Enter the New Password to Continue...");
            newPass = getPassword();
            reNewPass = getRePassword();
            if ( newPass.equals(reNewPass)) {
                break;
            }
            else {
                Util.printError("Password and Re-password do not match. Try again.");
            }
        }
        if( UserDb.getInstance().changePassword(Util.loggedInUserId,newPass) ) {
            Util.printSuccess("Password changed Successfully..");
            userMenu();
        }
        else {
            Util.printError("Password change failed. please try again..");
            userMenu();
        }

    }

    public void init() {
        userSignUpMenu();
//        askRegisterLogin();
    }

    void userSignUpMenu() {
        Util.prompt("\n1. Register" +
                "\n2. Login" +
                "\n3. Change User" +
                "\n4. Exit" );
        int choice = Util.choice();
        switch (choice) {
            case 1 -> {
                registerView();
            }
            case 2 -> {
                loginView();
            }
            case 3 -> {
                new com.movietime.MovieTimeBookingSystem().init();
            }
            case 4 -> {
                Util.message("Thankyou");
                System.exit(0);
            }
            default -> {
                Util.printError("Enter the Correct Choice..");
                userSignUpMenu();
            }
        }
    }

    private void loginView() {
        String userName = getUserName();
        String pass = getPassword();
        model.userLoginModel(userName, pass);
    }

    public void registerFailure() {
        Util.printError("UserName Already Exists");
        registerView();
    }

    public void loginFailure() {
        Util.printError("Login Credintials not match");
        loginView();
    }
}
