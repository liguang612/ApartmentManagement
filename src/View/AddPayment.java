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

public class AddPayment {
    JButton cancelButton, verifyButton;
    JComboBox<Integer> floorField, roomField;
    JComboBox<String> feeField, nationalityField;
    JFrame addFeeFrame, prevFrame;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField birthdayField, moneyField, payeeField;
    User user;

    public AddPayment(JFrame prev, User user) {
        this.prevFrame = prev;
        this.user = user;

        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("ComboBox.font", Constant.contentFont);
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("TextField.font", Constant.contentFont);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Nộp phí", JLabel.CENTER);
        JPanel frPanel = new JPanel(new GridLayout(1, 3));

        addFeeFrame = new JFrame("Nộp phí");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setLocation((int)prevFrame.getLocation().getX() + (int)prevFrame.getSize().getWidth() / 2 - 400, (int)prevFrame.getLocation().getY() + (int)prevFrame.getSize().getHeight() / 2 - 200);
        addFeeFrame.setSize(800, 400);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        feeField = new JComboBox<String>();

        floorField = new JComboBox<Integer>(Constant.floor);
        roomField = new JComboBox<Integer>(Constant.room);
        frPanel.add(floorField);
        frPanel.add(new JLabel("     Phòng     "));
        frPanel.add(roomField);

        moneyField = new JTextField();
        moneyField.setFont(Constant.digitFont);

        nationalityField = new JComboBox<String>(Constant.country);
        nationalityField.setBackground(Color.WHITE);

        payeeField = new JTextField();

        verifyButton = new JButton("Thêm");
        verifyButton.setFont(Constant.buttonFont);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        contentPanel.setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(0, 5, 0, 15);
        gbc.gridx = 0; gbc.weightx = 2; gbc.weighty = 1;
        gbc.gridy = 0; contentPanel.add(new JLabel("Tầng", JLabel.RIGHT), gbc);
        gbc.gridy = 1; contentPanel.add(new JLabel("Người nộp", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Số tiền đã nộp", JLabel.RIGHT), gbc);
        gbc.gridy = 3; contentPanel.add(new JLabel("Quốc tịch", JLabel.RIGHT), gbc);
        gbc.gridy = 4; contentPanel.add(new JLabel("Loại phí", JLabel.RIGHT), gbc);

        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(frPanel, gbc);
        gbc.gridy = 1; contentPanel.add(payeeField, gbc);
        gbc.gridy = 2; contentPanel.add(moneyField, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 3; contentPanel.add(nationalityField, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 4; contentPanel.add(feeField, gbc);

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
        prevFrame.toFront();
    }

    private void verify() {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}