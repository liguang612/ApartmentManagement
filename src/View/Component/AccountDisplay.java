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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.AuthCtrl;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class AccountDisplay extends JPanel {
    private ImageIcon updatedImg;
    private JButton cancelButton, verifyButton;
    private JLabel avatarLabel, birthdayLabel, nameLabel, phoneLabel;
    private JPanel panel = new JPanel(new GridLayout(6, 3, 0, 15));
    private JTextField birthdayField, nameField, phoneField;
    private User user;
    private String imagePath = null;
    
    public AccountDisplay(User user) {
        this.user = user;

        UIManager.put("Button.font", Constant.buttonFont);
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("TextField.font", Constant.contentFont);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        avatarLabel = new JLabel(Tool.resize(user.getImg(), 616, 616), JLabel.CENTER);
        avatarLabel.setLayout(new BorderLayout());
        
        birthdayLabel = new JLabel(user.getBirthday());
        nameLabel = new JLabel(user.getName());
        phoneLabel = new JLabel(user.getPhoneNumber());

        birthdayField = new JTextField(user.getBirthday());
        nameField = new JTextField(user.getName());
        phoneField = new JTextField(user.getPhoneNumber());

        this.add(avatarLabel, BorderLayout.WEST);

        detail();
    }

    private void detail() {
        JLabel label = new JLabel("Chung cư " + user.getAbName());
        JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)), subPanel = new JPanel(new BorderLayout());

        cancelButton = new JButton("Hủy");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setVisible(false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                turnEditModeOff(false);
            }
        });

        verifyButton = new JButton("Xác nhận");
        verifyButton.setBackground(Color.WHITE);
        verifyButton.setVisible(false);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verifyEdit();
            }
        });

        editPanel.setBackground(Color.WHITE);
        editPanel.add(cancelButton);
        editPanel.add(verifyButton);

        label.setFont(new Font("Verdana", Font.BOLD, 26));
        label.setForeground(Color.BLUE);

        panel.setBackground(Color.WHITE);        
        panel.add(new JLabel());                                     panel.add(label);        panel.add(new JLabel());
        panel.add(new JLabel());                                     panel.add(new JLabel()); panel.add(new JLabel());
        panel.add(new JLabel("Tên: ", JLabel.RIGHT));           panel.add(new JLabel()); panel.add(nameLabel);
        panel.add(new JLabel("Ngày sinh: ", JLabel.RIGHT));     panel.add(new JLabel()); panel.add(birthdayLabel);
        panel.add(new JLabel("Số điện thoại: ", JLabel.RIGHT)); panel.add(new JLabel()); panel.add(phoneLabel);
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
            updatedImg = Tool.resize(new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()), 616, 616);
            avatarLabel.setIcon(updatedImg);
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public void turnEditModeOff(boolean accepted) {
        avatarLabel.removeMouseListener(avatarLabel.getMouseListeners()[0]);
        avatarLabel.removeAll();
        if (!accepted) {
            avatarLabel.setIcon(Tool.resize(user.getImg(), 616, 616));
            imagePath = null;
            updatedImg = null;
        }
        avatarLabel.revalidate();
        avatarLabel.repaint();

        cancelButton.setVisible(false);

        panel.remove(8); panel.add(nameLabel, 8);
        panel.remove(11); panel.add(birthdayLabel, 11);
        panel.remove(14); panel.add(phoneLabel, 14);
        panel.revalidate();
        panel.repaint();

        verifyButton.setVisible(false);
    }
    public void turnEditModeOn() {
        if (avatarLabel.getComponentCount() == 0) avatarLabel.add(new JLabel(new ImageIcon(Constant.image + "/pickImage.png")), BorderLayout.CENTER);

        if (avatarLabel.getMouseListeners().length == 0)
            avatarLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    pickImage();
                }
            });

        cancelButton.setVisible(true);

        panel.remove(8); panel.add(nameField, 8);
        panel.remove(11); panel.add(birthdayField, 11);
        panel.remove(14); panel.add(phoneField, 14);
        panel.revalidate();
        panel.repaint();

        verifyButton.setVisible(true);
    }
    public void verifyEdit() {
        if(updatedImg != null) {
            user.setImg(updatedImg);
        }
        turnEditModeOff(true);

        user.setName(nameField.getText());
        user.setBirthday(birthdayField.getText());
        user.setPhoneNumber(phoneField.getText());

        AuthCtrl.ChangeInformation(user);

        // update image to database

        if(imagePath == null) return;

        try(final InputStream inputStream = Files.newInputStream(Paths.get(imagePath))) {
            AuthCtrl.ChangeAvatar(user.getId(), inputStream);
            imagePath = null;
            updatedImg = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}