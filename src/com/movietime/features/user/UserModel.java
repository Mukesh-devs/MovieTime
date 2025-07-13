package com.movietime.features.user;

import com.movietime.dao.UserDao;
import com.movietime.dto.User;

public class UserModel {
    private UserView view;
    private UserDao userDao;
    public UserModel(UserView userView) {
        this.view = userView;
        this.userDao = new UserDao();
    }

    public void userRegisterModel(String name, String userName, String mobile, String email, String pass) {
        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setEmail(email);
        user.setMobileNo(mobile);
        user.setPassword(pass);

        if (userDao.isAlreadyUser(user.getUserName())) {
            view.registerFailure();
        }
        else {
            userDao.save(user);
            view.registrationSuccess();
        }
    }

    public void userLoginModel(String userName, String pass) {
        if ( userDao.userLoginValidation(userName,pass) ) {
            User loggedInUser = userDao.getUser(userName);
            if (loggedInUser != null) {
                view.loginSuccess(loggedInUser.getUserId(), loggedInUser);
            }
            else {
                view.loginFailure();
            }
        }
        else {
            view.loginFailure();
        }
    }
}
