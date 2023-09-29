package Controller;

import Model.User;
import SQL.DBQuery;

public class AuthCtrl {
    
    public static User Login(String username, String password) {
        return DBQuery.findUser(username, password);
    }
}
