package features.user;

import features.employee.EmployeeView;
import features.ticketbooking.TicketBookingView;
import util.Util;
//import MovieTimeBookingSystem;
import dto.User;
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
        String pass = getPassword();
        model.userRegisterModel(name,userName,mobile,email,pass);
    }

    public String getName() {
        Util.prompt("Enter your Name : ");
        return Util.readLine();
    }
    public String getUserName() {
        Util.prompt("Enter your UserName : ");
        return Util.readLine();
    }
    public String getMobileNo() {
        Util.prompt("Enter your Mobile No : ");
        return Util.readLine();
    }

    public String getEmail() {
        Util.prompt("Enter the Email : ");
        return Util.readLine();
    }

    public String getPassword() {
        Util.prompt("Enter the Password : ");
        return Util.readLine();
    }

    public void loginSuccess(int userId) {
        Util.message("Login Successful..!");
        Util.loggedInUserId = userId;
        userMenu();
    }

    public void registrationSuccess() {
        Util.printSuccess("Registration Successful..!");
        Util.message("Login to continue");
        loginView();
    }

    void userMenu() {
        Util.prompt("User Menu\n" +
                "1. View Movies\n" +
                "2. Book Ticket\n" +
                "3. Logout\n");
        int choice = Util.choice();
        switch (choice) {
            case 1 -> {
                // call movieService.listMovies();
            }
            case 2 -> {
                // call bookTicket();
                new TicketBookingView().init();
            }
            case 3 -> {
                Util.loggedInUserId = -1;
                init();
            }
            default -> {
                Util.printError("Invalid choice");
                userMenu();
            }
        }
    }


    public void init() {
        userSignUpMenu();
//        askRegisterLogin();
    }

    void userSignUpMenu() {
        Util.prompt("1. Register\n" +
                "2. Login\n" +
                "3. Back Menu\n" +
                "4. Exit\n" );
        int choice = Util.choice();
        switch (choice) {
            case 1 -> {
                registerView();
            }
            case 2 -> {
                loginView();
            }
            case 3 -> {
//                new MovieTimeBookingSystem().init();
            }
            case 4 -> {
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
