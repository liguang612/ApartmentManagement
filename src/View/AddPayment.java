package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.FeeCtrl;
import Model.Fee;
import Model.Payment;
import Model.User;
import Resources.Constant.Constant;

public class AddPayment {
    ArrayList<Fee> feeList;
    ButtonGroup feeType;
    JButton cancelButton, verifyButton;
    JCheckBox lateCheckBox;
    JComboBox<Fee> feeField;
    JComboBox<String> nationalityField;
    JFrame addFeeFrame, prevFrame;
    JLabel moneyLabel, notifyLabel, MoneyLabel;
    JPanel contentPanel = new JPanel(), feeTypePanel = new JPanel(new GridLayout(1, 3)), functionPanel = new JPanel(), moneyPanel = new JPanel(new GridLayout(1, 5, 15, 0));
    JSpinner floorField, monthField, roomField, quantityField, yearField;
    JTextField birthdayField, payeeField;
    JRadioButton oneTimeButton, monthlyButton, annualButton;
    User user;

    public AddPayment(JFrame prev, User user) {
        this.prevFrame = prev;
        this.user = user;

        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("ComboBox.font", Constant.contentFont.deriveFont((float)12.0));
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("RadioButton.font", Constant.contentFont);
        UIManager.put("Spinner.font", Constant.contentFont.deriveFont((float)12.0));
        UIManager.put("TextField.font", Constant.contentFont);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Nộp phí", JLabel.CENTER);
        JPanel frPanel = new JPanel(new GridLayout(1, 4)), latePanel = new JPanel(new GridLayout(1, 5, 15, 0));

        addFeeFrame = new JFrame("Nộp phí");
        addFeeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
                prevFrame.toFront();
            }
        });
        addFeeFrame.setBackground(Color.WHITE);
        addFeeFrame.setLayout(new BorderLayout());
        addFeeFrame.setLocation(prevFrame.getX() + prevFrame.getWidth() / 2 - 400, prevFrame.getY() + prevFrame.getHeight() / 2 - 200);
        addFeeFrame.setSize(800, 500);

        cancelButton = new JButton("Hủy");
        cancelButton.setFont(Constant.buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        feeField = new JComboBox<>();
        feeField.setEditable(true);
        feeField.setPreferredSize(new Dimension(760 / 7, 25));
        feeField.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                moneyLabel.setText("" + ((Fee)ie.getItem()).getCost());
                MoneyLabel.setText("" + ((Fee)ie.getItem()).getCost() * (int)quantityField.getValue());

                notifyLabel.setText("");

                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        feeType = new ButtonGroup();
        feeType.add(oneTimeButton = new JRadioButton("Một lần"));
        feeType.add(monthlyButton =  new JRadioButton("Hàng tháng"));
        feeType.add(annualButton =  new JRadioButton("Hàng năm"));

        oneTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                feeList = FeeCtrl.getFeeList(0);
                feeField.removeAllItems();
                for (Fee f : feeList) {feeField.addItem(f);}

                monthField.setEnabled(false);
                yearField.setEnabled(false);
            }
        });
        monthlyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                feeList = FeeCtrl.getFeeList(1);
                feeField.removeAllItems();
                for (Fee f : feeList) {feeField.addItem(f);}
            }
        });
        monthlyButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (lateCheckBox.isSelected()) {
                    monthField.setEnabled(true);
                    yearField.setEnabled(true);
                }
            }
        });
        annualButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                feeList = FeeCtrl.getFeeList(2);
                feeField.removeAllItems();
                for (Fee f : feeList) {feeField.addItem(f);}
            }
        });
        annualButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (lateCheckBox.isSelected()) {
                    monthField.setEnabled(true);
                    yearField.setEnabled(true);
                }
            }
        });

        feeTypePanel.add(oneTimeButton);
        feeTypePanel.add(monthlyButton);
        feeTypePanel.add(annualButton);

        floorField = new JSpinner(new SpinnerNumberModel(6, 6, 29, 1));
        roomField = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        frPanel.add(floorField);
        frPanel.add(new JLabel());
        frPanel.add(new JLabel("Phòng     "));
        frPanel.add(roomField);

        lateCheckBox = new JCheckBox();
        lateCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (monthlyButton.isSelected() || annualButton.isSelected()) {
                        monthField.setEnabled(true);
                        yearField.setEnabled(true);
                    }
                } else {
                    monthField.setEnabled(false);
                    yearField.setEnabled(false);
                }
            }
        });

        moneyLabel = new JLabel("0");
        MoneyLabel = new JLabel("0");

        monthField = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        yearField = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), LocalDate.now().getYear() - 20, LocalDate.now().getYear(), 1));

        latePanel.add(lateCheckBox);
        latePanel.add(new JLabel("Tháng", JLabel.RIGHT));
        latePanel.add(monthField);
        latePanel.add(new JLabel("Năm", JLabel.RIGHT));
        latePanel.add(yearField);
        monthField.setEnabled(false);
        yearField.setEnabled(false);

        nationalityField = new JComboBox<String>(Constant.country);
        nationalityField.setBackground(Color.WHITE);

        notifyLabel = new JLabel();
        notifyLabel.setFont(Constant.notifyFont);
        notifyLabel.setForeground(Color.RED);

        quantityField = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantityField.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (feeField.getSelectedItem() == null) notifyLabel.setText("Vui lòng chọn loại phí trước!");
                else MoneyLabel.setText("" + ((Fee)feeField.getSelectedItem()).getCost() * (int)quantityField.getValue());
            }
        });

        moneyPanel.add(moneyLabel);
        moneyPanel.add(new JLabel("Số lượng"));
        moneyPanel.add(quantityField);
        moneyPanel.add(new JLabel("Phải nộp"));
        moneyPanel.add(MoneyLabel);

        payeeField = new JTextField();

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
        gbc.gridy = 0; contentPanel.add(new JLabel("Tầng", JLabel.RIGHT), gbc);
        gbc.gridy = 1; contentPanel.add(new JLabel("Người nộp", JLabel.RIGHT), gbc);
        gbc.gridy = 2; contentPanel.add(new JLabel("Số tiền/đơn vị", JLabel.RIGHT), gbc);
        gbc.gridy = 3; contentPanel.add(new JLabel("Quốc tịch", JLabel.RIGHT), gbc);
        gbc.gridy = 4; contentPanel.add(new JLabel("Loại phí", JLabel.RIGHT), gbc);
        gbc.gridy = 6; contentPanel.add(new JLabel("Nộp muộn", JLabel.RIGHT), gbc);

        gbc.gridx = 1; gbc.weightx = 5; gbc.weighty = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; contentPanel.add(frPanel, gbc);
        gbc.gridy = 1; contentPanel.add(payeeField, gbc);
        gbc.gridy = 2; contentPanel.add(moneyPanel, gbc);
        gbc.anchor = GridBagConstraints.LINE_START; gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 3; contentPanel.add(nationalityField, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 4; contentPanel.add(feeField, gbc);
        gbc.gridy = 5; contentPanel.add(feeTypePanel, gbc);
        gbc.gridy = 6; contentPanel.add(latePanel, gbc);
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

        addFeeFrame.add(label, BorderLayout.NORTH);
        addFeeFrame.add(contentPanel, BorderLayout.CENTER);
        addFeeFrame.add(functionPanel, BorderLayout.SOUTH);

        addFeeFrame.setVisible(true);
    }

    private void cancel() {
        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }

    private void verify() {
        if (payeeField.getText().isEmpty()) {
            notifyLabel.setText("Người nộp không thể bỏ trống!");
            return ;
        }
        if (feeField.getSelectedItem() == null) {
            notifyLabel.setText("Chưa chọn loại phí!");
            return ;
        }

        try {
            LocalDate now = LocalDate.now();
            Payment payment = new Payment((int)floorField.getValue(), (int)roomField.getValue(), (int)((Fee)feeField.getSelectedItem()).getId(), payeeField.getText(), (int)quantityField.getValue(), new Date(System.currentTimeMillis()));
            if (oneTimeButton.isSelected()) {
                payment.setMonth(0);
                payment.setYear(0);
            } else {
                payment.setTimeValidate(Date.valueOf(now));
                payment.setMonth(monthField.isEnabled() ? (int)monthField.getValue() : now.getMonthValue());
                payment.setYear(yearField.isEnabled() ? (int)yearField.getValue() : now.getYear());
            }
            
            FeeCtrl.addPayment(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addFeeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}