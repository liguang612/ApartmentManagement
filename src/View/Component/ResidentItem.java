package View.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Resources.Constant.Constant;

public class ResidentItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JCheckBox checkBox = new JCheckBox();
    private JLabel birthday = new JLabel("Ngày sinh"), floor = new JLabel("Tầng"), id = new JLabel("CCCD/CMT"), name = new JLabel("Họ tên"),
                   nationality = new JLabel("Quốc tịch"), phoneNumber = new JLabel("Số điện thoại"), relationship = new JLabel("Mối quan hệ với chủ hộ"),
                   room = new JLabel("Phòng");

    public ResidentItem() {
        setLayout(new GridBagLayout());

        birthday.setHorizontalAlignment(JLabel.CENTER);
        birthday.setFont(Constant.titleFont);
        floor.setHorizontalAlignment(JLabel.CENTER);
        floor.setFont(Constant.titleFont);
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setFont(Constant.titleFont);
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setFont(Constant.titleFont);
        nationality.setHorizontalAlignment(JLabel.CENTER);
        nationality.setFont(Constant.titleFont);
        phoneNumber.setHorizontalAlignment(JLabel.CENTER);
        phoneNumber.setFont(Constant.titleFont);
        relationship.setHorizontalAlignment(JLabel.CENTER);
        relationship.setFont(Constant.titleFont);
        room.setHorizontalAlignment(JLabel.CENTER);
        room.setFont(Constant.titleFont);

        paint();
    }
    public ResidentItem(long id, String name, Date birthday, int phoneNumber, String nationality, int floor, int room, String relationship) {
        setLayout(new GridBagLayout());

        this.birthday.setFont(Constant.contentFont);
        this.birthday.setText(birthday.toString());

        this.floor.setFont(Constant.contentFont);
        this.floor.setText(floor + "");

        this.id.setFont(Constant.contentFont);
        this.id.setText((id < Long.parseLong("100000000000") ? "0" : "") + id);

        this.name.setFont(Constant.contentFont);
        this.name.setHorizontalAlignment(JLabel.LEFT);
        this.name.setText(name);

        this.nationality.setFont(Constant.contentFont);
        this.nationality.setText(nationality);

        this.phoneNumber.setFont(Constant.contentFont);
        this.phoneNumber.setText("0" + phoneNumber);

        this.relationship.setFont(Constant.contentFont);
        this.relationship.setHorizontalAlignment(JLabel.LEFT);
        this.relationship.setText(relationship);

        this.room.setFont(Constant.contentFont);
        this.room.setText("" + room);

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
        birthday.setHorizontalAlignment(JLabel.CENTER);
        birthday.setPreferredSize(new Dimension(100, 20));
        floor.setHorizontalAlignment(JLabel.CENTER);
        floor.setPreferredSize(new Dimension(100, 20));
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setPreferredSize(new Dimension(100, 20));
        name.setPreferredSize(new Dimension(100, 20));
        nationality.setHorizontalAlignment(JLabel.CENTER);
        nationality.setPreferredSize(new Dimension(100, 20));
        phoneNumber.setHorizontalAlignment(JLabel.CENTER);
        phoneNumber.setPreferredSize(new Dimension(100, 20));
        relationship.setPreferredSize(new Dimension(100, 20));
        room.setHorizontalAlignment(JLabel.CENTER);
        room.setPreferredSize(new Dimension(100, 20));

        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 2; gbc.weighty = 1; this.add(id, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 4; gbc.weighty = 1; this.add(name, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 2; gbc.weighty = 1; this.add(birthday, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 2; gbc.weighty = 1; this.add(phoneNumber, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 2; gbc.weighty = 1; this.add(nationality, gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(floor, gbc);
        gbc.gridx = 6; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(room, gbc);
        gbc.gridx = 7; gbc.gridy = 0; gbc.weightx = 4; gbc.weighty = 1; this.add(relationship, gbc);
        gbc.gridx = 8; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(checkBox, gbc);
    }

    public JCheckBox getCheckBox() {return checkBox;}
    public Long getId() {return Long.parseLong(id.getText());}
}
