package View.Component;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Apartment;
import Model.User;
import SQL.DBQuery;

public class ApartmentDisplay extends JPanel {
    JPanel apartmentViewport = new JPanel();
    JScrollPane apartmentScroll = new JScrollPane();

    public ApartmentDisplay(User user) {
        setLayout(new BorderLayout());

        add(new ApartmentItem(), BorderLayout.NORTH);
        add(apartmentScroll, BorderLayout.CENTER);

        apartmentViewport.setLayout(new BoxLayout(apartmentViewport, BoxLayout.Y_AXIS));
        apartmentViewport.add(new ApartmentItem((float)70.0, 33, 06, "Phạm Hoàng Thành", "966322513"));
        apartmentViewport.add(new ApartmentItem((float)70.0, 33, 06, "Phạm Hoàng Thành", "966322513"));

        // ArrayList<Apartment> apartmentList = DBQuery.getApartmentList(user.getId());

        // for(Apartment apartment: apartmentList) {
        //     apartmentViewport.add(new ApartmentItem(
        //         apartment.getArea(),
        //         apartment.getFloor(),
        //         apartment.getRoom(),
        //         apartment.getOwnerName(),
        //         apartment.getOwnerPhone()
        //     ));
        // }

        apartmentScroll.setViewportView(apartmentViewport);
    }
}
