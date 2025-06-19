package features.user;

import db.UserDb;
import dto.User;

public class UserModel {
    private UserView view;
    User user = new User();
    public UserModel(UserView userView) {
        this.view = userView;
    }

    public void userRegisterModel(String name, String userName, String mobile, String email, String pass) {
        user.setName(name);
        user.setUserName(userName);
        user.setEmail(email);
        user.setName(mobile);
        user.setPassword(pass);

        if (UserDb.getInstance().isAlreadyUser(user.getUserName())) {
            view.registerFailure();
        }
        else {
            UserDb.getInstance().save(user);
            view.registrationSuccess();
        }
    }

    public void userLoginModel(String userName, String pass) {
        if ( UserDb.getInstance().UserloginValidation(userName,pass) ) {
            view.loginSuccess();
        }
        else {
            view.loginFailure();
        }
    }
}
