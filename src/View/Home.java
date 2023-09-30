package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Model.User;
import View.Component.ApartmentDisplay;

public class Home {
    ApartmentDisplay apartmentDisplay = new ApartmentDisplay();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    JButton addApartment = new JButton("Thêm chủ hộ"), deleteApartment = new JButton("Xóa chủ hộ"), editApartment = new JButton("Sửa chủ hộ");
    JFrame homeFrame;
    JPanel contentPanel, functionPanel,
            accountManagePanel = new JPanel(), feeManagePanel = new JPanel(), residentManagePanel = new JPanel(),
            residentDisplay = new JPanel();
    JScrollPane residentScroll = new JScrollPane();
    JTabbedPane contentTabbedPane = new JTabbedPane(), functionTabbedPane = new JTabbedPane();

    public Home(User user) {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        homeFrame = new JFrame(user.getAbName());
        homeFrame.setBackground(Color.WHITE);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setLayout(gb);
        homeFrame.setSize(1000, 500);

        function();
        content();

        homeFrame.setVisible(true);
    };

    private void residentManage() {
        residentManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        residentManagePanel.add(addApartment);
        residentManagePanel.add(editApartment);
        residentManagePanel.add(deleteApartment);
    }

    private void content() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));

        contentTabbedPane.addTab("Danh sách căn hộ", apartmentDisplay);
        contentTabbedPane.addTab("Danh sách cư dân", residentDisplay);

        contentPanel.add(contentTabbedPane, BorderLayout.CENTER);

        gbc.anchor = GridBagConstraints.NORTH; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 880; gbc.insets = new Insets(10, 10, 10, 10); homeFrame.add(contentPanel, gbc);
    }

    private void function() {
        JLabel label1, label2, label3;

        label1 = new JLabel("Cài đặt", JLabel.CENTER);
        label1.setBackground(Color.WHITE);
        label1.setFont(label1.getFont().deriveFont((float)12.0));
        label1.setPreferredSize(new Dimension(75, 25));

        label2 = new JLabel("Quản lý cư dân", JLabel.CENTER);
        label2.setBackground(Color.WHITE);
        label2.setFont(label1.getFont().deriveFont((float)12.0));
        label2.setPreferredSize(new Dimension(100, 25));

        label3 = new JLabel("Quản lý thu phí", JLabel.CENTER);
        label3.setBackground(Color.WHITE);
        label3.setFont(label1.getFont().deriveFont((float)12.0));
        label3.setPreferredSize(new Dimension(100, 25));

        functionPanel = new JPanel(new BorderLayout());
        functionPanel.setBackground(new Color(237, 237, 237, 200));

        residentManage();

        functionTabbedPane.addTab("Cài đặt", accountManagePanel);
        functionTabbedPane.addTab("Quản lý cư dân", residentManagePanel);
        functionTabbedPane.addTab("Quản lý thu phí", feeManagePanel);
        functionTabbedPane.setTabComponentAt(0, label1);
        functionTabbedPane.setTabComponentAt(1, label2);
        functionTabbedPane.setTabComponentAt(2, label3);

        functionPanel.add(functionTabbedPane, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 200; homeFrame.add(functionPanel, gbc);
    }
}
