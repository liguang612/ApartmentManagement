package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection database;
    private Boolean status;
    private String host, username, password;
    
    DBConnection(String host, String username, String password) {
        status = false;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    Boolean Connect() {

        return true;
    }
}
