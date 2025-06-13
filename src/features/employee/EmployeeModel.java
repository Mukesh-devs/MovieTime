package features.employee;

import db.EmployeeDb;
import dto.Employee;
import util.Util;

public class EmployeeModel {
    private EmployeeView view;

    public EmployeeModel(EmployeeView employeeView) {
        this.view = employeeView;
    }


    public void employeeLogin() {

    }

    void employeeRegister() {

    }

    void registration(Employee employee) {
        if ( UserAlreadyExists(employee.getFirstName())) {
            Util.printError("Already a User..");
        }
        else {
            EmployeeDb.getInstance().setEmployee(employee);
            view.EmployeeMenu();
        }
    }
    boolean UserAlreadyExists(String username) {

        if ( username.equals(EmployeeDb.getInstance().getFirstName()) && EmployeeDb.getInstance().getFirstName() != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
