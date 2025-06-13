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
        EmployeeMenu();
    }

    void EmployeeMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        int menuChoice = Util.choice();
        switch (menuChoice) {
            case 1 -> {
                employeeRegisterView();
            }
            case 2 -> {
                model.employeeLogin();
            }
            case 3 -> {
                System.exit(0);
            }
            default -> {
                Util.printError("Enter The Correct the Choice..");
            }
        }
    }

    String getFirstName() {
        Util.prompt("Enter your FirstName : ");
        return Util.readLine();
    }
    String getUserName() {
        Util.prompt("Enter the UserName : ");
        return Util.readLine();
    }
    String getLastName() {
        Util.prompt("Enter your LastName : ");
        return Util.readLine();
    }
    String getPassword() {
        Util.prompt("Enter the Password : ");
        return Util.readLine();
    }
    String getRePassword() {
        Util.prompt("Enter the RePassword : ");
        return Util.readLine();
    }
    String getEmail() {
        Util.prompt("Enter the Email : ");
        return Util.readLine();
    }
    void employeeRegisterView() {
        employee.setFirstName(getFirstName());
        employee.setLastName(getLastName());
        employee.setUserName(getUserName());
        employee.setEmail(getEmail());
        employee.setPassword(getPassword());
        employee.setRePassword(getRePassword());
        model.registration(employee);
    }

    void employeeLoginView() {

    }

    public void loginSuccess() {
        Util.message("Login Successfull..");
        Util.prompt("1. ShowTimes ");
        Util.prompt("2. Add Movies ");
        Util.prompt("3. Exit");
        int choice = Util.readInt();
        switch (choice) {
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {
                System.exit(0);
            }
        }

    }
}
