package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection database = null;

    private static String host = "jdbc:sqlserver://localhost:1433";
    private static String databaseName = "ApartmentManagement";
    private static String username = "sa";
    private static String password = "Alfie_2110";

    public static void connect() {
        try {
            String connectionUrl = host + ";"
                    + "database=" + databaseName + ";"
                    + "user=" + username + ";"
                    + "password=" + password + ";"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
            database = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect(String host, String databaseName, String user, String password) {
        try {
            database = DriverManager.getConnection(host + databaseName, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
