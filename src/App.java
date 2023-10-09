import Model.User;
import SQL.DBConnection;
import View.Home;
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
        // new Home(new User(0, "Phạm Hoàng Thành", "123456789", "25-06-2003"));
    }
}
