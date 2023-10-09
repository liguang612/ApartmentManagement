import javax.swing.ImageIcon;

import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;
import SQL.DBConnection;
import View.Home;
import View.Login;

public class App {
    public static void main(String[] args) {
        // DBConnection.connect();
        // new Login();
        new Home(new User(0, "Phạm Hoàng Thành", "123456789", "15-06-2003", (new Tool()).resize(new ImageIcon(Constant.image + "/avatar.jpg"), 616, 616)));
    }
}
