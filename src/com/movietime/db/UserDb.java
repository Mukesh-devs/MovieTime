package com.movietime.db;

import com.movietime.dto.User;

import java.util.HashMap;
import java.util.Map;

public class UserDb {

    private static UserDb instance;

    public static UserDb getInstance() {
        if ( instance != null) {
            return instance;
        }
        instance = new UserDb();
        return instance;
    }

    Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getUserName(), user);
    }

    public boolean isAlreadyUser(String userName) {
        if ( users.containsKey(userName) ){
            return true;
        }
        return false;
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public boolean UserloginValidation(String userName, String pass) {
        User user = getUser(userName);
        if ( user != null && pass.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public User getUserbyId(int userId) {
        for ( User user : users.values()) {
            if ( user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public boolean checkOldPassword(int userId, String oldPass) {
        User user = getUserbyId(userId);
        if ( user.getPassword().equals(oldPass)) {
            return true;
        }
        return false;
    }

    public boolean changePassword(int loggedInUserId, String newPass) {
        User user = getUserbyId(loggedInUserId);
        if ( user == null) {
            return false;
        }
        else {
            user.setPassword(newPass);
            return true;
        }
    }
}
