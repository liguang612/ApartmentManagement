package Resources.Constant;

public class Constant {
    public static final String image = System.getProperty("user.dir") + "/src/Resources/Image/";
    public static String verticalImageTitle(String img, String title) {
        return "<html><center><img src='file:" + Constant.image + img + "'/><br/>" + title + "</center></html>";
    }
}