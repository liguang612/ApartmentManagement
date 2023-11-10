package View.Component;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ApartmentCtrl;
import Model.Apartment;
import Model.User;

public class ApartmentDisplay extends JPanel {
    ArrayList<Integer> selections = new ArrayList<Integer>();
    JPanel apartmentViewport = new JPanel();
    JScrollPane apartmentScroll = new JScrollPane();

    public ApartmentDisplay(User user) {
        ApartmentItem header = new ApartmentItem();

        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(apartmentScroll, BorderLayout.CENTER);

        apartmentViewport.setLayout(new BoxLayout(apartmentViewport, BoxLayout.Y_AXIS));

        ArrayList<Apartment> apartmentList = ApartmentCtrl.getApartmentList();

        for(Apartment apartment: apartmentList) {
            ApartmentItem temp = new ApartmentItem(
                apartment.getArea(),
                apartment.getFloor(),
                apartment.getRoom(),
                apartment.getOwnerName(),
                '0' + apartment.getOwnerPhone()
            );

            apartmentViewport.add(temp);
            temp.getCheckBox().addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    if (ie.getStateChange() == ItemEvent.SELECTED) {
                        selections.add(Integer.valueOf(temp.getApartmentId()));
                    }
                    if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        selections.remove(Integer.valueOf(temp.getApartmentId()));
                    }
                }
            });
        }

        apartmentScroll.setViewportView(apartmentViewport);

        header.getCheckBox().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                boolean state = ie.getStateChange() == ItemEvent.SELECTED ? true : false;
                int count = apartmentViewport.getComponentCount();

                selections.clear();
                for (int i = 0; i < count; i++) {
                    ((ApartmentItem)apartmentViewport.getComponent(i)).getCheckBox().setSelected(state);
                    if (state) selections.add(((ApartmentItem)apartmentViewport.getComponent(i)).getApartmentId());
                }
            }
        });
    }

    public ArrayList<Integer> getSelections() {return selections;}
}
