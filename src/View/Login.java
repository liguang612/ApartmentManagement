package View;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controller.AuthCtrl;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class Login {
    GridBagConstraints gbc;
    GridBagLayout gb;
    JButton button1, button2;
    JFrame loginFrame;
    JLabel label1, label2, label3, label4;
    JPanel panel1, panel2, panel3;
    JTextField username;
    JPasswordField password;

    public Login() {
        try {
            if(!System.getProperty("os.name").startsWith("Linux")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        UIManager.put("Button.font", Constant.buttonFont.deriveFont((float)12.0));
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("PasswordField.font", Constant.contentFont.deriveFont((float)12.0));
        UIManager.put("TextField.font", Constant.contentFont.deriveFont((float)12.0));

        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        loginFrame = new JFrame("Đăng nhập");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(gb);
        loginFrame.setSize(600, 400);

        button1 = new JButton("Hủy");
        button1.setBackground(Color.WHITE);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        button2 = new JButton("Xác nhận");
        button2.setBackground(Color.WHITE);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        label1 = new JLabel(Tool.resize(new ImageIcon(System.getProperty("user.dir") + "/src/Resources/Image/avatar.png"), 125, 138) );

        label2 = new JLabel("Đăng nhập");
        label2.setFont(Constant.titleFont.deriveFont((float)18.0));

        label3 = new JLabel("Tài khoản");
        label4 = new JLabel("Mật khẩu");

        username = new JTextField();
        username.setSize(400, 50);

        password = new JPasswordField();
        password.setSize(400, 50);

        panel1 = new JPanel(new BorderLayout(10, 0));
        panel1.add(label3, BorderLayout.WEST);
        panel1.add(username, BorderLayout.CENTER);

        panel2 = new JPanel(new BorderLayout(10, 0));
        panel2.add(label4, BorderLayout.WEST);
        panel2.add(password, BorderLayout.CENTER);

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
        System.exit(0);
    }

    public void verify() {
        User myUser = AuthCtrl.Login(username.getText(), new String(password.getPassword()));
        
        if (myUser == null) {
            JFrame frame = new JFrame("Thông báo");
            JLabel label = new JLabel("Đăng nhập thất bại");

            label.setFont(label.getFont().deriveFont(Font.BOLD, (float)18.0));

            frame.add(label);
            frame.setLocationRelativeTo(loginFrame);
            frame.setSize(200, 100);
            frame.setVisible(true);
        } else {
            loginFrame.setVisible(false);
            new Home(myUser);
        }
    }

    public JFrame getLoginFrame() {
        return this.loginFrame;
    }
}
