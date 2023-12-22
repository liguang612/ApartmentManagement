package View.Component.Item;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constant.Constant;

public class FeeItem extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private Integer feeId;
    private JCheckBox checkBox = new JCheckBox(), mandatoryCheckBox = new JCheckBox();
    private JLabel cost = new JLabel("Chi phí"), expiration = new JLabel("Hạn nộp"), mandatory = new JLabel("  Bắt buộc"), name = new JLabel("Tên");

    public FeeItem() {
        setLayout(new GridBagLayout());

        cost.setHorizontalAlignment(JLabel.CENTER);
        cost.setFont(Constant.titleFont);
        expiration.setHorizontalAlignment(JLabel.CENTER);
        expiration.setFont(Constant.titleFont);
        mandatory.setHorizontalAlignment(JLabel.CENTER);
        mandatory.setFont(Constant.titleFont);
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setFont(Constant.titleFont);

        paint(mandatory);
    }
    public FeeItem(int cost, String expiration, boolean mandatory, String name) {
        setLayout(new GridBagLayout());

        this.cost.setFont(Constant.contentFont);
        this.cost.setHorizontalAlignment(JLabel.CENTER);
        this.cost.setText("" + cost);
        this.expiration.setFont(Constant.contentFont);
        this.expiration.setText(expiration);
        this.name.setFont(Constant.contentFont);
        this.name.setText(name);

        mandatoryCheckBox.setForeground(Color.BLUE);
        mandatoryCheckBox.setEnabled(false);
        mandatoryCheckBox.setSelected(mandatory);

        paint(mandatoryCheckBox);

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

    public void paint(Component mandatoryCom) {
        checkBox.setBackground(null);
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        cost.setHorizontalAlignment(JLabel.CENTER);
        cost.setPreferredSize(new Dimension(100, 20));
        expiration.setHorizontalAlignment(JLabel.CENTER);
        expiration.setPreferredSize(new Dimension(100, 20));
        name.setPreferredSize(new Dimension(100, 20));

        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(name, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(cost, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(mandatoryCom, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(expiration, gbc);
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; this.add(checkBox, gbc);
    }

    public JCheckBox getCheckBox() {return checkBox;}
    public Integer getFeeId() {return feeId;}

    public void setFeeId(Integer feeId) {this.feeId = feeId;}
}
