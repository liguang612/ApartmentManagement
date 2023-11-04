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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ResidentCtrl;
import Model.Resident;
import Model.User;
import Resources.Constant.Constant;
import Resources.Constant.Tool;
import View.Component.ResidentDisplay;

public class AddResident {
    JButton cancelButton, verifyButton;
    JComboBox<Integer> dayField, floorField, monthField, roomField, yearField;
    JComboBox<String> countryField;
    JFrame addResidentFrame, prevFrame;
    JLabel notifyLabel;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField idField, nameField, phoneField, relationshipField;
    User user;

    public AddResident(JFrame prev, User user) {
        this.prevFrame = prev;
        this.user = user;

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Chào mừng cư dân mới tới BlueMoon", JLabel.CENTER);
        JPanel datePanel = new JPanel(new GridLayout(1, 6)), frPanel = new JPanel(new GridLayout(1, 3));

        addResidentFrame = new JFrame("Thêm cư dân mới");
        addResidentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
                prevFrame.toFront();
            }
        });
        addResidentFrame.setBackground(Color.WHITE);
        addResidentFrame.setLayout(new BorderLayout());
        addResidentFrame.setLocation(prevFrame.getX() + prevFrame.getWidth() / 2 - 400, prevFrame.getY() + prevFrame.getHeight() / 2 - 200);
        addResidentFrame.setSize(800, 400);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        countryField = new JComboBox<String>(Constant.country);
        countryField.setBackground(Color.WHITE);

        dayField = new JComboBox<Integer>(Constant.day);
        dayField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        monthField = new JComboBox<Integer>(Constant.month);
        monthField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        yearField = new JComboBox<Integer>(Constant.year);
        yearField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                checkDay();
            }
        });
        yearField.setSelectedIndex(100);
        datePanel.add(new JLabel("Ngày  ", JLabel.RIGHT));
        datePanel.add(dayField);
        datePanel.add(new JLabel("Tháng  ", JLabel.RIGHT));
        datePanel.add(monthField);
        datePanel.add(new JLabel("Năm  ", JLabel.RIGHT));
        datePanel.add(yearField);

        floorField = new JComboBox<Integer>(Constant.floor);
        roomField = new JComboBox<Integer>(Constant.room);
        frPanel.add(floorField);
        frPanel.add(new JLabel("     Phòng     "));
        frPanel.add(roomField);

        idField = new JTextField();
        idField.setFont(Constant.digitFont);
        
        nameField = new JTextField();

        notifyLabel = new JLabel("", JLabel.LEFT);
        notifyLabel.setFont(Constant.notifyFont);
        notifyLabel.setForeground(Color.RED);

        phoneField = new JTextField();
        phoneField.setFont(Constant.digitFont);

        relationshipField = new JTextField();

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
        gbc.gridy = 0; contentPanel.add(new JLabel("Cắn cước công dân / Chứng minh thư"));
        gbc.gridy = 1; contentPanel.add(new JLabel("Họ và tên", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Số điện thoại", JLabel.RIGHT), gbc);
        gbc.gridy = 3; contentPanel.add(new JLabel("Ngày sinh", JLabel.RIGHT), gbc);
        gbc.gridy = 4; contentPanel.add(new JLabel("Quốc tịch", JLabel.RIGHT), gbc);
        gbc.gridy = 5; contentPanel.add(new JLabel("Tầng", JLabel.RIGHT), gbc);
        gbc.gridy = 6; contentPanel.add(new JLabel("Mối quan hệ với chủ hộ", JLabel.RIGHT), gbc);
        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(idField, gbc);
        gbc.gridy = 1; contentPanel.add(nameField, gbc);
        gbc.gridy = 2; contentPanel.add(phoneField, gbc);
        gbc.gridy = 3; contentPanel.add(datePanel, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 4; contentPanel.add(countryField, gbc);
        gbc.gridy = 5; contentPanel.add(frPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 6; contentPanel.add(relationshipField, gbc);
        gbc.gridy = 7; contentPanel.add(notifyLabel, gbc);

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

        addResidentFrame.add(label, BorderLayout.NORTH);
        addResidentFrame.add(contentPanel, BorderLayout.CENTER);
        addResidentFrame.add(functionPanel, BorderLayout.SOUTH);

        addResidentFrame.setVisible(true);
    }

    private void cancel() {
        addResidentFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }

    private void checkDay() {
        int days = Tool.checkDay(monthField.getSelectedIndex() + 1, (Integer)yearField.getSelectedItem());

        if (dayField.getSelectedIndex() > days - 1) {
            dayField.setSelectedIndex(days - 1);
        }
    }

    private void verify() {
        if (idField.getText().isEmpty()) {
            notifyLabel.setText("Căn cước công dân / Chứng minh nhân dân không thể bỏ trống!");
            return ;
        }
        if (nameField.getText().isEmpty()) {
            notifyLabel.setText("Tên không được bỏ trống");
            return ;
        }
        if (phoneField.getText().length() != 10) {
            notifyLabel.setText("Số điện thoại phải có 10 chữ số");
            return ;
        }

        try {
            if (ResidentCtrl.existsPhoneNumber(Integer.parseInt(phoneField.getText()))) {
                notifyLabel.setText("Số điện thoại đã tồn tại");
                return;
            }
            if (ResidentCtrl.existsResident(Long.parseLong(idField.getText()))) {
                notifyLabel.setText("Số căn cước công dân này đã tồn tại");
                return;
            }

            ResidentCtrl.addResident(new Resident(
                Long.parseLong(idField.getText()),
                nameField.getText(),
                Date.valueOf((Integer)yearField.getSelectedItem() + "-" + (Integer)monthField.getSelectedItem() + "-" + (Integer)dayField.getSelectedItem()),
                Integer.parseInt(phoneField.getText()),
                countryField.getSelectedItem().toString(),
                (Integer)floorField.getSelectedItem(), 
                (Integer)roomField.getSelectedItem(),
                relationshipField.getText()));
        } catch (NumberFormatException e) {
            notifyLabel.setText("Số căn cước công dân / chứng minh nhân dân, số điện thoại phải là các số");
            return;
        }

        ((Home)prevFrame).getResidentTabbedPane().setComponentAt(1, new ResidentDisplay(user));

        addResidentFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}