import Model.Apartment;
import SQL.DBConnection;
import SQL.DBQuery;

public class App {
    public static void main(String[] args) {
        DBConnection.connect(
            "jdbc:mysql://localhost/",
            "meadow",
            "25062003"
        );
        DBQuery.getFeeList(new Apartment());
    }
}