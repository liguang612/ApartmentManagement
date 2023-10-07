package View.Component;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;


public class AccountDisplay extends JPanel {
    private JLabel avatarLabel;
    private JPanel panel = new JPanel(new GridLayout(2,4));
    private Tool tool = new Tool();
    
    public AccountDisplay(User user) {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        setLayout(new BorderLayout());

        avatar(user.getImg(), user.getName());
        detail(user);
    }

    private void avatar(String img, String name) {
        avatarLabel = new JLabel(new ImageIcon(Constant.image + "/avatar.jpg"), JLabel.CENTER);
        avatarLabel.setFont(avatarLabel.getFont().deriveFont((float)18.0));

        this.add(avatarLabel, BorderLayout.WEST);
    }

    private void detail(User user) {
        JPanel subPanel = new JPanel(new BorderLayout());
        panel.add(new JLabel()); panel.add(new JLabel()); panel.add(new JLabel("Ngày sinh")); panel.add(new JLabel()); panel.add(new JLabel(user.getBirthday()));
        panel.add(new JLabel()); panel.add(new JLabel()); panel.add(new JLabel("Số điện thoại")); panel.add(new JLabel()); panel.add(new JLabel('0' + String.valueOf(user.getPhoneNumber())));

        subPanel.add(panel, BorderLayout.NORTH);
        this.add(subPanel, BorderLayout.CENTER);
    }
}
