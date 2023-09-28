import sql.DBConnection;
import view.Login;

public class App {
    public static void main(String[] args) {
        DBConnection.connect();
        new Login();
    }
}
