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
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement(
                        "SELECT * FROM Account WHERE userId = (SELECT userId FROM [Login] WHERE username = ? AND [password] = ?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getInt(4));
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

                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Fee WHERE (cycle <> 0) OR (cycle=0 AND expiration > ?)");
                preparedStatement.setLong(1, currentTime);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    feeList.add(new Fee(
                        resultSet.getInt(1),
                        resultSet.getString(2), 
                        resultSet.getInt(3), 
                        resultSet.getBoolean(4),
                        resultSet.getLong(5),
                        resultSet.getInt(6)
                    ));
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        for(Fee fee: feeList) {
            System.out.println(fee.getName());
        }
        return null;
    }
}
