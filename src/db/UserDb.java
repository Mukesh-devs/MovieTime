package db;

import dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
}
