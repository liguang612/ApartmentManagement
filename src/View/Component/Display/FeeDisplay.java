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

import Controller.FeeCtrl;
import Model.Fee;
import Model.User;
import Resources.Constant.Constant;
public class FeeDisplay extends JPanel {
    ArrayList<Fee> feeList;
    JLabel statistics;
    JTable table;
    DefaultTableModel model;
    String[] header = {"Mã phí", "Tên", "Chi phí / đơn vị", "Hạn nộp", "Bắt buộc"};
    Object[][] data;

    public FeeDisplay(User user, int cycle) {
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 15));

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

        statistics = new JLabel("", JLabel.RIGHT);

        filter("");

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statistics, BorderLayout.SOUTH);
    }

    public void filter(String keyword) {
        ArrayList<Object[]> filteredData = new ArrayList<>();
        int mandatory = 0, optional = 0;

        for (Fee fee : feeList) {
            if (fee.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(fee.toData());
                if (fee.getMandatory()) {++mandatory;} else {++optional;}
            }
        }

        model.setDataVector(filteredData.toArray(new Object[0][0]), header);
        statistics.setText("Số loại phí: " + filteredData.size() + "   (Bắt buộc: " + mandatory + "   Không bắt buộc: " + optional + ")");

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