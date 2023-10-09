package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Controller.FeeCtrl;
import Model.User;
import Resources.Constant.Constant;

public class AddFee {
    String[] cycleType = {"Một lần", "Hàng tháng", "Hàng năm"};

    JButton cancelButton, verifyButton;
    JCheckBox mandatoryField = new JCheckBox();
    JComboBox<String> cycleField;
    JFrame addFeeFrame, prevFrame;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField costField, expirationField, nameField;
    User user;

    public AddFee(JFrame prev, User user) {
        this.prevFrame = prev;
        this.user = user;

        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("TextField.font", Constant.contentFont);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Thêm loại phí mới", JLabel.CENTER);

        addFeeFrame = new JFrame("Thêm loại phí mới");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setLocationRelativeTo(prevFrame);
        addFeeFrame.setSize(800, 400);

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

        costField = new JTextField();
        costField.setFont(Constant.digitFont);

        cycleField = new JComboBox<>(cycleType);
        cycleField.setBackground(Color.WHITE);
        cycleField.setFont(Constant.contentFont);
        cycleField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (cycleField.getSelectedIndex() != 0) {
                    expirationField.setEnabled(false);
                } else {
                    expirationField.setEnabled(true);
                }
            }
        });
        
        expirationField = new JTextField();

        nameField = new JTextField();

        contentPanel.setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(0, 5, 0, 15);
        gbc.gridx = 0; gbc.weightx = 2; gbc.weighty = 1;
        gbc.gridy = 0; contentPanel.add(new JLabel("Tên khoản phí", JLabel.RIGHT), gbc);
        gbc.gridy = 1; contentPanel.add(new JLabel("Số tiền", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Bắt buộc", JLabel.RIGHT), gbc);
        gbc.gridy = 3; contentPanel.add(new JLabel("Chu kỳ đóng phí", JLabel.RIGHT), gbc);
        gbc.gridy = 4; contentPanel.add(new JLabel("Hạn nộp", JLabel.RIGHT), gbc);
        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(nameField, gbc);
        gbc.gridy = 1; contentPanel.add(costField, gbc);
        gbc.gridy = 2; contentPanel.add(mandatoryField, gbc);
        gbc.gridy = 4; contentPanel.add(expirationField, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 3; contentPanel.add(cycleField, gbc);

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

        String feeName = nameField.getText();
        int feeCost = Integer.parseInt(costField.getText());
        int feeMandatory = mandatoryField.isSelected() ? 1 : 0;
        int feeCycle = java.util.Arrays.asList(cycleType).indexOf(cycleField.getSelectedItem().toString());
        String expirationDate = expirationField.getText();

        FeeCtrl.addNewFee(user.getId(), feeName, feeCost, feeMandatory, feeCycle, expirationDate);
    }
}