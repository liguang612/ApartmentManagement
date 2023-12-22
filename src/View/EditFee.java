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
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.FeeCtrl;
import Model.Fee;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;
import View.Component.Display.FeeDisplay;

public class EditFee {
    JButton cancelButton, verifyButton;
    JCheckBox mandatoryField = new JCheckBox();
    JComboBox<Integer> dayField, monthField, yearField;
    JComboBox<String> cycleField;
    JFrame addFeeFrame, prevFrame;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField costField, nameField;
    User user;

    public EditFee(JFrame prev, User user, Integer currentId) {
        this.prevFrame = prev;
        this.user = user;

        Fee current = FeeCtrl.getFee(currentId);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Thêm loại phí mới", JLabel.CENTER);
        JPanel datePanel = new JPanel(new GridLayout(1, 6));

        addFeeFrame = new JFrame("Thêm loại phí mới");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
                prevFrame.toFront();
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setLocation(prevFrame.getX() + prevFrame.getWidth() / 2 - 400, prevFrame.getY() + prevFrame.getHeight() / 2 - 200);
        addFeeFrame.setSize(800, 400);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        costField = new JTextField();
        costField.setFont(Constant.digitFont);
        costField.setText(current.getCost() + "");

        cycleField = new JComboBox<>(Constant.cycleType);
        cycleField.setBackground(Color.WHITE);
        cycleField.setFont(Constant.contentFont);
        cycleField.setSelectedIndex(current.getCycle());

        dayField = new JComboBox<Integer>(Constant.day);
        
        dayField.setSelectedItem(Date.valueOf(current.getExpirationDate()).toLocalDate().getDayOfMonth());
        monthField = new JComboBox<Integer>(Constant.month);
        monthField.setSelectedItem(Date.valueOf(current.getExpirationDate()).toLocalDate().getMonthValue());
        yearField = new JComboBox<Integer>(Constant.year);
        yearField.setSelectedItem(Date.valueOf(current.getExpirationDate()).toLocalDate().getYear());
        
        cycleField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (cycleField.getSelectedIndex() != 0) {
                    dayField.setEnabled(false);
                    monthField.setEnabled(false);
                    yearField.setEnabled(false);
                } else {
                    dayField.setEnabled(true);
                    monthField.setEnabled(true);
                    yearField.setEnabled(true);
                }
            }
        });
        yearField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        dayField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        monthField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        
        datePanel.add(new JLabel("Ngày  ", JLabel.RIGHT));
        datePanel.add(dayField);
        datePanel.add(new JLabel("Tháng  ", JLabel.RIGHT));
        datePanel.add(monthField);
        datePanel.add(new JLabel("Năm  ", JLabel.RIGHT));
        datePanel.add(yearField);

        mandatoryField.setSelected(current.getMandatory());

        nameField = new JTextField();
        nameField.setText(current.getName());

        verifyButton = new JButton("Thêm");
        verifyButton.setFont(Constant.buttonFont);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify(currentId);
            }
        });

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
        gbc.gridy = 4; contentPanel.add(datePanel, gbc);
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
        prevFrame.toFront();
    }

    private void checkDay() {
        int days = Tool.checkDay(monthField.getSelectedIndex() + 1, (Integer)yearField.getSelectedItem());

        if (dayField.getSelectedIndex() > days - 1) {
            dayField.setSelectedIndex(days - 1);
        }
    }

    private void verify(Integer currentId) {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();

        String feeName = nameField.getText();
        int feeCost = Integer.parseInt(costField.getText());
        boolean feeMandatory = mandatoryField.isSelected() ? true : false;
        int feeCycle = java.util.Arrays.asList(Constant.cycleType).indexOf(cycleField.getSelectedItem().toString());
        String expirationDate = yearField.getSelectedItem() + "-" + monthField.getSelectedItem() + "-" + dayField.getSelectedItem();

        FeeCtrl.editFee(new Fee(currentId, feeName, feeCost, feeMandatory, feeCycle, expirationDate.toString()));

        ((Home)prevFrame).getFeeTabbedPane().setComponentAt(0, new FeeDisplay(user, 0));
        ((Home)prevFrame).getFeeTabbedPane().setComponentAt(1, new FeeDisplay(user, 1));
        ((Home)prevFrame).getFeeTabbedPane().setComponentAt(2, new FeeDisplay(user, 2));
    }
}