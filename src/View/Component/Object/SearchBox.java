package View.Component.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Resources.Constant.Constant;

public class SearchBox extends JTextField {
    private ImageIcon icon;
    private String hint;

    public SearchBox() {
        super();
    }
    public SearchBox(ImageIcon icon) {
        super();
        this.icon = icon;
    }
    public SearchBox(String hint) {
        super();
        this.hint = hint;
    }
    public SearchBox(ImageIcon icon, String hint) {
        super();
        this.icon = icon;
        this.hint = hint;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            g.drawImage(icon.getImage(), getWidth() - icon.getIconWidth(), (getHeight() - icon.getIconHeight()) / 2, this);
        }
        if (hint != null) {
            if (this.getText().isEmpty()) {
                Graphics2D g2d = (Graphics2D)g.create();

                g2d.setColor(Color.GRAY);
                g2d.setFont(Constant.hintFont);
                g2d.drawString(hint, 5, 16);
                g2d.dispose();
            }
        }
    }
}
