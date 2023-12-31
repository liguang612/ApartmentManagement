package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.FeeCtrl;
import Model.Fee;
import Model.Payment;
import Resources.Constant.Constant;
import View.Component.Object.SearchBox;

public class ShowPaymentList extends JFrame {
    ArrayList<Payment> payments;
    DefaultTableModel model;
    Fee fee;
    JTable table;
    SearchBox paymentSearchBox = new SearchBox(new ImageIcon(Constant.image + "search.png"), "Nhập từ khóa để tìm kiếm");
    String[] header = {"Tầng", "Phòng", "Người nộp", "Ngày nộp", "Định kỳ", "Số lượng\n(m2, người, ...)", "Đã nộp"};
    String[][] data;

    public ShowPaymentList(JFrame prevFrame, Integer feeId) {
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("Table.font", Constant.contentFont);
        UIManager.put("TableHeader.font", Constant.titleFont);

        setLayout(new BorderLayout(15, 15));
        setLocation(prevFrame.getWidth() / 2 - 600, prevFrame.getHeight() / 2 - 400);
        setSize(new Dimension(1200, 800));
        setTitle("Danh sách nộp phí");

        fee = FeeCtrl.getFee(feeId);
        payments = FeeCtrl.getPaymentList(fee.getId());

        int temp = payments.size();
        data = new String[temp][];
        for (int i = 0; i < temp; i++) {
            data[i] = payments.get(i).toData();
        }

        JLabel label1 = new JLabel("Loại phí: ");
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
        panel1.add(new JLabel(fee.getName()));

        panel2.add(paymentSearchBox);
        paymentSearchBox.setPreferredSize(new Dimension(200, 25));
        paymentSearchBox.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == '\n') {
                    filter(paymentSearchBox.getText());
                }
            }
        });

        panel.add(panel1, BorderLayout.WEST);
        panel.add(panel2, BorderLayout.SOUTH);

        panel3.add(new JScrollPane(table));

        add(panel, BorderLayout.NORTH);
        add(panel3, BorderLayout.CENTER);

        setVisible(true);
    }

    public void filter(String keyword) {
        ArrayList<String[]> filteredData = new ArrayList<>();

        for (Payment payment : payments) {
            if (payment.getPayee().toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(payment.toData());
            }
        }

        model.setDataVector(filteredData.toArray(new String[0][0]), header);

        this.revalidate();
        this.repaint();
    }
}