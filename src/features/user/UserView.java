package features.user;

import features.employee.EmployeeView;
import util.Util;
import dto.User;
import java.util.Scanner;

public class UserView {

    private final UserModel model;
    Scanner scanner = new Scanner(System.in);
    public UserView() {
        model = new UserModel(this);
    }

    private void registerView() {
        User User = new User();
        User.setFirstName(getFirstName());
        User.setLastName(getLastName());
        User.setMobileNo(getMobileNo());
        User.setEmail(getEmail());
        User.setPassword(getPassword());
        User.setRePassword(getRePassword());
        UserModel.register(User);
    }

    public String getFirstName() {
        Util.prompt("Enter your FirstName : ");
        return Util.readLine();
    }

    public String getLastName() {
        Util.prompt("Enter your LastName : ");
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

    private String getRePassword() {
        Util.prompt("Enter the RePassword : ");
        return Util.readLine();
    }

    public void loginSuccess() {
        Util.message("Login Successful..!");
        //TODO: Add User specific menu after login
    }

    public void registrationSuccess() {
        Util.message("Registration Successful..!");
        UserMenu();
    }
    public void init() {
        askRegisterLogin();
    }

    void askRegisterLogin() {
        System.out.println("1. Employee");
        System.out.println("2. User");
        System.out.println("3. Exit");
        int homeChoice = Util.choice();
        switch (homeChoice) {
            case 1 -> {
                new EmployeeView().init();
            }
            case 2 -> {

            }
            case 3 -> {
                System.exit(0);
            }
            default -> {
                Util.printError("Enter the Correct the choice");
                askRegisterLogin();
            }
        }
    }

}
