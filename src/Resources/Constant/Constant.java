package Resources.Constant;

import java.awt.Font;

public class Constant {
    public static final String image = System.getProperty("user.dir") + "/src/Resources/Image/";
    
    public static final String customFont = System.getProperty("user.dir") + "/src/Resources/Font";
    public static final Font buttonFont = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font digitFont = new Font("Consolas", Font.PLAIN, 14);
    public static final Font titleFont = new Font("Segoe UI", Font.BOLD, 14);

    public static String verticalImageTitle(String img, String title) {
        return "<html><center><img src='file:" + Constant.image + img + "'/><br/>" + title + "</center></html>";
    }
}