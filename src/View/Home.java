package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.ApartmentCtrl;
import Controller.AuthCtrl;
import Controller.FeeCtrl;
import Controller.ResidentCtrl;
import Model.User;
import Resources.Constant.Constant;
import View.Component.AccountDisplay;
import View.Component.ApartmentDisplay;
import View.Component.Dialog;
import View.Component.FeeDisplay;
import View.Component.ResidentDisplay;

public class Home extends JFrame {
    AccountDisplay accountDisplay;
    FeeDisplay annualFeeDisplay, monthlyFeeDisplay, oneTimeFeeDisplay;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    Integer mode = 0;
    JButton addApartment, deleteApartment, editApartment, addResident, deleteResident, editResident, changeStatus,
            addFee, deleteFee, editFee, pay, payList,
            changePassword, editAccount, signOut;
    JPanel contentPanel, functionPanel,
           accountManagePanel = new JPanel(), feeManagePanel = new JPanel(), paymentManagePanel = new JPanel(), residentManagePanel = new JPanel(),
           accountContentPanel = new JPanel(), feeContentPanel = new JPanel(), paymentContentPanel = new JPanel(), residentContentPanel = new JPanel();
    JScrollPane residentScroll = new JScrollPane();
    JTabbedPane functionTabbedPane = new JTabbedPane(), feeTabbedPane = new JTabbedPane(), residentTabbedPane = new JTabbedPane();
    User user;

    public Home(User user) {
        try {
            if(!System.getProperty("os.name").startsWith("Linux")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.user = user;

        UIManager.put("Button.font", Constant.buttonFont.deriveFont((float)13.0));
        UIManager.put("ComboBox.font", Constant.contentFont);
        UIManager.put("Label.font", Constant.contentFont);

        changePassword = new JButton(Constant.verticalImageTitle("changePassword.png", "Đổi mật khẩu"));
        changePassword.setBackground(Color.WHITE);
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                changePassword();
            }
        });
        editAccount = new JButton(Constant.verticalImageTitle("editAccount.png", "Sửa thông tin tài khoản"));
        editAccount.setBackground(Color.WHITE);
        editAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editAccount();
            }
        });
        signOut = new JButton(Constant.verticalImageTitle("signOut.png", "Đăng xuất"));
        signOut.setBackground(Color.WHITE);
        signOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                signOut();
            }
        });

        addApartment = new JButton(Constant.verticalImageTitle("addOwner.png", "Thêm chủ căn hộ"));
        addApartment.setBackground(Color.WHITE);
        addApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addApartment();
            }
        });
        deleteApartment = new JButton(Constant.verticalImageTitle("deleteOwner.png", "Xóa chủ căn hộ"));
        deleteApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteApartment();
            }
        });
        deleteApartment.setBackground(Color.WHITE);
        editApartment = new JButton(Constant.verticalImageTitle("editOwner.png", "Sửa chủ căn hộ"));
        editApartment.setBackground(Color.WHITE);
        addResident = new JButton(Constant.verticalImageTitle("addResident.png", "Thêm cư dân"));
        addResident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addResident();
            }
        });
        addResident.setBackground(Color.WHITE);
        deleteResident = new JButton(Constant.verticalImageTitle("deleteResident.png", "Xóa cư dân"));
        deleteResident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteResident();
            }
        });
        deleteResident.setBackground(Color.WHITE);
        editResident = new JButton(Constant.verticalImageTitle("editResident.png", "Sửa thông tin cư dân"));
        editResident.setBackground(Color.WHITE);
        changeStatus = new JButton(Constant.verticalImageTitle("changeStatus.png", "Thay đổi nhân khẩu"));
        changeStatus.setBackground(Color.WHITE);

        addFee = new JButton(Constant.verticalImageTitle("addFee.png", "Thêm loại phí"));
        addFee.setBackground(Color.WHITE);
        addFee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addFee();
            }
        });
        deleteFee = new JButton(Constant.verticalImageTitle("deleteFee.png", "Xóa loại phí"));
        deleteFee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteFee();
            }
        });
        deleteFee.setBackground(Color.WHITE);
        editFee = new JButton(Constant.verticalImageTitle("editFee.png", "Sửa loại phí"));
        editFee.setBackground(Color.WHITE);

        pay = new JButton(Constant.verticalImageTitle("pay.png", "Nộp phí"));
        pay.setBackground(Color.WHITE);
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addPayment();
            }
        });
        payList = new JButton(Constant.verticalImageTitle("payList.png", "Danh sách đã nộp phí"));
        payList.setBackground(Color.WHITE);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(gb);
        setSize(1000, 500);
        setTitle("Quản lý chung chư BlueMoon");

        function();
        content();

        setVisible(true);
    };

    private void accountManage() {
        accountManagePanel.setBackground(Color.WHITE);
        accountManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        accountManagePanel.add(editAccount);
        accountManagePanel.add(changePassword);
        accountManagePanel.add(signOut);
    }
    private void feeManage() {
        feeManagePanel.setBackground(Color.WHITE);
        feeManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        feeManagePanel.add(addFee);
        feeManagePanel.add(editFee);
        feeManagePanel.add(deleteFee);
    }
    private void paymentManage() {
        paymentManagePanel.setBackground(Color.WHITE);
        paymentManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        paymentManagePanel.add(pay);
        paymentManagePanel.add(payList);
    }
    private void residentManage() {
        residentManagePanel.setBackground(Color.WHITE);
        residentManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        residentManagePanel.removeAll();
        if (mode == 0) {
            residentManagePanel.add(addApartment);
            residentManagePanel.add(editApartment);
            residentManagePanel.add(deleteApartment);
        } else {
            residentManagePanel.add(addResident);
            residentManagePanel.add(editResident);
            residentManagePanel.add(deleteResident);
            residentManagePanel.add(changeStatus);
        }
    }

    private void accountContent() {
        accountDisplay = new AccountDisplay(user);

        contentPanel.removeAll();
        contentPanel.add(accountDisplay);
    }
    private void feeContent() {
        annualFeeDisplay = new FeeDisplay(user, 2);
        monthlyFeeDisplay = new FeeDisplay(user, 1);
        oneTimeFeeDisplay = new FeeDisplay(user, 0);

        feeTabbedPane.addTab("Phí", oneTimeFeeDisplay);
        feeTabbedPane.addTab("Phí hàng tháng", monthlyFeeDisplay);
        feeTabbedPane.addTab("Phí thường niên", annualFeeDisplay);

        feeContentPanel.setBackground(Color.WHITE);
        feeContentPanel.setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH; gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0;
        feeContentPanel.add(feeTabbedPane, gbc);
    }
    private void paymentContent() {
        paymentContentPanel.setBackground(Color.WHITE);
    }
    private void residentContent() {
        residentTabbedPane.addTab("Danh sách căn hộ", new ApartmentDisplay(user));
        residentTabbedPane.addTab("Danh sách cư dân", new ResidentDisplay(user));

        residentContentPanel.setBackground(Color.WHITE);
        residentContentPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH; gbc.anchor = GridBagConstraints.CENTER; gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0;
        residentContentPanel.add(residentTabbedPane, gbc);
    }

    private void content() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));

        accountContent();
        feeContent();
        paymentContent();
        residentContent();

        residentTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                mode = residentTabbedPane.getSelectedIndex();
                residentManage();
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 1000; gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; contentPanel.add(accountContentPanel, gbc);
        gbc.anchor = GridBagConstraints.NORTH; add(contentPanel, gbc);
    }

    private void function() {
        JLabel label1, label2, label3, label4;

        label1 = new JLabel("Tài khoản", JLabel.CENTER);
        label1.setBackground(Color.WHITE);
        label1.setFont(label1.getFont().deriveFont((float)12.0));
        label1.setPreferredSize(new Dimension(75, 25));

        label2 = new JLabel("Quản lý cư dân", JLabel.CENTER);
        label2.setBackground(Color.WHITE);
        label2.setFont(label1.getFont());
        label2.setPreferredSize(new Dimension(100, 25));

        label3 = new JLabel("Quản lý phí thu", JLabel.CENTER);
        label3.setBackground(Color.WHITE);
        label3.setFont(label1.getFont());
        label3.setPreferredSize(new Dimension(100, 25));

        label4 = new JLabel("Nộp phí", JLabel.CENTER);
        label4.setBackground(Color.WHITE);
        label4.setFont(label1.getFont());
        label4.setPreferredSize(new Dimension(100, 25));        

        functionPanel = new JPanel(new BorderLayout());
        functionPanel.setBackground(new Color(237, 237, 237, 200));

        accountManage();
        feeManage();
        paymentManage();
        residentManage();

        functionTabbedPane.addTab("Tài khoản", accountManagePanel);
        functionTabbedPane.addTab("Quản lý cư dân", residentManagePanel);
        functionTabbedPane.addTab("Quản lý phí thu", feeManagePanel);
        functionTabbedPane.addTab("Nộp phí", paymentManagePanel);
        functionTabbedPane.setTabComponentAt(0, label1);
        functionTabbedPane.setTabComponentAt(1, label2);
        functionTabbedPane.setTabComponentAt(2, label3);
        functionTabbedPane.setTabComponentAt(3, label4);

        functionTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (functionTabbedPane.getSelectedIndex() == 0) {
                    contentPanel.removeAll();
                    
                    accountContent();
                    gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(accountContentPanel, gbc);

                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else if (functionTabbedPane.getSelectedIndex() == 1) {
                    contentPanel.removeAll();

                    gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(residentContentPanel, gbc);
                    
                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else if (functionTabbedPane.getSelectedIndex() == 2) {
                    contentPanel.removeAll();

                    gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(feeContentPanel, gbc);
                    
                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else {
                    contentPanel.removeAll();

                    gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(paymentContentPanel, gbc);

                    contentPanel.revalidate();
                    contentPanel.repaint();
                }
            }
        });
        functionPanel.add(functionTabbedPane, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 100; add(functionPanel, gbc);
    }

    public JFrame getFrame() {return this;}
    public JTabbedPane getFeeTabbedPane() {return feeTabbedPane;}
    public JTabbedPane getResidentTabbedPane() {return residentTabbedPane;}

    private void addApartment() {
        setEnabled(false);
        new AddApartment(this, user);
    }
    private void deleteApartment() {
        if (ApartmentCtrl.deleteApartment(((ApartmentDisplay)residentTabbedPane.getSelectedComponent()).getSelections())) {
            new Dialog(this, user, 2, "Thành công");
            residentTabbedPane.setComponentAt(0, new ApartmentDisplay(user));
        } else {
            new Dialog(this, user, 0, "Thất bại");
        }
    }

    private void addFee() {
        setEnabled(false);
        new AddFee(this, user);
    }
    private void deleteFee() {
        int selected = feeTabbedPane.getSelectedIndex();
        if (FeeCtrl.deleteFee(((FeeDisplay)feeTabbedPane.getSelectedComponent()).getSelections())) {
            new Dialog(this, user, 2, "Thành công");
            feeTabbedPane.setComponentAt(selected, new FeeDisplay(user, selected));
        } else {
            new Dialog(this, user, 0, "Thất bại");
        }
    }

    private void addPayment() {
        setEnabled(false);
        new AddPayment(this, user);
    }

    private void addResident() {
        setEnabled(false);
        new AddResident(this, user);
    }
    private void deleteResident() {
        if (ResidentCtrl.deleteResident(((ResidentDisplay)residentTabbedPane.getSelectedComponent()).getSelections())) {
            new Dialog(this, user, 2, "Thành công");
            residentTabbedPane.setComponentAt(0, new ApartmentDisplay(user));
            residentTabbedPane.setComponentAt(1, new ResidentDisplay(user));
        } else {
            new Dialog(this, user, 0, "Thất bại");
        }
    }

    private void changePassword() {
        setEnabled(false);
        new ChangePassword(this, user);
    }
    private void editAccount() {
        accountDisplay.turnEditModeOn();
    }
    private void signOut() {AuthCtrl.signOut(this);}
}
