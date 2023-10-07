import Model.User;
import SQL.DBConnection;
import View.Home;
import View.Login;

public class App {
    public static void main(String[] args) {
        // DBConnection.connect();
        // new Login();
        new Home(new User(0, "Phạm Hoàng Thành", "123456789", "15-06-2003"));
    }
}
