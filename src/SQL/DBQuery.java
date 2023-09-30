package SQL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Apartment;
import Model.Fee;
import Model.User;

public class DBQuery {

    // user api

    public static User findUser(String username, String password) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    // return new User(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // fee api

    public static Fee[] getFeeList(Apartment apartment) {

        ArrayList<Fee> feeList = new ArrayList<Fee>();

        if(DBConnection.database != null) {
            try {
                long currentTime = System.currentTimeMillis();
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM fees WHERE (cycle <> 0) OR (cycle=0 AND expiration > ?)");
                preparedStatement.setLong(1, currentTime);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    feeList.add(new Fee(
                        resultSet.getInt(1),
                        resultSet.getString(2), 
                        resultSet.getInt(3), 
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        resultSet.getLong(6)
                    ));
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
