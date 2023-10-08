package View.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Model.User;
import Resources.Constant.Constant;


public class AccountDisplay extends JPanel {
    private JLabel avatarLabel;
    private JPanel panel = new JPanel(new GridLayout(5, 3));
    
    public AccountDisplay(User user) {
        UIManager.put("Label.font", Constant.contentFont);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        avatar(user.getImg(), user.getName());
        detail(user);
    }

    private void avatar(String img, String name) {
        avatarLabel = new JLabel(new ImageIcon(Constant.image + "/avatar.jpg"), JLabel.CENTER);

        this.add(avatarLabel, BorderLayout.WEST);
    }

    private void detail(User user) {
        JLabel label = new JLabel("Chung cư BlueMoon");
        JPanel subPanel = new JPanel(new BorderLayout());

        label.setFont(new Font("Verdana", Font.BOLD, 26));
        label.setForeground(Color.BLUE);

        panel.setBackground(Color.WHITE);        
        panel.add(new JLabel());                                     panel.add(label);        panel.add(new JLabel());
        panel.add(new JLabel());                                     panel.add(new JLabel()); panel.add(new JLabel());
        panel.add(new JLabel("Tên: ", JLabel.RIGHT));           panel.add(new JLabel()); panel.add(new JLabel(user.getName()));
        panel.add(new JLabel("Ngày sinh: ", JLabel.RIGHT));     panel.add(new JLabel()); panel.add(new JLabel(user.getBirthday()));
        panel.add(new JLabel("Số điện thoại: ", JLabel.RIGHT)); panel.add(new JLabel()); panel.add(new JLabel('0' + String.valueOf(user.getPhoneNumber())));

        subPanel.setBackground(Color.WHITE);
        subPanel.add(panel, BorderLayout.NORTH);
        this.add(subPanel, BorderLayout.CENTER);
    }
}
