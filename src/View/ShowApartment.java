package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ApartmentCtrl;
import Model.Resident;
import Resources.Constant.Constant;
import View.Component.ResidentCard;

public class ShowApartment extends JFrame {
    ArrayList<Resident> members;

    public ShowApartment(JFrame prevFrame, Integer id) {
        JLabel label1 = new JLabel("Danh sách cư dân căn hộ phòng " + id, JLabel.CENTER);
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        members = ApartmentCtrl.getMembers(id);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                prevFrame.setEnabled(true);
            }
        });
        
        setLayout(new BorderLayout(20, 10));
        setLocation(prevFrame.getWidth() / 2 - 500, prevFrame.getHeight() / 2 - 350);
        setSize(new Dimension(1000, 700));
        setTitle("Danh sách cư dân căn hộ " + id);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panel.setLayout(new GridLayout(members.size() / 2 + 1, 2, 20, 10));

        label1.setFont(Constant.titleFont.deriveFont((float)24.0));

        for (Resident r : members) {
            panel.add(new ResidentCard(r));
        }

        scrollPane.setViewportView(panel);

        add(label1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
