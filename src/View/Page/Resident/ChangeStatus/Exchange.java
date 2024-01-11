package View.Page.Resident.ChangeStatus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Controller.ResidentCtrl;
import Model.Resident;
import Resources.Constant.Constant;

public class Exchange {
    private DefaultTableModel fromModel, toModel;
    private JButton cancelButton, verifyButton;
    private JFrame exchangeFrame, prevFrame;
    private JSpinner fromFloorField, fromRoomField, toFloorField, toRoomField;
    private JTable fromTable, toTable;
    private String[] header = {"Họ tên", "Ngày sinh", "CCCD/CMT", "Số điện thoại"};
    private String[][] fromData, toData;

    public Exchange(JFrame prev) {
        this.prevFrame = prev;

        JLabel label;
        JPanel centerPanel, fromFrPanel = new JPanel(new GridLayout(1, 4)), fromPanel, functionPanel, movePanel,
               toFrPanel = new JPanel(new GridLayout(1, 4)), toPanel;

        exchangeFrame = new JFrame();

        ((JPanel)exchangeFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        exchangeFrame.setLayout(new BorderLayout(15, 15));
        exchangeFrame.setLocation(prevFrame.getX() + prevFrame.getWidth() / 2 - 600, prevFrame.getY() + prevFrame.getHeight() / 2 - 400);
        exchangeFrame.setSize(new Dimension(1200, 800));

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        centerPanel = new JPanel(new GridBagLayout());

        fromModel = new DefaultTableModel(fromData, header) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        fromFloorField = new JSpinner(new SpinnerNumberModel(6, 6, 29, 1));
        fromFloorField.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                updateFrom();
            }
        });

        fromRoomField = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        fromRoomField.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                updateFrom();
            }
        });

        fromFrPanel.add(new JLabel("Tầng", JLabel.CENTER));
        fromFrPanel.add(fromFloorField);
        fromFrPanel.add(new JLabel("Phòng", JLabel.CENTER));
        fromFrPanel.add(fromRoomField);

        fromPanel = new JPanel(new BorderLayout(0, 15));

        fromTable = new JTable(fromModel);
        fromTable.setRowHeight(30);
        fromTable.setShowVerticalLines(false);

        fromPanel.add(fromFrPanel, BorderLayout.NORTH);
        fromPanel.add(new JScrollPane(fromTable), BorderLayout.CENTER);

        functionPanel = new JPanel(new GridLayout(2, 5));
        
        label = new JLabel("Chuyển căn hộ");
        label.setFont(Constant.getTitleFont2(2));

        movePanel = new JPanel();
        movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));

        toFloorField = new JSpinner(new SpinnerNumberModel(6, 6, 29, 1));
        toFloorField.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                updateTo();
            }
        });

        toModel = new DefaultTableModel(toData, header) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        toRoomField = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        toRoomField.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                updateTo();
            }
        });

        toFrPanel.add(new JLabel("Tầng", JLabel.CENTER));
        toFrPanel.add(toFloorField);
        toFrPanel.add(new JLabel("Phòng", JLabel.CENTER));
        toFrPanel.add(toRoomField);

        toPanel = new JPanel(new BorderLayout(0, 15));

        toTable = new JTable(toModel);
        toTable.setRowHeight(30);
        toTable.setShowVerticalLines(false);

        toPanel.add(toFrPanel, BorderLayout.NORTH);
        toPanel.add(new JScrollPane(toTable), BorderLayout.CENTER);

        centerPanel.add(fromPanel);
        centerPanel.add(toPanel);

        verifyButton = new JButton("Xác nhận");
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                verify();
            }
        });

        exchangeFrame.add(label, BorderLayout.NORTH);
        exchangeFrame.add(centerPanel, BorderLayout.CENTER);

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

        updateFrom();
        updateTo();

        exchangeFrame.setVisible(true);
    }

    private void cancel() {
        exchangeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }

    private void updateFrom() {
        ArrayList<Resident> residents = ResidentCtrl.getResidentList((int)fromFloorField.getValue(), (int)fromRoomField.getValue());
        ArrayList<String[]> filteredData = new ArrayList<>();

        for (Resident resident : residents) {
            filteredData.add(resident.toData());
        }

        fromModel.setDataVector(filteredData.toArray(new String[0][0]), header);
    }

    private void updateTo() {
        ArrayList<Resident> residents = ResidentCtrl.getResidentList((int)toFloorField.getValue(), (int)toRoomField.getValue());
        ArrayList<String[]> filteredData = new ArrayList<>();

        for (Resident resident : residents) {
            filteredData.add(resident.toData());
        }

        toModel.setDataVector(filteredData.toArray(new String[0][0]), header);
    }

    private void verify() {
        exchangeFrame.setVisible(false);
        prevFrame.setEnabled(true);
        prevFrame.toFront();
    }
}
