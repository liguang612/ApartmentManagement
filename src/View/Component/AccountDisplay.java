package View.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class AccountDisplay extends JPanel {
    private JButton cancelButton, verifyButton;
    private JLabel avatarLabel;
    private JPanel panel = new JPanel(new GridLayout(6, 3));
    private Tool tool = new Tool();
    private User user;
    
    public AccountDisplay(User user) {
        this.user = user;

        UIManager.put("Button.font", Constant.buttonFont);
        UIManager.put("Label.font", Constant.contentFont);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        avatarLabel = new JLabel(tool.resize(new ImageIcon(Constant.image + "/avatar.jpg"), 616, 616), JLabel.CENTER);
        this.add(avatarLabel, BorderLayout.WEST);

        detail(user);
    }

    private void detail(User user) {
        JLabel label = new JLabel("Chung cư BlueMoon");
        JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)), subPanel = new JPanel(new BorderLayout());

        cancelButton = new JButton("Hủy");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setVisible(false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                turnEditModeOff();
            }
        });

        verifyButton = new JButton("Xác nhận");
        verifyButton.setBackground(Color.WHITE);
        verifyButton.setVisible(false);

        editPanel.setBackground(Color.WHITE);
        editPanel.add(cancelButton);
        editPanel.add(verifyButton);

        label.setFont(new Font("Verdana", Font.BOLD, 26));
        label.setForeground(Color.BLUE);

        panel.setBackground(Color.WHITE);        
        panel.add(new JLabel());                                     panel.add(label);        panel.add(new JLabel());
        panel.add(new JLabel());                                     panel.add(new JLabel()); panel.add(new JLabel());
        panel.add(new JLabel("Tên: ", JLabel.RIGHT));           panel.add(new JLabel()); panel.add(new JLabel(user.getName()));
        panel.add(new JLabel("Ngày sinh: ", JLabel.RIGHT));     panel.add(new JLabel()); panel.add(new JLabel(user.getBirthday()));
        panel.add(new JLabel("Số điện thoại: ", JLabel.RIGHT)); panel.add(new JLabel()); panel.add(new JLabel('0' + String.valueOf(user.getPhoneNumber())));
        panel.add(new JLabel());                                     panel.add(editPanel);    panel.add(new JLabel());

        subPanel.setBackground(Color.WHITE);
        subPanel.add(panel, BorderLayout.NORTH);
        this.add(subPanel, BorderLayout.CENTER);
    }

    public void pickImage() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", ImageIO.getReaderFileSuffixes());
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        fileChooser.setFileFilter(filter);

        if (result == JFileChooser.APPROVE_OPTION) {
            avatarLabel.setIcon(tool.resize(new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()), 616, 616));
        }
    }

    public void turnEditModeOff() {
        avatarLabel.removeMouseListener(avatarLabel.getMouseListeners()[0]);
        avatarLabel.removeAll();
        avatarLabel.setIcon(tool.resize(new ImageIcon(Constant.image + "/avatar.jpg"), 616, 616));
        avatarLabel.revalidate();
        avatarLabel.repaint();
        cancelButton.setVisible(false);
        verifyButton.setVisible(false);
    }
    public void turnEditModeOn() {
        avatarLabel.setLayout(new BorderLayout());
        avatarLabel.add(new JLabel(new ImageIcon(Constant.image + "/pickImage.png")), BorderLayout.CENTER);
        avatarLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                pickImage();
            }
        });

        cancelButton.setVisible(true);
        verifyButton.setVisible(true);
    }
}
