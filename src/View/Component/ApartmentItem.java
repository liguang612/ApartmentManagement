package View.Component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constant.Constant;

public class ApartmentItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel area = new JLabel("Diện tích (m2)"), floor = new JLabel("Tầng"), ownerName = new JLabel("Tên chủ sở hữu"), ownerPhone = new JLabel("Số điện thoại"), room = new JLabel("Số phòng");

    public ApartmentItem() {
        setLayout(new GridBagLayout());

        area.setFont(Constant.titleFont);
        floor.setFont(Constant.titleFont);
        ownerName.setFont(Constant.titleFont);
        ownerPhone.setFont(Constant.titleFont);
        room.setFont(Constant.titleFont);

        paint();
    }
    public ApartmentItem(Float area, Integer floor, Integer room, String ownerName, String ownerPhone) {
        setLayout(new GridBagLayout());

        this.area.setFont(Constant.contentFont);
        this.area.setText(area.toString());
        this.floor.setFont(Constant.contentFont);
        this.floor.setText(floor.toString());
        this.ownerName.setFont(Constant.contentFont);
        this.ownerName.setText(ownerName);
        this.ownerPhone.setFont(Constant.contentFont);
        this.ownerPhone.setText('0' + ownerPhone);
        this.room.setFont(Constant.contentFont);
        this.room.setText(room.toString());

        paint();
    }
    public void paint() {
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(floor, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(room, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerName, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerPhone, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(area, gbc);
    }

    public double getArea() {return Double.parseDouble(area.getText());}
}