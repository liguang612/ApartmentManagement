package View.Page.Vehicle;

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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Controller.ApartmentCtrl;
import Model.User;
import Model.Vehicle;
import Resources.Constant.Constant;
import View.Home;
import View.Component.Display.ResidentDisplay;
import View.Component.Object.Dialog;

public class EditVehicle {
    JButton cancelButton, verifyButton;
    JComboBox<String> typeField;
    JFrame addVehicleFrame, prevFrame;
    JLabel notifyLabel;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JSpinner floorField, roomField;
    JTextField licensePlateField;
    User user;

    public EditVehicle(JFrame prev, User user, String licensePlate) {
        this.prevFrame = prev;
        this.user = user;

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Thêm phương tiện", JLabel.CENTER);
        JPanel frPanel = new JPanel(new GridLayout(1, 3));
        Vehicle vehicle = ApartmentCtrl.getVehicle(licensePlate);

        addVehicleFrame = new JFrame("Thêm phương tiện mới");
        addVehicleFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
                prevFrame.toFront();
            }
        });
        addVehicleFrame.setBackground(Color.WHITE);
        addVehicleFrame.setLayout(new BorderLayout());
        addVehicleFrame.setLocation(prevFrame.getX() + prevFrame.getWidth() / 2 - 500, prevFrame.getY() + prevFrame.getHeight() / 2 - 200);
        addVehicleFrame.setSize(1000, 400);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        typeField = new JComboBox<String>(Constant.vehicleType);
        typeField.setBackground(Color.WHITE);
        typeField.setSelectedIndex(vehicle.getType());

        floorField = new JSpinner(new SpinnerNumberModel(vehicle.getFloor(), 6, 29, 1));
        roomField = new JSpinner(new SpinnerNumberModel(vehicle.getRoom(), 1, 5, 1));
        frPanel.add(floorField);
        frPanel.add(new JLabel("     Phòng     "));
        frPanel.add(roomField);

        licensePlateField = new JTextField();
        licensePlateField.setFont(Constant.digitFont);
        licensePlateField.setText(vehicle.getLicensePlates());

        notifyLabel = new JLabel("", JLabel.LEFT);
        notifyLabel.setFont(Constant.notifyFont);
        notifyLabel.setForeground(Color.RED);

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
        gbc.gridy = 0; contentPanel.add(new JLabel("Biển số xe", JLabel.RIGHT), gbc);
        gbc.gridy = 1; contentPanel.add(new JLabel("Loại", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Tầng", JLabel.RIGHT), gbc);
        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(licensePlateField, gbc);
        gbc.gridy = 1; contentPanel.add(typeField, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 2; contentPanel.add(frPanel, gbc);

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

        addVehicleFrame.add(label, BorderLayout.NORTH);
        addVehicleFrame.add(contentPanel, BorderLayout.CENTER);
        addVehicleFrame.add(functionPanel, BorderLayout.SOUTH);

        addVehicleFrame.setVisible(true);
    }

    private void cancel() {
        addVehicleFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
    private void verify() {
        if (licensePlateField.getText().isEmpty()) {
            notifyLabel.setText("Căn cước công dân / Chứng minh nhân dân không thể bỏ trống!");
            return ;
        }

        try {
            if (ApartmentCtrl.getVehicle(licensePlateField.getText()) != null) {
                notifyLabel.setText("Biến số xe đã tồn tại");
                return;
            }

            if (!ApartmentCtrl.addVehicle(new Vehicle(licensePlateField.getText(), (Integer)floorField.getValue(), (Integer)roomField.getValue(), typeField.getSelectedIndex()))) {
                new Dialog(addVehicleFrame, user, 0, "Lỗi");
            }
        } catch (NumberFormatException e) {
            notifyLabel.setText("Số căn cước công dân / chứng minh nhân dân, số điện thoại phải là các số");
            return;
        }

        ((Home)prevFrame).setResidentDisplay(new ResidentDisplay(user));

        addVehicleFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}