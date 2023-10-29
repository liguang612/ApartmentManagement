import javax.swing.ImageIcon;

import Model.User;
import Resources.Constant.Constant;
import SQLServer.DBConnection; // Use for SQLServer
import View.Home;
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
        // new Home(new User(1, "Phạm Hoàng Thành", "966322513", "15/06/2023", new ImageIcon(Constant.image + "avatar.png"), "BlueMoon"));
    }
}
