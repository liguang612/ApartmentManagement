package View.Component.Object;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Resources.Constant.Constant;

public class TextField extends JTextField {
    private ImageIcon icon;
    private String hint;

    public TextField() {
        super();

        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        setOpaque(false);
    }
    public TextField(ImageIcon icon) {
        super();
        this.icon = icon;

        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        setOpaque(false);
    }
    public TextField(String hint) {
        super();
        this.hint = hint;

        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        setOpaque(false);
    }
    public TextField(ImageIcon icon, String hint) {
        super();
        this.icon = icon;
        this.hint = hint;

        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int height = getHeight(), width = getWidth();

        if (!isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            // GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#2193b0"), height, width, Color.decode("#6dd5ed"));
            g2.setPaint(getBackground());
            g2.fillRoundRect(0, 0, width, height, 24, 24);
            g2.dispose();
        }

        super.paintComponent(g);

        if (icon != null) {
            g.drawImage(icon.getImage(), width - icon.getIconWidth() - 10, (height - icon.getIconHeight()) / 2, this);
        }
        if (hint != null) {
            if (this.getText().isEmpty()) {
                Graphics2D g2d = (Graphics2D)g.create();

                g2d.setColor(Color.GRAY);
                g2d.setFont(Constant.hintFont.deriveFont((float)16));
                g2d.drawString(hint, 10, height / 2 + 7);
                g2d.dispose();
            }
        }
    }
}