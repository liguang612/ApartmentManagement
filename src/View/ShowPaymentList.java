package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Controller.FeeCtrl;
import Model.Fee;
import Model.Payment;
import Resources.Constant.Constant;

public class ShowPaymentList extends JFrame {
    ArrayList<Payment> payments;
    Fee fee;

    public ShowPaymentList(JFrame prevFrame, Integer feeId) {
        UIManager.put("Label.font", Constant.contentFont);

        fee = FeeCtrl.getFee(feeId);
        payments = FeeCtrl.getPaymentList(fee.getId());

        JLabel label1 = new JLabel("Loại phí: ");
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });

        setLayout(new BorderLayout(15, 15));
        setLocation(prevFrame.getWidth() / 2 - 500, prevFrame.getHeight() / 2 - 350);
        setSize(new Dimension(1000, 700));
        setTitle("Danh sách nộp phí");
        
        label1.setFont(Constant.contentFont.deriveFont(Font.BOLD));

        panel1.add(label1);
        panel1.add(new JLabel(fee.getName()));

        add(panel1, BorderLayout.NORTH);

        setVisible(true);
    }
}
