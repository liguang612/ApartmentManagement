package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Controller.AuthCtrl;
import Model.User;
import Resources.Constant.Constant;
import View.Component.ApartmentDisplay;

public class Home {
    ApartmentDisplay apartmentDisplay = new ApartmentDisplay();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    JButton addApartment, deleteApartment, editApartment,
            editAccount, signOut;
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

        homeFrame = new JFrame("BlueMoon Mangager");
        homeFrame.setBackground(Color.WHITE);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setLayout(gb);
        homeFrame.setSize(1000, 500);

        function();
        content();

        homeFrame.setVisible(true);
    };

    private void accountManage() {
        accountManagePanel.setBackground(Color.WHITE);
        accountManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        editAccount = new JButton(Constant.verticalImageTitle("editAccount.png", "Sửa thông tin tài khoản"));
        editAccount.setBackground(Color.WHITE);
        signOut = new JButton(Constant.verticalImageTitle("signOut.png", "Đăng xuất"));
        signOut.setBackground(Color.WHITE);
        signOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                signOut();
            }
        });

        accountManagePanel.add(editAccount);
        accountManagePanel.add(signOut);

    }
    private void feeManage() {
        feeManagePanel.setBackground(Color.WHITE);
    }
    private void residentManage() {
        residentManagePanel.setBackground(Color.WHITE);
        residentManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        addApartment = new JButton(Constant.verticalImageTitle("addOwner.png", "Thêm chủ căn hộ"));
        addApartment.setBackground(Color.WHITE);
        deleteApartment = new JButton(Constant.verticalImageTitle("deleteOwner.png", "Xóa chủ căn hộ"));
        deleteApartment.setBackground(Color.WHITE);
        editApartment = new JButton(Constant.verticalImageTitle("editOwner.png", "Sửa chủ căn hộ"));
        editApartment.setBackground(Color.WHITE);
        
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
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 1000; gbc.insets = new Insets(10, 10, 10, 10); homeFrame.add(contentPanel, gbc);
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

        accountManage();
        feeManage();
        residentManage();

        functionTabbedPane.addTab("Cài đặt", accountManagePanel);
        functionTabbedPane.addTab("Quản lý cư dân", residentManagePanel);
        functionTabbedPane.addTab("Quản lý thu phí", feeManagePanel);
        functionTabbedPane.setTabComponentAt(0, label1);
        functionTabbedPane.setTabComponentAt(1, label2);
        functionTabbedPane.setTabComponentAt(2, label3);

        functionPanel.add(functionTabbedPane, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 100; homeFrame.add(functionPanel, gbc);
    }

    public JFrame getFrame() {return homeFrame;}

    private void signOut() {AuthCtrl.signOut(this);}
}
