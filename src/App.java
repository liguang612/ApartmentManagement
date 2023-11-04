import SQLServer.DBConnection; // Use for SQLServer
import View.Login;

public class App {
    public static void main(String[] args) {
        // DBConnection.connect(
        //     "jdbc:mysql://localhost/",
        //     "ApartmentManagement",
        //     "ragnie",
        //     "Alfie_2110"
        // );
        DBConnection.connect();
        new Login();
    }
}
