package View.Component.Item;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constant.Constant;

public class ApartmentItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JCheckBox checkBox = new JCheckBox();
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
        this.ownerPhone.setText(ownerPhone);
        this.room.setFont(Constant.contentFont);
        this.room.setText(room.toString());

        paint();

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(0XFFE5FEFF));
            }
            public void mouseExited(MouseEvent me) {
                setBackground(null);
            }
            public void mousePressed(MouseEvent me) {
                checkBox.setSelected(!checkBox.isSelected());
            }
        });
    }
    public void paint() {
        area.setHorizontalAlignment(JLabel.CENTER);
        area.setPreferredSize(new Dimension(100, 20));
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkBox.setBackground(null);
        floor.setHorizontalAlignment(JLabel.CENTER);
        floor.setPreferredSize(new Dimension(100, 20));
        ownerName.setHorizontalAlignment(JLabel.CENTER);
        ownerName.setPreferredSize(new Dimension(100, 20));
        ownerPhone.setHorizontalAlignment(JLabel.CENTER);
        ownerPhone.setPreferredSize(new Dimension(100, 20));
        room.setHorizontalAlignment(JLabel.CENTER);
        room.setPreferredSize(new Dimension(100, 20));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(floor, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(room, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerName, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 10; gbc.weighty = 1; this.add(ownerPhone, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(area, gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(checkBox, gbc);
    }

    public int getApartmentId() {return Integer.parseInt(floor.getText()) * 100 + Integer.parseInt(room.getText());}
    public double getArea() {return Double.parseDouble(area.getText());}
    public JCheckBox getCheckBox() {return checkBox;}
}