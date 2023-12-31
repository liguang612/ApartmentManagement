package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import View.Component.Display.AccountDisplay;
import View.Component.Display.ApartmentDisplay;
import View.Component.Display.FeeDisplay;
import View.Component.Display.ResidentDisplay;
import View.Component.Object.Dialog;
import View.Component.Object.SearchBox;
import View.Page.AddApartment;
import View.Page.EditApartment;
import View.Page.ShowApartment;

public class Home extends JFrame {
    AccountDisplay accountDisplay;
    FeeDisplay annualFeeDisplay, monthlyFeeDisplay, oneTimeFeeDisplay;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    Integer mode = 0;
    JButton addApartment, deleteApartment, editApartment, showApartment,
            addResident, changeStatus, deleteResident, editResident, exchange, getInTemp, getOutTemp, historyStatus,
            addFee, deleteFee, editFee, pay, payList,
            changePassword, editAccount, signOut;
    JPanel contentPanel, functionPanel,
           accountManagePanel = new JPanel(), feeManagePanel = new JPanel(), residentManagePanel = new JPanel(),
           accountContentPanel = new JPanel(), feeContentPanel = new JPanel(), residentContentPanel = new JPanel();
    JPopupMenu changeStatusPopupMenu = new JPopupMenu();
    JScrollPane residentScroll = new JScrollPane();
    JTabbedPane functionTabbedPane = new JTabbedPane(), feeTabbedPane = new JTabbedPane(), residentTabbedPane = new JTabbedPane();
    SearchBox apartmentSearchBox = new SearchBox(new ImageIcon(Constant.image + "search.png"), "Nhập từ khóa để tìm kiếm"),
              feeSearchBox = new SearchBox(new ImageIcon(Constant.image + "search.png"), "Nhập từ khóa để tìm kiếm");
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
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("ComboBox.font", Constant.contentFont);
        UIManager.put("Label.font", Constant.contentFont);
        UIManager.put("PopupMenu.Background", Color.WHITE);
        UIManager.put("TabbedPane.font", Constant.contentFont);

        changePassword = new JButton(Constant.verticalImageTitle("changePassword.png", "Đổi mật khẩu"));
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                changePassword();
            }
        });
        editAccount = new JButton(Constant.verticalImageTitle("editAccount.png", "Sửa thông tin tài khoản"));
        editAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editAccount();
            }
        });
        signOut = new JButton(Constant.verticalImageTitle("signOut.png", "Đăng xuất"));
        signOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                signOut();
            }
        });

        addApartment = new JButton(Constant.verticalImageTitle("addOwner.png", "Thêm chủ căn hộ"));
        addApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addApartment();
            }
        });
        apartmentSearchBox.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == '\n') {
                    if (residentTabbedPane.getSelectedComponent() instanceof ApartmentDisplay)
                        ((ApartmentDisplay)residentTabbedPane.getSelectedComponent()).filter(apartmentSearchBox.getText());
                    else
                        ((ResidentDisplay)residentTabbedPane.getSelectedComponent()).filter(apartmentSearchBox.getText());
                }
            }
        });
        apartmentSearchBox.setPreferredSize(new Dimension(300, 30));
        deleteApartment = new JButton(Constant.verticalImageTitle("deleteOwner.png", "Xóa chủ căn hộ"));
        deleteApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteApartment();
            }
        });
        editApartment = new JButton(Constant.verticalImageTitle("editOwner.png", "Sửa chủ căn hộ"));
        editApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editApartment();
            }
        });
        showApartment = new JButton(Constant.verticalImageTitle("showApartment.png", "Thống kê nhân khẩu"));
        showApartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                showApartment();
            }
        });

        addResident = new JButton(Constant.verticalImageTitle("addResident.png", "Thêm cư dân"));
        addResident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addResident();
            }
        });
        deleteResident = new JButton(Constant.verticalImageTitle("deleteResident.png", "Xóa cư dân"));
        deleteResident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteResident();
            }
        });
        editResident = new JButton(Constant.verticalImageTitle("editResident.png", "Sửa thông tin cư dân"));
        editResident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editResident();
            }
        });
        exchange = new JButton("Chuyển hộ khẩu");
        exchange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });
        getInTemp = new JButton("Đăng ký tạm trú");
        getInTemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                getInTempStatus();
            }
        });
        getOutTemp = new JButton("Đăng ký tạm vắng");
        getOutTemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                getOutTempStatus();
            }
        });

        changeStatusPopupMenu.setLayout(new GridLayout(1, 3));
        changeStatusPopupMenu.add(exchange);
        changeStatusPopupMenu.add(getInTemp);
        changeStatusPopupMenu.add(getOutTemp);
        changeStatus = new JButton(Constant.verticalImageTitle("changeStatus.png", "Thay đổi nhân khẩu"));
        changeStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                changeStatusPopupMenu.show(changeStatus, 0, changeStatus.getHeight());
            }
        });
        historyStatus = new JButton(Constant.verticalImageTitle("historyStatus.png", "Lịch sử cư trú"));
        historyStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });

        addFee = new JButton(Constant.verticalImageTitle("addFee.png", "Thêm loại phí"));
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
        editFee = new JButton(Constant.verticalImageTitle("editFee.png", "Sửa loại phí"));
        editFee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editFee();
            }
        });
        feeSearchBox.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() == '\n') {
                    ((FeeDisplay)feeTabbedPane.getSelectedComponent()).filter(feeSearchBox.getText());
                }
            }
        });
        feeSearchBox.setPreferredSize(new Dimension(300, 30));

        pay = new JButton(Constant.verticalImageTitle("pay.png", "Nộp phí"));
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addPayment();
            }
        });
        payList = new JButton(Constant.verticalImageTitle("payList.png", "Danh sách đã nộp phí"));
        payList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                showPaymentList();
            }
        });

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
        feeManagePanel.add(pay);
        feeManagePanel.add(payList);
    }
    private void residentManage() {
        residentManagePanel.setBackground(Color.WHITE);
        residentManagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        residentManagePanel.removeAll();
        if (mode == 0) {
            residentManagePanel.add(addApartment);
            residentManagePanel.add(editApartment);
            residentManagePanel.add(deleteApartment);
            residentManagePanel.add(showApartment);
        } else {
            residentManagePanel.add(addResident);
            residentManagePanel.add(editResident);
            residentManagePanel.add(deleteResident);
            residentManagePanel.add(changeStatus);
            residentManagePanel.add(historyStatus);
        }

        residentManagePanel.revalidate();
        residentManagePanel.repaint();
    }

    private void accountContent() {
        accountDisplay = new AccountDisplay(user);

        contentPanel.removeAll();
        contentPanel.add(accountDisplay);
    }
    private void feeContent() {
        JLabel label1, label2, label3;

        label1 = new JLabel("Phí", JLabel.CENTER);
        label1.setBackground(Color.WHITE);
        label1.setFont(label1.getFont().deriveFont((float)12.0));
        label1.setPreferredSize(new Dimension(75, 25));

        label2 = new JLabel("Phí hàng tháng", JLabel.CENTER);
        label2.setBackground(Color.WHITE);
        label2.setFont(label1.getFont());
        label2.setPreferredSize(new Dimension(100, 25));

        label3 = new JLabel("Phí thường niên", JLabel.CENTER);
        label3.setBackground(Color.WHITE);
        label3.setFont(label1.getFont());
        label3.setPreferredSize(new Dimension(100, 25));

        feeTabbedPane.addTab("Phí", new FeeDisplay(user, 0));
        feeTabbedPane.addTab("Phí hàng tháng", null);
        feeTabbedPane.addTab("Phí thường niên", null);
        feeTabbedPane.setTabComponentAt(0, label1);
        feeTabbedPane.setTabComponentAt(1, label2);
        feeTabbedPane.setTabComponentAt(2, label3);

        feeTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (feeTabbedPane.getSelectedIndex() == 0) {
                    feeTabbedPane.setComponentAt(0, new FeeDisplay(user, 0));
                    feeTabbedPane.setComponentAt(1, null);
                    feeTabbedPane.setComponentAt(2, null);
                } else if (feeTabbedPane.getSelectedIndex() == 1) {
                    feeTabbedPane.setComponentAt(0, null);
                    feeTabbedPane.setComponentAt(1, new FeeDisplay(user, 1));
                    feeTabbedPane.setComponentAt(2, null);
                } else {
                    feeTabbedPane.setComponentAt(0, null);
                    feeTabbedPane.setComponentAt(1, null);
                    feeTabbedPane.setComponentAt(2, new FeeDisplay(user, 2));
                }
            }
        });

        feeContentPanel.setBackground(Color.WHITE);
        feeContentPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; feeContentPanel.add(feeSearchBox, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1.0; gbc.weighty = 100.0; feeContentPanel.add(feeTabbedPane, gbc);
    }
    private void residentContent() {
        JLabel label1, label2;

        label1 = new JLabel("Danh sách căn hộ", JLabel.CENTER);
        label1.setBackground(Color.WHITE);
        label1.setFont(label1.getFont().deriveFont((float)12.0));
        label1.setPreferredSize(new Dimension(100, 25));

        label2 = new JLabel("Danh sách cư dân", JLabel.CENTER);
        label2.setBackground(Color.WHITE);
        label2.setFont(label1.getFont());
        label2.setPreferredSize(new Dimension(100, 25));

        residentTabbedPane.addTab("Danh sách căn hộ", new ApartmentDisplay(user));
        residentTabbedPane.addTab("Danh sách cư dân", null);
        residentTabbedPane.setTabComponentAt(0, label1);
        residentTabbedPane.setTabComponentAt(1, label2);

        residentTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (residentTabbedPane.getSelectedIndex() == 0) {
                    residentTabbedPane.setComponentAt(0, new ApartmentDisplay(user));
                    residentTabbedPane.setComponentAt(1, null);
                } else {
                    residentTabbedPane.setComponentAt(0, null);
                    residentTabbedPane.setComponentAt(1, new ResidentDisplay(user));
                }
            }
        });

        residentContentPanel.setBackground(Color.WHITE);
        residentContentPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; residentContentPanel.add(apartmentSearchBox, gbc);
        gbc.fill = GridBagConstraints.BOTH; gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1.0; gbc.weighty = 100.0; residentContentPanel.add(residentTabbedPane, gbc);
    }

    private void content() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));

        accountContent();
        feeContent();
        residentContent();

        residentTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                mode = residentTabbedPane.getSelectedIndex();
                residentManage();
            }
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 1000; gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH; contentPanel.add(accountContentPanel, gbc);
        gbc.anchor = GridBagConstraints.NORTH; add(contentPanel, gbc);
    }

    private void function() {
        JLabel label1, label2, label3;

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

        functionPanel = new JPanel(new BorderLayout());
        functionPanel.setBackground(new Color(237, 237, 237, 200));

        accountManage();
        feeManage();
        residentManage();

        functionTabbedPane.addTab("Tài khoản", accountManagePanel);
        functionTabbedPane.addTab("Quản lý cư dân", residentManagePanel);
        functionTabbedPane.addTab("Quản lý phí thu", feeManagePanel);
        functionTabbedPane.setTabComponentAt(0, label1);
        functionTabbedPane.setTabComponentAt(1, label2);
        functionTabbedPane.setTabComponentAt(2, label3);

        functionTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (functionTabbedPane.getSelectedIndex() == 0) {
                    contentPanel.removeAll();
                    
                    accountContent();
                    gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
                    contentPanel.add(accountContentPanel, gbc);

                    revalidate();
                    repaint();
                } else if (functionTabbedPane.getSelectedIndex() == 1) {
                    contentPanel.removeAll();

                    gbc.anchor = GridBagConstraints.NORTH; gbc.fill = GridBagConstraints.BOTH;
                    contentPanel.add(residentContentPanel, gbc);
                    
                    revalidate();
                    repaint();
                } else {
                    contentPanel.removeAll();

                    gbc.anchor = GridBagConstraints.NORTH; gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(feeContentPanel, gbc);
                    
                    revalidate();
                    repaint();
                }
            }
        });
        functionPanel.add(functionTabbedPane, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 100; add(functionPanel, gbc);
    }

    public JFrame getFrame() {return this;}
    public JTabbedPane getFeeTabbedPane() {return feeTabbedPane;}

    public void setApartmentDisplay(ApartmentDisplay apartmentDisplay) {residentTabbedPane.setComponentAt(0, apartmentDisplay);}
    public void setResidentDisplay(ResidentDisplay residentDisplay) {residentTabbedPane.setComponentAt(1, residentDisplay);}

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
    private void editApartment() {
        ArrayList<Integer> selections = ((ApartmentDisplay)residentTabbedPane.getSelectedComponent()).getSelections();
        if (selections.size() == 1) {
            setEnabled(false);
            new EditApartment(this, user, selections.get(0));
        } else {
            new Dialog(this, user, 0, "Chỉ được chọn 1 mục!");
        }
    }
    private void showApartment() {
        ArrayList<Integer> selections = ((ApartmentDisplay)residentTabbedPane.getSelectedComponent()).getSelections();
        if (selections.size() == 1) {
            setEnabled(false);
            new ShowApartment(this, selections.get(0));
        } else {
            new Dialog(this, user, 0, "Chỉ được chọn 1 mục!");
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
    private void editFee() {
        ArrayList<Integer> selections = ((FeeDisplay)feeTabbedPane.getSelectedComponent()).getSelections();
        if (selections.size() == 1) {
            setEnabled(false);
            new EditFee(this, user, selections.get(0));
        } else {
            new Dialog(this, user, 0, "Chỉ được chọn 1 mục!");
        }
    }

    private void addPayment() {
        setEnabled(false);
        new AddPayment(this, user);
    }
    private void showPaymentList() {
        ArrayList<Integer> selections = ((FeeDisplay)feeTabbedPane.getSelectedComponent()).getSelections();
        if (selections.size() == 1) {
            setEnabled(false);
            new ShowPaymentList(this, selections.get(0));
        } else {
            new Dialog(this, user, 0, "Chỉ được chọn 1 mục!");
        }
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
    private void editResident() {
        ArrayList<Long> selections = ((ResidentDisplay)residentTabbedPane.getSelectedComponent()).getSelections();
        if (selections.size() == 1) {
            setEnabled(false);
            new EditResident(this, user, selections.get(0));
        } else {
            new Dialog(this, user, 0, "Chỉ được chọn 1 mục !");
        }
    }
    private void exchange() {

    }
    private void getInTempStatus() {

    }
    private void getOutTempStatus() {

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
