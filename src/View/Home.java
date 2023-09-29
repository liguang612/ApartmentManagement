package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Model.User;

public class Home {
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    JFrame homeFrame;
    JPanel contentPanel, functionPanel, accountManagePanel = new JPanel(), feeManagePanel = new JPanel(), residentManagePanel = new JPanel();
    JTabbedPane functionTabbedPane = new JTabbedPane();

    public Home(User user) {
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        homeFrame = new JFrame("BlueMoon Mangager");
        
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(new FlowLayout());
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setSize(1000, 500);

        homeFrame.setVisible(true);
    };

    private void function() {
        functionPanel = new JPanel(new BorderLayout());

        functionTabbedPane.addTab("Cài đặt", contentPanel);
        functionTabbedPane.addTab("Quản lý cư dân", contentPanel);
        functionTabbedPane.addTab("Quản lý thu phí", contentPanel);
    }
}
