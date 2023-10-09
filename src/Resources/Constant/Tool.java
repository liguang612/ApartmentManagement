package Resources.Constant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

public class Tool {
    public static ImageIcon resize(ImageIcon img, int height, int width) {
        BufferedImage resizeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resizeImage.getGraphics();
        Image image = img.getImage();

        g.drawImage(image, 0, 0, width, height, null);

        return new ImageIcon(resizeImage);
    }

    public static String MillisToDate(long millis) {
        DateFormat simple = new SimpleDateFormat("dd-MM-yyy");
        Date result = new Date(millis);
        return simple.format(result);
    }

    public static long DateToMillis(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        long millis = 0L;
        try {
            millis = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;
    }

    public static ImageIcon BytesToImage(byte[] bytes) {
        ImageIcon imageIcon = new ImageIcon(bytes);
        Image format = imageIcon.getImage();
        return new ImageIcon(format.getScaledInstance(512, 512, Image.SCALE_SMOOTH));
    }
}
