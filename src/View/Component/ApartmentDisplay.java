package View.Component;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ApartmentCtrl;
import Model.Apartment;
import Model.User;

public class ApartmentDisplay extends JPanel {
    JPanel apartmentViewport = new JPanel();
    JScrollPane apartmentScroll = new JScrollPane();

    public ApartmentDisplay(User user) {
        setLayout(new BorderLayout());

        add(new ApartmentItem(), BorderLayout.NORTH);
        add(apartmentScroll, BorderLayout.CENTER);

        apartmentViewport.setLayout(new BoxLayout(apartmentViewport, BoxLayout.Y_AXIS));

        ArrayList<Apartment> apartmentList = ApartmentCtrl.getApartmentList(user.getId());

        for(Apartment apartment: apartmentList) {
            apartmentViewport.add(new ApartmentItem(
                apartment.getArea(),
                apartment.getFloor(),
                apartment.getRoom(),
                apartment.getOwnerName(),
                '0' + apartment.getOwnerPhone()
            ));
        }

        apartmentScroll.setViewportView(apartmentViewport);
    }
}
