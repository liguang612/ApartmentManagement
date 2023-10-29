package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection database = null;

    public static void connect(String host, String databaseName, String user, String password) {
        try {
            database = DriverManager.getConnection(host + databaseName, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
