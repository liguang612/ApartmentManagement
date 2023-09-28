package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection database = null;

    private static String host = "jdbc:sqlserver://localhost:1433";
    private static String databaseName = "ApartmentManagement";
    private static String username = "sa";
    private static String password = "P@ssword1";

    public static Connection connect() {
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
        return database;
    }
}
