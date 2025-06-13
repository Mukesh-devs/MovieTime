package features.user;

import features.employee.EmployeeView;
import util.Util;

import java.util.Scanner;

public class UserView {

    private final UserModel model;
    Scanner scanner = new Scanner(System.in);
    public UserView() {
        model = new UserModel(this);
    }

    public void init() {
        askRegisterLogin();
    }

    void askRegisterLogin() {
        System.out.println("1. Employee");
        System.out.println("2. Customer");
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
                printError("Enter the Correct the choice");
                askRegisterLogin();
            }
        }
    }

    void printError(String error) {
        System.out.println("Error : " + error);
    }
}
