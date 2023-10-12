package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Model.User;
import Resources.Constant.Constant;

public class AddResident {
    JButton cancelButton, verifyButton;
    JComboBox<Integer> floorField, roomField;
    JComboBox<String> countryField;
    JFrame addFeeFrame, prevFrame;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField birthdayField, nameField, phoneField, relationshipField;
    User user;

    public AddResident(JFrame prev, User user) {
        this.prevFrame = prev;
        this.user = user;

        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("ComboBox.font", Constant.contentFont);
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("TextField.font", Constant.contentFont);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Chào mừng cư dân mới tới BlueMoon", JLabel.CENTER);
        JPanel frPanel = new JPanel(new GridLayout(1, 3));

        addFeeFrame = new JFrame("Thêm cư dân mới");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setLocation((int)prevFrame.getLocation().getX() + (int)prevFrame.getSize().getWidth() / 2 - 400, (int)prevFrame.getLocation().getY() + (int)prevFrame.getSize().getHeight() / 2 - 200);
        addFeeFrame.setSize(800, 400);

        birthdayField = new JTextField();

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });
        verifyButton = new JButton("Thêm");
        verifyButton.setFont(Constant.buttonFont);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        phoneField = new JTextField();
        phoneField.setFont(Constant.digitFont);

        countryField = new JComboBox<String>(Constant.country);
        countryField.setBackground(Color.WHITE);

        floorField = new JComboBox<Integer>(Constant.floor);
        roomField = new JComboBox<Integer>(Constant.room);
        frPanel.add(floorField);
        frPanel.add(new JLabel("     Phòng     "));
        frPanel.add(roomField);
        
        nameField = new JTextField();

        relationshipField = new JTextField();

        contentPanel.setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(0, 5, 0, 15);
        gbc.gridx = 0; gbc.weightx = 2; gbc.weighty = 1;
        gbc.gridy = 0; contentPanel.add(new JLabel("Họ và tên", JLabel.RIGHT), gbc);
        gbc.gridy = 1; contentPanel.add(new JLabel("Số điện thoại", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Ngày sinh", JLabel.RIGHT), gbc);
        gbc.gridy = 3; contentPanel.add(new JLabel("Quốc tịch", JLabel.RIGHT), gbc);
        gbc.gridy = 4; contentPanel.add(new JLabel("Tầng", JLabel.RIGHT), gbc);
        gbc.gridy = 5; contentPanel.add(new JLabel("Mối quan hệ với chủ hộ", JLabel.RIGHT), gbc);
        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(nameField, gbc);
        gbc.gridy = 1; contentPanel.add(phoneField, gbc);
        gbc.gridy = 2; contentPanel.add(birthdayField, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 3; contentPanel.add(countryField, gbc);
        gbc.gridy = 4; contentPanel.add(frPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 5; contentPanel.add(relationshipField, gbc);

        functionPanel.setLayout(new GridLayout(2, 5));
        functionPanel.add(new JLabel());
        functionPanel.add(cancelButton);
        functionPanel.add(new JLabel());
        functionPanel.add(verifyButton);
        functionPanel.add(new JLabel());
        functionPanel.add(new JLabel());
        functionPanel.add(new JLabel());
        functionPanel.add(new JLabel());
        functionPanel.add(new JLabel());

        label.setFont(Constant.titleFont.deriveFont((float)18.0));

        addFeeFrame.add(label, BorderLayout.NORTH);
        addFeeFrame.add(contentPanel, BorderLayout.CENTER);
        addFeeFrame.add(functionPanel, BorderLayout.SOUTH);

        addFeeFrame.setVisible(true);
    }

    private void cancel() {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
    }

    private void verify() {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
    }
}