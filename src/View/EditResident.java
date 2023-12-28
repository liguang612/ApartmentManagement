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
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import Controller.ResidentCtrl;
import Model.Resident;
import Model.User;
import Resources.Constant.Constant;
import View.Component.Display.ResidentDisplay;

public class EditResident {
    JButton cancelButton, verifyButton;
    JComboBox<String> countryField;
    JFrame addResidentFrame, prevFrame;
    JLabel notifyLabel;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JSpinner dateField, floorField, roomField;
    JTextField idField, nameField, phoneField, relationshipField;
    Long oldId;
    User user;

    public EditResident(JFrame prev, User user, Long currentId) {
        this.prevFrame = prev;
        this.user = user;

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Sửa thông tin cư dân", JLabel.CENTER);
        JPanel datePanel = new JPanel(new GridLayout(1, 6)), frPanel = new JPanel(new GridLayout(1, 3));
        Resident current = ResidentCtrl.getResident(currentId);

        addResidentFrame = new JFrame("Sửa thông tin cư dân");
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
        countryField.setSelectedItem(current.getNationality());

        Calendar calendar = Calendar.getInstance();
        java.util.Date initialDate = calendar.getTime();
        java.util.Date starDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 10); java.util.Date endDate = calendar.getTime();
        dateField = new JSpinner(new SpinnerDateModel(initialDate, starDate, endDate, Calendar.YEAR));
        dateField.setEditor(new JSpinner.DateEditor(dateField, "dd/MM/yyyy"));

        floorField = new JSpinner(new SpinnerNumberModel(6, 6, 29, 1));
        roomField = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        frPanel.add(floorField);
        frPanel.add(new JLabel("     Phòng     "));
        frPanel.add(roomField);

        idField = new JTextField();
        idField.setFont(Constant.digitFont);
        idField.setText(current.getId() + "");
        
        nameField = new JTextField();
        nameField.setText(current.getName());

        notifyLabel = new JLabel("", JLabel.LEFT);
        notifyLabel.setFont(Constant.notifyFont);
        notifyLabel.setForeground(Color.RED);

        phoneField = new JTextField();
        phoneField.setFont(Constant.digitFont);
        phoneField.setText(current.getPhoneNumber() + "");

        relationshipField = new JTextField();
        relationshipField.setText(current.getRelationship());

        verifyButton = new JButton("Thêm");
        verifyButton.setFont(Constant.buttonFont);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify(current, currentId);
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

    private void verify(Resident current, Long currentId) {
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
            Integer phoneNumber = Integer.parseInt(phoneField.getText());
            if (current.getPhoneNumber() != phoneNumber && ResidentCtrl.existsPhoneNumber(phoneNumber)) {
                notifyLabel.setText("Số điện thoại đã tồn tại");
                return;
            }
            Long id = Long.parseLong(idField.getText());
            if (current.getId() != id && ResidentCtrl.existsResident(id)) {
                notifyLabel.setText("Số căn cước công dân này đã tồn tại");
                return;
            }

            ResidentCtrl.editResident(new Resident(
                Long.parseLong(idField.getText()),
                nameField.getText(),
                (Date)dateField.getValue(),
                Integer.parseInt(phoneField.getText()),
                countryField.getSelectedItem().toString(),
                (Integer)floorField.getValue(), 
                (Integer)roomField.getValue(),
                relationshipField.getText()), currentId);
        } catch (NumberFormatException e) {
            notifyLabel.setText("Số căn cước công dân / chứng minh nhân dân, số điện thoại phải là các số");
            return;
        }

        ((Home)prevFrame).setResidentDisplay(new ResidentDisplay(user));

        addResidentFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}