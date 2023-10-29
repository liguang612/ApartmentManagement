package View.Component;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.FeeCtrl;
import Model.Fee;
import Model.User;

public class FeeDisplay extends JPanel {
    JPanel feeViewport = new JPanel();
    JScrollPane feeScroll = new JScrollPane();

    public FeeDisplay(User user, int cycle) {
        setLayout(new BorderLayout());

        add(new FeeItem(), BorderLayout.NORTH);
        add(feeScroll, BorderLayout.CENTER);

        feeViewport.setLayout(new BoxLayout(feeViewport, BoxLayout.Y_AXIS));

        ArrayList<Fee> feeList = FeeCtrl.getFeeList(cycle);
        for(Fee fee: feeList) {
            feeViewport.add(new FeeItem(
                fee.getCost(),
                fee.getExpirationDate(),
                fee.getMandatory(),
                fee.getName()
            ));
        }

        feeScroll.setViewportView(feeViewport);
    }
}
