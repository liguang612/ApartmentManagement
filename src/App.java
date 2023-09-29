import Model.Apartment;
import Model.User;
import SQL.DBConnection;
import SQL.DBQuery;
import View.Login;

public class App {
    public static void main(String[] args) {
        DBConnection.connect();
        //new Login();
        DBQuery.getFeeList(new Apartment());
    }
}