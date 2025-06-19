package features.employee;

import db.EmployeeDb;
import dto.Employee;
import util.Util;

public class EmployeeModel {
    private EmployeeView view;
    final Employee employee = new Employee();

    public EmployeeModel(EmployeeView employeeView) {
        this.view = employeeView;
    }


    public void employeeLogin() {
        String username = view.getUserName();
        String password = view.getPassword();
        if ( validCredentials(username,password)) {
            view.loginSuccess();
        }else {
            Util.printError("Enter correct Credentials...");
            employeeLogin();
        }
    }

    void employeeRegister() {

    }
    boolean validCredentials(String username,String password) {
        if ( username.equals(EmployeeDb.getInstance().getUserName()) && password.equals(EmployeeDb.getInstance().getpassword())) {
            return true;
        }
        return false;
    }

    void registration(Employee employee) {
        if ( UserAlreadyExists(employee.getUserName())) {
            Util.printError("Already a User..");
        }
        else {
            EmployeeDb.getInstance().setEmployee(employee);
            view.EmployeeMenu();
        }
    }
    boolean UserAlreadyExists(String username) {

        if ( username.equals(EmployeeDb.getInstance().getUserName()) && EmployeeDb.getInstance().getUserName() != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void employeeRegisterModel(String name, String userName, String pass, String email) {
        employee.setName(name);
        employee.setUserName(userName);
        employee.setPassword(pass);
        employee.setEmail(email);
        registration(employee);
    }

    public void employeeLoginModel(String userName, String pass) {
        if ( EmployeeDb.getInstance().validatePassword(pass) ) {
            view.loginSuccess();
        }
        else {
            view.loginFailure();
        }
    }
}
