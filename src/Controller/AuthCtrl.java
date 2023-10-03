package Controller;

import Model.User;
import SQL.DBQuery;
import View.Home;
import View.Login;

public class AuthCtrl {
    public static User Login(int id) {
        return DBQuery.findUserByID(id);
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
}
