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
import Model.User;
import Model.Vehicle;
import Resources.Constant.Constant;
public class VehicleDisplay extends JPanel {
    ArrayList<Vehicle> vehicles;
    JLabel statistics;
    JTable table;
    DefaultTableModel model;
    String[] header = {"Tầng", "Phòng", "Biển số xe", "Loại phương tiện"};
    Object[][] data;

    public VehicleDisplay(User user) {
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 15));

        vehicles = ApartmentCtrl.getVehicle();

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
        ArrayList<Object[]> filteredData = new ArrayList<>();
        int car = 0, motorbike = 0;

        for (Vehicle vehicle : vehicles) {
            filteredData.add(vehicle.toData());
            if (vehicle.getType() == 0) {++motorbike;}
            else {++car;}
        }

        model.setDataVector(filteredData.toArray(new Object[0][0]), header);
        statistics.setText("Số xe: " + filteredData.size() + "   (Xe máy: " + motorbike + "   Ô tô: " + car + ")");

        this.revalidate();
        this.repaint();
    }

    public ArrayList<String> getSelections() {
        ArrayList<String> selections = new ArrayList<>();

        for (int i : table.getSelectedRows()) {
            selections.add(table.getValueAt(i, 2).toString());
        }

        return selections;
    }
}