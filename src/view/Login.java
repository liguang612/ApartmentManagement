package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import model.User;
import sql.Connecting_SQLServer;

public class Login {
    GridBagConstraints gbc;
    GridBagLayout gb;
    JButton button1, button2;
    JFrame loginFrame;
    JLabel label1, label2, label3, label4;
    JPanel panel1, panel2, panel3;
    JTextField textField1, textField2;

    public Login() {
        UIManager.put("Label.font", new Font("SegoeUI", Font.PLAIN, 14));

        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        loginFrame = new JFrame("Đăng nhập");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(gb);
        loginFrame.setSize(600, 400);

        button1 = new JButton("Hủy");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        button2 = new JButton("Xác nhận");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        label1 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\src\\image\\login.png"));

        label2 = new JLabel("Đăng nhập");
        label2.setFont(new Font("SegoeUI", Font.BOLD, 20));

        label3 = new JLabel("Tài khoản");
        label4 = new JLabel("Mật khẩu");

        textField1 = new JTextField();
        textField1.setSize(400, 50);

        textField2 = new JTextField();
        textField2.setSize(400, 50);

        panel1 = new JPanel(new BorderLayout(10, 0));
        panel1.add(label3, BorderLayout.WEST);
        panel1.add(textField1, BorderLayout.CENTER);

        panel2 = new JPanel(new BorderLayout(10, 0));
        panel2.add(label4, BorderLayout.WEST);
        panel2.add(textField2, BorderLayout.CENTER);

        panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel3.add(button1);
        panel3.add(button2);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 150; loginFrame.add(label1, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 50; loginFrame.add(label2, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 1; gbc.weighty = 50; loginFrame.add(panel1, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 1; gbc.weighty = 50; loginFrame.add(panel2, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 1; gbc.weighty = 100; loginFrame.add(panel3, gbc);

        loginFrame.setVisible(true);
    }

    public void cancel() {
        getLoginFrame().setVisible(false);
    }

    public void verify() {
        User myUser = (new Connecting_SQLServer()).getUser(textField1.getText(), textField2.getText());
        String message;
        if (myUser == null) {message = "Đăng nhập thất bại";} else message = "Đăng nhập thành công";

        JFrame frame = new JFrame("Thông báo");
        frame.add(new JLabel(message));
        frame.setSize(100, 100);
        frame.setVisible(true);
    }

    public JFrame getLoginFrame() {
        return this.loginFrame;
    }
}
