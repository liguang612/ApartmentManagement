import SQL.DBConnection;
import View.Login;

public class App {
    public static void main(String[] args) {
        DBConnection.connect(
            "jdbc:mysql://localhost/",
            "ApartmentManagement",
            "meadow",
            "25062003"
        );
        // DBConnection.connect(); // use for My SQL Server
        new Login();
    }
}