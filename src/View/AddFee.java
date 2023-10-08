package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Resources.Constant.Constant;

public class AddFee {
    String[] cycleType = {"Một lần", "Hàng tháng", "Hàng năm"};

    JButton cancelButton = new JButton("Hủy"), verifyButton = new JButton("Thêm");
    JCheckBox mandatoryField = new JCheckBox();
    JComboBox<String> cycleField;
    JFrame addFeeFrame, prevFrame;
    JPanel contentPanel = new JPanel(), functionPanel = new JPanel();
    JTextField costField = new JTextField(), expirationField = new JTextField(), nameField = new JTextField();

    public AddFee(JFrame prev) {
        this.prevFrame = prev;

        UIManager.put("Label.font", Constant.contentFont);

        JLabel label = new JLabel("Thêm loại phí mới", JLabel.CENTER);

        addFeeFrame = new JFrame("Thêm loại phí mới");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setSize(800, 400);

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });
        verifyButton.setBackground(Color.WHITE);
        verifyButton.setFont(Constant.buttonFont);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        cycleField = new JComboBox<>(cycleType);

        contentPanel.setLayout(new GridLayout(5, 2, 15, 20));
        contentPanel.add(new JLabel("Tên khoản phí", JLabel.RIGHT)); contentPanel.add(nameField);
        contentPanel.add(new JLabel("Số tiền", JLabel.RIGHT)); contentPanel.add(costField);
        contentPanel.add(new JLabel("Bắt buộc", JLabel.RIGHT)); contentPanel.add(mandatoryField);
        contentPanel.add(new JLabel("Chu kỳ đóng phí", JLabel.RIGHT)); contentPanel.add(cycleField);

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
    }

    private void verify() {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
    }
}
