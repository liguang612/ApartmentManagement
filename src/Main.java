import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.*;

public class Main {
    public static void main(String[] args) {
        // Tạo dữ liệu cho bảng
        Object[][] data = {{"John", "Doe"}, {"Jane", "Doe"}};
        String[] columnNames = {"First Name", "Last Name"};

        // Tạo JTable
        JTable table = new JTable(data, columnNames);

        // Tạo TableCellRenderer tùy chỉnh
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, true));
                return label;
            }
        };

        // Đặt TableCellRenderer cho header của JTable
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(renderer);

        // Hiển thị JTable trong JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
    }
}
