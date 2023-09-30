package View.Component;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ApartmentItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel area = new JLabel("Diện tích (m2)"), floor = new JLabel("Tầng"), ownerName = new JLabel("Tên chủ sở hữu"), ownerPhone = new JLabel("Số điện thoại"), room = new JLabel("Số phòng");

    public ApartmentItem() {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        setLayout(new GridBagLayout());

        area.setFont(new Font("Segoe UI", Font.BOLD, 14));
        floor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ownerName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ownerPhone.setFont(new Font("Segoe UI", Font.BOLD, 14));
        room.setFont(new Font("Segoe UI", Font.BOLD, 14));

        paint();
    }
    public ApartmentItem(Float area, Integer floor, Integer room, String ownerName, String ownerPhone) {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        setLayout(new GridBagLayout());

        this.area.setText(area.toString());
        this.floor.setText(floor.toString());
        this.ownerName.setText(ownerName);
        this.ownerPhone.setText(ownerPhone);
        this.room.setText(room.toString());

        paint();
    }
    public void paint() {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(floor, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(room, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerName, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerPhone, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(area, gbc);
    }

    public double getArea() {return Double.parseDouble(area.getText());}
}