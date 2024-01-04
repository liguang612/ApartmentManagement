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

import Controller.ResidentCtrl;
import Model.Resident;
import Model.User;
import Resources.Constant.Constant;

public class ResidentDisplay extends JPanel {
    ArrayList<Resident> residentList;
    DefaultTableModel model;
    JLabel statistics;
    JTable table;
    String[] header = {"CCCD/CMT", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Dân tộc", "Quốc tịch", "Tầng", "Phòng", "Mối quan hệ với chủ hộ"};
    String[][] data;

    public ResidentDisplay(User user) {
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 15));

        residentList = ResidentCtrl.getResidentList();

        model = new DefaultTableModel(data, header){
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
        int absent = 0, living = 0, staying = 0;

        for (Resident resident : residentList) {
            if (resident.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(resident.toData());

                switch (resident.getStatus()) {
                    case 0:
                        ++living;
                        break;
                    case 1:
                        ++staying;
                        break;
                    case 2:
                        ++absent;
                        break;
                    default:
                        break;
                }
            }
        }

        model.setDataVector(filteredData.toArray(new String[0][0]), header);
        statistics.setText("Tổng số cư dân: " + filteredData.size() + "   (Thường trú: " + living + "   Tạm trú: " + staying + "   Tạm vắng: " + absent + ")");
    }

    public ArrayList<Long> getSelections() {
        ArrayList<Long> selections = new ArrayList<>();

        for (int i : table.getSelectedRows()) {
            selections.add(Long.parseLong(table.getValueAt(i, 0).toString()));
        }

        return selections;
    }
}
