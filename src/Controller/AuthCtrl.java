package Controller;

import Model.User;
import SQL.DBQuery;

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
}
