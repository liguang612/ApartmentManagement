import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tạo JPanel với kích thước lớn
        JPanel largePanel = new JPanel(new GridLayout(1, 1));
        largePanel.setPreferredSize(new Dimension(500, 500));
        JLabel largeLabel = new JLabel("abc");
        largeLabel.setBackground(Color.WHITE);
        largeLabel.setOpaque(true);
        largePanel.add(largeLabel);
        

        // Tạo JPanel với kích thước nhỏ
        JPanel smallPanel = new JPanel(new GridLayout(1, 1));
        smallPanel.setPreferredSize(new Dimension(200, 200));
        JLabel smallLabel = new JLabel("def");
        smallLabel.setBackground(Color.YELLOW);
        smallLabel.setOpaque(true);
        smallPanel.add(smallLabel);

        // Thêm các JScrollPane và JPanel vào JTabbedPane
        tabbedPane.addTab("Large Tab", largePanel);
        tabbedPane.addTab("Small tab", null);

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    tabbedPane.setComponentAt(1, null);
                    tabbedPane.setComponentAt(0, largePanel);
                } else {
                    tabbedPane.setComponentAt(0, null);
                    tabbedPane.setComponentAt(1, smallPanel);
                }
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        System.out.println("" + 123);
    }

}
