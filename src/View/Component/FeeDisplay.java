package View.Component;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FeeDisplay extends JPanel {
    JPanel feeViewport = new JPanel();
    JScrollPane feeScroll = new JScrollPane();

    public FeeDisplay() {
        setLayout(new BorderLayout());

        add(new FeeItem(), BorderLayout.NORTH);
        add(feeScroll, BorderLayout.CENTER);

        feeViewport.setLayout(new BoxLayout(feeViewport, BoxLayout.Y_AXIS));
        feeViewport.add(new FeeItem(50000, "31/12/2023", true, "Only - fan"));
        feeViewport.add(new FeeItem(50000, "31/12/2023", true, "Only - fan"));

        feeScroll.setViewportView(feeViewport);
    }
}
