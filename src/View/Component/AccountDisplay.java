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
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.AuthCtrl;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;

public class AccountDisplay extends JPanel {
    private ImageIcon updatedImg;
    private JButton cancelButton, verifyButton;
    private JLabel avatarLabel;
    private JPanel panel = new JPanel(new GridLayout(6, 3));
    private User user;
    private String imagePath = null;
    
    public AccountDisplay(User user) {
        this.user = user;

        UIManager.put("Button.font", Constant.buttonFont);
        UIManager.put("Label.font", Constant.contentFont);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        avatarLabel = new JLabel(user.getImg(), JLabel.CENTER);
        this.add(avatarLabel, BorderLayout.WEST);

        detail();
    }

    private void detail() {
        JLabel label = new JLabel(user.getAbName());
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
            updatedImg = Tool.resize(new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()), 616, 616);
            avatarLabel.setIcon(updatedImg);
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public void turnEditModeOff(boolean accepted) {
        avatarLabel.removeMouseListener(avatarLabel.getMouseListeners()[0]);
        avatarLabel.removeAll();
        if (!accepted) {
            avatarLabel.setIcon(Tool.resize(user.getImg(), 512, 512));
            imagePath = null;
            updatedImg = null;
        }
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
    public void verifyEdit() {
        if(updatedImg != null) {
            user.setImg(updatedImg);
        }
        turnEditModeOff(true);

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