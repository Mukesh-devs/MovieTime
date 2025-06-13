package db;

import dto.Employee;

public class EmployeeDb {
    Employee employee;
    private static EmployeeDb instance;
    public static EmployeeDb getInstance() {
        if ( instance != null) {
            return instance;
        }
        instance = new EmployeeDb();
        return instance;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;

    }
    public String getFirstName() {
        if ( employee == null) {
            return null;
        }
        return employee.getFirstName();
    }
}
