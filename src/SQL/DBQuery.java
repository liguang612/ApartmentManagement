package SQL;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Model.Apartment;
import Model.Fee;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class DBQuery {

    // User api

    public static Boolean ChangeAvatar(int userId, InputStream inputStream) {
        if(DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.
                        prepareStatement("UPDATE users SET avatar=? WHERE userId=?");
                preparedStatement.setBlob(1, inputStream);
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static User findUser(String username, String password) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    byte[] bytes = resultSet.getBytes(8);
                    ImageIcon avatar = null;
                    if(bytes == null) {
                        avatar = Tool.resize(new ImageIcon(Constant.image + "/avatar.png"), 512, 512);
                    } else {
                        avatar = Tool.BytesToImage(bytes);
                    }

                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            Tool.MillisToDate(resultSet.getLong(6)),
                            avatar,
                            resultSet.getString(7));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Apartment api

    public static ArrayList<Apartment> getApartmentList(int aBId) {
        ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement(
                                "SELECT apartments.apartmentId, apartments.floor, apartments.room, apartments.area, apartments.aBId, members.name, members.phone FROM apartments LEFT JOIN members ON members.role = 'leader' AND apartments.apartmentId = members.apartmentId WHERE aBId = ?");
                preparedStatement.setInt(1, aBId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    apartmentList.add(new Apartment(
                            resultSet.getInt("apartmentId"),
                            resultSet.getInt("floor"),
                            resultSet.getInt("room"),
                            resultSet.getFloat("area"),
                            resultSet.getString("name"),
                            resultSet.getString("phone")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return apartmentList;
    }

    // Fee api

    public static ArrayList<Fee> getFeeList(int abId) {

        ArrayList<Fee> feeList = new ArrayList<Fee>();

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("SELECT * FROM fees WHERE aBId = ?");
                preparedStatement.setInt(1, abId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    feeList.add(new Fee(
                            resultSet.getInt(1),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getBoolean(5),
                            resultSet.getInt(6),
                            Tool.MillisToDate(resultSet.getLong(7))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return feeList;
    }

    public static Boolean addNewFee(int abId, String name, int cost, int mandatory, int cycle, String expirationDate) {

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement(
                        "INSERT INTO fees (aBId, name, cost, mandatory, cycle, expiration) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, abId);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, cost);
                preparedStatement.setInt(4, mandatory);
                preparedStatement.setInt(5, cycle);
                if (cycle == 0) {
                    preparedStatement.setLong(6, Tool.DateToMillis(expirationDate));
                } else {
                    preparedStatement.setLong(6, 0);
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return true;
    }
}
