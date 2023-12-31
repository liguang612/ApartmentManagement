package View.Component.Display;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.FeeCtrl;
import Model.Fee;
import Model.User;
import Resources.Constant.Constant;
public class FeeDisplay extends JPanel {
    ArrayList<Fee> feeList;
    JTable table;
    DefaultTableModel model;
    String[] header = {"Mã phí", "Tên", "Chi phí / đơn vị", "Hạn nộp", "Bắt buộc"};
    Object[][] data;

    public FeeDisplay(User user, int cycle) {
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setLayout(new GridLayout(1, 1));

        feeList = FeeCtrl.getFeeList(cycle);

        model = new DefaultTableModel(data, header) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(25);

        filter("");

        add(new JScrollPane(table));
    }

    public void filter(String keyword) {
        ArrayList<Object[]> filteredData = new ArrayList<>();

        for (Fee fee : feeList) {
            if (fee.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(fee.toData());
            }
        }

        model.setDataVector(filteredData.toArray(new Object[0][0]), header);

        this.revalidate();
        this.repaint();
    }

    public ArrayList<Integer> getSelections() {
        ArrayList<Integer> selections = new ArrayList<>();

        for (int i : table.getSelectedRows()) {
            selections.add(Integer.parseInt(table.getValueAt(i, 0).toString()));
        }

        return selections;
    }
}