package View.Component;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ApartmentDisplay extends JPanel {
    JPanel apartmentViewport = new JPanel();
    JScrollPane apartmentScroll = new JScrollPane();

    public ApartmentDisplay() {
        setLayout(new BorderLayout());

        add(new ApartmentItem(), BorderLayout.NORTH);
        add(apartmentScroll, BorderLayout.CENTER);

        apartmentViewport.setLayout(new BoxLayout(apartmentViewport, BoxLayout.Y_AXIS));
        apartmentViewport.add(new ApartmentItem(70.0, 33, "Phạm Hoàng Thành", 966322513, 06));
        apartmentViewport.add(new ApartmentItem(70.0, 33, "Phạm Hoàng Thành", 966322513, 06));

        apartmentScroll.setViewportView(apartmentViewport);
    }
}
