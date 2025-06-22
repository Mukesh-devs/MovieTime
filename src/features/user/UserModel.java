package features.user;

import db.UserDb;
import dto.User;

public class UserModel {
    private UserView view;
    public UserModel(UserView userView) {
        this.view = userView;
    }

    public void userRegisterModel(String name, String userName, String mobile, String email, String pass) {
        User user = new User();
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
            User loggedInUser = UserDb.getInstance().getUser(userName);
            if (loggedInUser != null) {
                view.loginSuccess(loggedInUser.getUserId());
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
