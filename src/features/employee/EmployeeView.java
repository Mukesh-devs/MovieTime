package features.employee;

import db.EmployeeDb;
import dto.Employee;
import util.Util;

import java.util.Scanner;

public class EmployeeView {
    final Employee employee = new Employee();
    private final EmployeeModel model;
    Scanner scanner = new Scanner(System.in);
    public EmployeeView() {
        model = new EmployeeModel(this);
    }

    public void init() {
        while (true) {
            System.out.println("\nEMPLOYEE PORTAL");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int menuChoice = Util.choice();
            switch (menuChoice) {
                case 1 -> employeeRegisterView();
                case 2 -> employeeLoginView();
                case 3 -> System.exit(0);
                default -> Util.printError("Enter a valid choice.");
            }
        }
    }


    String getName() {
        Util.prompt("Enter your Name : ");
        return Util.readLine();
    }
    String getUserName() {
        Util.prompt("Enter your UserName : ");
        return Util.readLine();
    }
    String getPassword() {
        Util.prompt("Enter the Password : ");
        return Util.readLine();
    }
    String getEmail() {
        Util.prompt("Enter the Email : ");
        return Util.readLine();
    }
    void employeeRegisterView() {
        String name = getName();
        String userName = getUserName();
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
        model.employeeRegisterModel(name,userName,email,pass);
    }

    private String getRePassword() {
        Util.prompt("Re-enter Password: ");
        return Util.readLine();
    }


    void employeeLoginView() {
        String username = getUserName();
        String password = getPassword();
        model.employeeLoginModel(username, password);
    }

    public void loginSuccess() {
        Util.printSuccess("Employee login Successfull");
        showMenu();
    }

    private void showMenu() {
        while (true) {
            Util.prompt("\n--- Employee Menu ---\n" +
                    "1. Add Movie\n" +
                    "2. Remove Movie\n" +
                    "3. View All Movies\n" +
                    "4. Logout");
            int choice = Util.choice();
            switch (choice) {
//                case 1 -> addMovieView();
//                case 2 -> removeMovieView();
//                case 3 -> viewAllMovies();
                case 4 -> {
                    Util.message("Logging out...");
                    return;
                }
                default -> Util.printError("Invalid choice. Please select again.");
            }
        }
    }


    public void loginFailure() {
        Util.printError("Login Credintials does not match");
        employeeLoginView();
    }

    public void registrationFailure() {
        Util.printError("Employee Already Exists..");
        employeeRegisterView();
    }

    public void registrationSuccess() {
        Util.printSuccess("Employee Registration Successfull");
        employeeLoginView();
    }
}
