package com.movietime.dao;

import com.movietime.db.DatabaseConnection;
import com.movietime.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    private final Connection connection;

    public EmployeeDao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void save(Employee employee) {
        String query = "insert into employees (empId, name, userName, password, email) values(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,employee.getEmpId());
            ps.setString(2,employee.getName());
            ps.setString(3, employee.getUserName());
            ps.setString(4, employee.getPassword());
            ps.setString(5, employee.getEmail());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userAlreadyExist(String userName) {
        String query = "select count(*) from employees where userName = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,userName);
            try(ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validate(String userName, String password) {
        Employee employee = getEmployee(userName);
        return employee != null && employee.getPassword().equals(password);

    }

    public Employee getEmployee(String userName) {
        String query = "Select * from employees where username = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,userName);
            try(ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmpId(rs.getInt("empId"));
                    employee.setName(rs.getString("name"));
                    employee.setUserName(rs.getString("username"));
                    employee.setPassword(rs.getString("password"));
                    employee.setEmail(rs.getString("email"));
                    return employee;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
