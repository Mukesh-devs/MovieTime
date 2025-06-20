package db;

import dto.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDb {
    private Employee employee;
    private static EmployeeDb instance;
    Map<String, Employee> employees = new HashMap<>();
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
    
    public String getUserName() {
        if ( employee == null) {
            return null;
        }
        return employee.getUserName();
    }

    public boolean validatePassword(String pass) {
        if ( employee.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
    public String getpassword() {
        if ( employee == null) {
            return null;
        }
        return employee.getPassword();
    }
    public boolean validate(String userName, String password) {
        Employee emp = employees.get(userName);
        return emp != null && emp.getPassword().equals(password);
    }

    public boolean userAlreadyExist(String userName) {
        return employees.containsKey(userName);
    }

    public void save(Employee employee) {
        employees.put(employee.getUserName(),employee);
    }
}
