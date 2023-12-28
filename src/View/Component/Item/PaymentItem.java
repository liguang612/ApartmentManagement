package View.Component.Item;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constant.Constant;

public class PaymentItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private Integer feeId;
    private JCheckBox checkBox = new JCheckBox();
    private JLabel date = new JLabel("Ngày"), fee = new JLabel("Phí"), feeCode = new JLabel("Mã phí"), floor = new JLabel("Phòng"), money = new JLabel("Đã nộp"), owner = new JLabel("Chủ hộ"), payer = new JLabel("Người nộp"), room = new JLabel("Phòng");

    public PaymentItem() {
        setLayout(new GridBagLayout());

        date.setFont(Constant.titleFont);
        fee.setFont(Constant.titleFont);
        feeCode.setFont(Constant.titleFont);
        floor.setFont(Constant.titleFont);
        money.setFont(Constant.titleFont);
        owner.setFont(Constant.titleFont);
        payer.setFont(Constant.titleFont);
        room.setFont(Constant.titleFont);
    }
    public PaymentItem(Date date, String fee, int feeCode, int floor, int money, String owner, String payer, int room) {
        setLayout(new GridBagLayout());

        this.date.setText(date.toString());
        this.fee.setText(fee);
        this.feeCode.setText("" + feeCode);
        this.floor.setText("" + floor);
        this.money.setText("" + money);
        this.owner.setText(owner);
        this.payer.setText(payer);
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
        checkBox.setBackground(null);
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        date.setHorizontalAlignment(JLabel.CENTER);
        fee.setHorizontalAlignment(JLabel.CENTER);
        feeCode.setHorizontalAlignment(JLabel.CENTER);
        floor.setHorizontalAlignment(JLabel.CENTER);
        money.setHorizontalAlignment(JLabel.CENTER);
        owner.setHorizontalAlignment(JLabel.CENTER);
        payer.setHorizontalAlignment(JLabel.CENTER);
        room.setHorizontalAlignment(JLabel.CENTER);

        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(date, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(fee, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(feeCode, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(floor, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(money, gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(owner, gbc);
        gbc.gridx = 6; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(payer, gbc);
        gbc.gridx = 7; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(room, gbc);
    }

    public JCheckBox getCheckBox() {return checkBox;}

    public void setFeeId(Integer feeId) {this.feeId = feeId;}
}
