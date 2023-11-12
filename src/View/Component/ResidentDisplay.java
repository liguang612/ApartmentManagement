package View.Component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ResidentCtrl;
import Model.Resident;
import Model.User;

public class ResidentDisplay extends JPanel {
    ArrayList<Long> selections = new ArrayList<Long>();
    ArrayList<Resident> residentList;
    JPanel residentViewport = new JPanel();
    JScrollPane residentScroll = new JScrollPane();

    public ResidentDisplay(User user) {
        ResidentItem header = new ResidentItem();

        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(residentScroll, BorderLayout.CENTER);

        residentViewport.setLayout(new BoxLayout(residentViewport, BoxLayout.Y_AXIS));

        residentList = ResidentCtrl.getResidentList();

        for(Resident r : residentList) {
            ResidentItem temp = new ResidentItem(
                r.getId(),
                r.getName(),
                r.getBirthday(),
                r.getPhoneNumber(),
                r.getNationality(),
                r.getFloor(),
                r.getRoom(),
                r.getRelationship());

            residentViewport.add(temp);
            temp.getCheckBox().addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    if (ie.getStateChange() == ItemEvent.SELECTED) {
                        selections.add(temp.getId());
                    }
                    if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        selections.remove(temp.getId());
                    }
                }
            });
        }

        residentScroll.setViewportView(residentViewport);

        header.getCheckBox().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                boolean state = ie.getStateChange() == ItemEvent.SELECTED ? true : false;
                int count = residentViewport.getComponentCount();

                selections.clear();
                for (int i = 0; i < count; i++) {
                    ((ResidentItem)residentViewport.getComponent(i)).getCheckBox().setSelected(state);
                    if (state) selections.add(((ResidentItem)residentViewport.getComponent(i)).getId());
                }
            }
        });
    }

    public void filter(String keyword) {
        Component item;
        int endBound = residentList.size();
        for (int i = 0; i < endBound; i++) {
            item = residentViewport.getComponent(i);
            if (residentList.get(i).getName().toLowerCase().contains(keyword.toLowerCase())) {
                item.setVisible(true);
            } else {
                item.setVisible(false);
            }
        }
    }

    public ArrayList<Long> getSelections() {return selections;}
}
