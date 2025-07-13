package com.movietime.dao;

import com.movietime.db.DatabaseConnection;
import com.movietime.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void save(User user) {
        String query = "insert into users (userId,name, userName, password, email, mobileNo) values (?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,user.getUserId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getUserName());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getEmail());
            ps.setString(6,user.getMobileNo());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isAlreadyUser(String userName) {
        String query = "Select count(*) from users where userName = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,userName);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String userName) {
        String query = "Select * from users where userName = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,userName);
            try(ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setName(rs.getString("name"));
                    user.setUserName(rs.getString("userName"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setMobileNo(rs.getString("mobileNo"));
                    return user;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userLoginValidation(String userName, String pass) {
        User user = getUser(userName);
        return user != null && pass.equals(user.getPassword());
    }

    public User getUserbyId(int userId) {
        String query = "Select * from users where userId = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,userId);
            try(ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setName(rs.getString("name"));
                    user.setUserName(rs.getString("userName"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setMobileNo(rs.getString("mobileNo"));
                    return user;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkOldPassword(int userId, String oldPass) {
        User user = getUserbyId(userId);
        return user != null && user.getPassword().equals(oldPass);
    }

    public boolean changePassword(int loggedInUserId, String newPass) {
        String query = "update users set password = ? where userId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,newPass);
            ps.setInt(2,loggedInUserId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
