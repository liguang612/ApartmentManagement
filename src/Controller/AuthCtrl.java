package Controller;

import model.User;
import sql.DBQuery;

public class AuthCtrl {
    public static User Login(String username, String password) {
        return DBQuery.findUserByID(1);
    }

    public static String Register(String username, String password) {
        return "Register Successful";
    }
}