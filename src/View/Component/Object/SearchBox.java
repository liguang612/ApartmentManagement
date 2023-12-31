package View.Component.Object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Resources.Constant.Constant;

public class SearchBox extends JTextField {
    private ImageIcon icon;
    private String hint;
    private Shape shape;

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

        setSelectionColor(new Color(80, 199, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int height = getHeight(), width = getWidth();

        if (icon != null) {
            g.drawImage(icon.getImage(), getWidth() - icon.getIconWidth(), (getHeight() - icon.getIconHeight()) / 2, this);
        }
        if (hint != null) {
            if (this.getText().isEmpty()) {
                Graphics2D g2d = (Graphics2D)g.create();

                g2d.setColor(Color.GRAY);
                g2d.setFont(Constant.hintFont);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawString(hint, 10, getHeight() / 2 + 7);
                g2d.dispose();
            }
        }

        super.paintComponent(g);
    }
}
