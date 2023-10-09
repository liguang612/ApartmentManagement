package Resources.Constant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Tool {
    public static ImageIcon resize(ImageIcon img, int height, int width) {
        BufferedImage resizeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resizeImage.getGraphics();
        Image image = img.getImage();

        g.drawImage(image, 0, 0, width, height, null);

        return new ImageIcon(resizeImage);
    }
}
