import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connecting_SQLServer {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "database=ST;" + "user=sa;" + "password=Alfie_2110;" + "encrypt=true;" + "trustServerCertificate=true;" + "loginTimeout=30;";
        ResultSet resultSet = null;

        try (
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
        ) {
            String selectSQL = "SELECT TOP 10 id, name, address FROM student";

            resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}  