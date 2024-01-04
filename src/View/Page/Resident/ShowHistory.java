package View.Page.Resident;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.ResidentCtrl;
import Model.Activity;
import Model.Resident;
import Resources.Constant.Constant;

public class ShowHistory extends JFrame {
    ArrayList<Activity> activities;
    DefaultTableModel model;
    JTable table;
    Resident resident;
    String[] header = {"Mã", "Trạng thái", "Ngày vào", "Ngày ra", "Lý do"};
    String[][] data;

    public ShowHistory(JFrame prevFrame, Long residentId) {
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setLayout(new BorderLayout(15, 15));
        setLocation(prevFrame.getWidth() / 2 - 600, prevFrame.getHeight() / 2 - 400);
        setSize(new Dimension(1200, 800));
        setTitle("Lịch sử thường trú, tạm trú, tạm vắng");

        resident = ResidentCtrl.getResident(residentId);
        activities = ResidentCtrl.getHistory(resident.getId());

        int temp = activities.size();
        data = new String[temp][];
        for (int i = 0; i < temp; i++) {
            data[i] = activities.get(i).toData();
        }

        JLabel label1 = new JLabel("Thông tin cá nhân: ");
        JPanel panel = new JPanel(new BorderLayout(15, 15)), panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT)),
               panel2 = new JPanel(new FlowLayout()), panel3 = new JPanel(new GridLayout(1, 1));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.revalidate();
                prevFrame.repaint();
                prevFrame.setEnabled(true);
            }
        });

        label1.setFont(Constant.contentFont.deriveFont(Font.BOLD));

        model = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(25);

        filter("");

        panel1.add(label1);
        panel1.add(new JLabel(resident.getName()));

        panel.add(panel1, BorderLayout.WEST);
        panel.add(panel2, BorderLayout.SOUTH);

        panel3.add(new JScrollPane(table));

        add(panel, BorderLayout.NORTH);
        add(panel3, BorderLayout.CENTER);

        setVisible(true);
    }

    public void filter(String keyword) {
        ArrayList<String[]> filteredData = new ArrayList<>();

        for (Activity activity : activities) {
            if (true) {
                filteredData.add(activity.toData());
            }
        }

        model.setDataVector(filteredData.toArray(new String[0][0]), header);

        this.revalidate();
        this.repaint();
    }
}