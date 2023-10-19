package Controller;

import java.io.InputStream;

import Model.User;
import SQL.DBQuery;
import View.Home;
import View.Login;

public class AuthCtrl {

    public static Boolean ChangePassword(int userId, String oldPassword, String newPassword) {
        return DBQuery.ChangePassword(userId, oldPassword, newPassword);
    }
    
    public static User Login(String username, String password) {
        return DBQuery.findUser(username, password);
    }

    public static String Register(String username, String password) {
        return "Register Successful";
    }

    public static void signOut(Home home) {
        home.getFrame().setVisible(false);
        new Login();
    }

    public static Boolean ChangeAvatar(int userId, InputStream inputStream) {
        return DBQuery.ChangeAvatar(userId, inputStream);
    }

    public static Boolean ChangeInformation(User user) {
        return DBQuery.ChangeInformation(user);
    }
}
