package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class Connecting_SQLServer {
    public User getUser(String username, String password) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "database=ApartmentManagement;" + "user=sa;" + "password=Alfie_2110;" + "encrypt=true;" + "trustServerCertificate=true;" + "loginTimeout=30;";
        ResultSet resultSet = null;

        try (
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
        ) {
            String selectSQL = "SELECT userId FROM [Login] WHERE username = '" + username + "' AND " + " [password] = '" + password + "'";
            resultSet = statement.executeQuery(selectSQL);

            if (resultSet.next()) selectSQL = "SELECT userId, [name], birthday, phoneNumber FROM Account WHERE userId = '" + resultSet.getInt(1) + "'"; 
            else return null;
            resultSet = statement.executeQuery(selectSQL);

            if (resultSet.next()) return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
}  