package SQLServer;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Model.Apartment;
import Model.Fee;
import Model.Resident;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class DBQuery {
    public static boolean addNewApartment(int apartmentId, long ownerId) {
        System.out.println(apartmentId + ", " + ownerId);
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("UPDATE Apartment SET ownerId = ? WHERE apartmentId = ?");

                preparedStatement.setLong(1, ownerId);
                preparedStatement.setInt(2, apartmentId);

                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean addResident(Resident resident) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("INSERT INTO Resident VALUES(?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setLong(1, resident.getId());
                preparedStatement.setString(2, resident.getName());
                preparedStatement.setDate(3, resident.getBirthday());
                preparedStatement.setInt(4, resident.getPhoneNumber());
                preparedStatement.setString(5, resident.getNationality());
                preparedStatement.setInt(6, resident.getFloor() * 100 + resident.getRoom());
                preparedStatement.setString(7, resident.getRelationship());

                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean addNewFee(String name, int cost, boolean mandatory, int cycle, String expirationDate) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("INSERT INTO Fee([name], cost, mandatory, cycle, expiration) VALUES (?, ?, ?, ?, ?)");

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, cost);
                preparedStatement.setBoolean(3, mandatory);
                preparedStatement.setInt(4, cycle);
                preparedStatement.setString(5, expirationDate);

                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean changeAvatar(int userId, InputStream inputStream) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("UPDATE [User] SET [image] = ? WHERE userId = ?");
                
                preparedStatement.setBlob(1, inputStream);
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    public static boolean changeAvatar(int userId, ImageIcon image) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("UPDATE [User] SET [image] = ? WHERE userId = ?");

                preparedStatement.setBytes(1, Tool.imageToBytes(image));
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean changePassword(int userId, String newPassword) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("UPDATE [Login] SET password = ? WHERE userId = ?");

                preparedStatement.setString(1, newPassword);
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean checkOldPassword(int userId, String oldPassword) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM [Login] WHERE userId = ? AND password = ?");

                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, oldPassword);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean existsPhoneNumber(int phoneNumber) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Resident WHERE phoneNumber = ?");

                preparedStatement.setInt(1, phoneNumber);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean existsResident(long residentId) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Resident WHERE id = ?");

                preparedStatement.setLong(1, residentId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static User findUser(String username, String password) {
        Integer userId = null;

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM [Login] WHERE username = ? AND password = ?");

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    userId = resultSet.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (userId != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM [User] WHERE userId = ?");

                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new User(
                        userId,
                        resultSet.getString(2),
                        resultSet.getDate(3).toString(),
                        (Integer.valueOf(resultSet.getInt(4))).toString(),
                        resultSet.getBytes(5) == null ? Tool.resize(new ImageIcon(Constant.image + "/avatar.png"), 512, 512) : Tool.BytesToImage(resultSet.getBytes(5))
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static ArrayList<Apartment> getApartmentList() {
        ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT Apartment.[apartmentId], area, [name], phoneNumber FROM Apartment INNER JOIN Resident ON ownerId = id ORDER BY apartmentId ASC");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    apartmentList.add(new Apartment(resultSet.getInt(1), resultSet.getInt(1) / 100, resultSet.getInt(1) % 100, resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return apartmentList;
    }

    public static ArrayList<Fee> getFeeList(int cycle) {
        ArrayList<Fee> feeList = new ArrayList<Fee>();

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Fee WHERE cycle = ?");
                preparedStatement.setInt(1, cycle);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    feeList.add(new Fee(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return feeList;
    }

    public static ArrayList<Resident> getResidentList() {
        ArrayList<Resident> residentList = new ArrayList<Resident>();

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Resident");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    residentList.add(new Resident(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6) / 100,
                        resultSet.getInt(6) % 100,
                        resultSet.getString(7)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return residentList;
    }
    public static ArrayList<Resident> getResidentList(int floor, int room) {
        ArrayList<Resident> residentList = new ArrayList<Resident>();

        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM Resident WHERE apartmentId = ?");

                preparedStatement.setInt(1, floor * 100 + room);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    residentList.add(new Resident(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6) / 100,
                        resultSet.getInt(6) % 100,
                        resultSet.getString(7)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return residentList;
    }
}
