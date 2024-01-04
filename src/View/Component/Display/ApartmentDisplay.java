package View.Component.Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.ApartmentCtrl;
import Model.Apartment;
import Model.User;
import Resources.Constant.Constant;

public class ApartmentDisplay extends JPanel {
    ArrayList<Apartment> apartmentList;
    JLabel statistics;
    JTable table;
    DefaultTableModel model;
    String[] header = {"Tầng", "Phòng", "Tên chủ sở hữu", "Số điện thoại", "Diện tích (m^2)"};
    String[][] data;

    public ApartmentDisplay(User user) {
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 15));

        apartmentList = ApartmentCtrl.getApartmentList();

        model = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(25);

        statistics = new JLabel("", JLabel.RIGHT);

        filter("");

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statistics, BorderLayout.SOUTH);
    }
    
    public void filter(String keyword) {
        ArrayList<String[]> filteredData = new ArrayList<>();

        for (Apartment apartment : apartmentList) {
            if (apartment.getOwnerName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(apartment.toData());
            }
        }

        model.setDataVector(filteredData.toArray(new String[0][0]), header);
        statistics.setText("Tổng số căn hộ: " + filteredData.size());

        this.revalidate();
        this.repaint();
    }

    public ArrayList<Integer> getSelections() {
        ArrayList<Integer> selections = new ArrayList<>();

        for (int i : table.getSelectedRows()) {
            selections.add(Integer.parseInt(table.getValueAt(i,0).toString()) * 100 + Integer.parseInt(table.getValueAt(i, 1).toString()));
        }

        return selections;
    }
}
