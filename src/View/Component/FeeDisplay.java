package View.Component;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.FeeCtrl;
import Model.Fee;
import Model.User;

public class FeeDisplay extends JPanel {
    ArrayList<Integer> selections = new ArrayList<Integer>();
    JPanel feeViewport = new JPanel();
    JScrollPane feeScroll = new JScrollPane();

    public FeeDisplay(User user, int cycle) {
        FeeItem header = new FeeItem();

        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(feeScroll, BorderLayout.CENTER);

        feeViewport.setLayout(new BoxLayout(feeViewport, BoxLayout.Y_AXIS));

        ArrayList<Fee> feeList = FeeCtrl.getFeeList(cycle);
        for(Fee fee: feeList) {
            FeeItem temp = new FeeItem(
                fee.getCost(),
                fee.getExpirationDate(),
                fee.getMandatory(),
                fee.getName()
            );
            temp.setFeeId(fee.getId());

            feeViewport.add(temp);
            temp.getCheckBox().addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    if (ie.getStateChange() == ItemEvent.SELECTED) {
                        selections.add(temp.getFeeId());
                    }
                    if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        selections.remove(temp.getFeeId());
                    }
                }
            });
        }

        feeScroll.setViewportView(feeViewport);

        header.getCheckBox().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                boolean state = ie.getStateChange() == ItemEvent.SELECTED ? true : false;
                int count = feeViewport.getComponentCount();
                
                selections.clear();
                for (int i = 0; i < count; i++) {
                    ((FeeItem)feeViewport.getComponent(i)).getCheckBox().setSelected(state);
                    if (state) selections.add(((FeeItem)feeViewport.getComponent(i)).getFeeId());
                }
            }
        });
    }

    public ArrayList<Integer> getSelections() {return selections;}
}
