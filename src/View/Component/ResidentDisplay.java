package View.Component;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ResidentCtrl;
import Model.Resident;
import Model.User;

public class ResidentDisplay extends JPanel {
    JPanel residentViewport = new JPanel();
    JScrollPane residentScroll = new JScrollPane();

    public ResidentDisplay(User user) {
        setLayout(new BorderLayout());

        add(new ResidentItem(), BorderLayout.NORTH);
        add(residentScroll, BorderLayout.CENTER);

        residentViewport.setLayout(new BoxLayout(residentViewport, BoxLayout.Y_AXIS));

        ArrayList<Resident> residentList = ResidentCtrl.getResidentList();
        for(Resident r : residentList) {
            residentViewport.add(new ResidentItem(r.getId(), r.getName(), r.getBirthday(), r.getPhoneNumber(), r.getNationality(), r.getFloor(), r.getRoom(), r.getRelationship()));
        }

        residentScroll.setViewportView(residentViewport);
    }
}
