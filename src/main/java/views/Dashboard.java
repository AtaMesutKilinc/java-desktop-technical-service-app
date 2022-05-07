/*
 * Created by JFormDesigner on Wed Apr 06 17:15:25 TRT 2022
 */

package views;

import java.awt.event.*;
import javax.swing.border.*;

import models.DashboardImpl;
import models.UserImpl;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    DashboardImpl dashboardImpl= new DashboardImpl();
    public Dashboard() {
        initComponents();
        lblName.setText("Sayın "+UserImpl.name);
        tblRepaired.setModel(dashboardImpl.serviceRepairedTable(null));
        tblArrived.setModel(dashboardImpl.serviceArrivedTable(null));

    }

    private void btnCustomerAddClick(ActionEvent e) {
        CustomerAdd customerAdd= new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();

    }

    private void btnServiceAddClick(ActionEvent e) {
        Services services=new Services();
        services.setVisible(true);
        dispose();
    }

    private void btnArchiveClick(ActionEvent e) {
        Archive archive= new Archive();
        archive.setVisible(true);
        dispose();
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String search=txtSearch.getText().toLowerCase(Locale.ROOT).trim();
        tblArrived.setModel(dashboardImpl.serviceArrivedTable(search));
        tblRepaired.setModel(dashboardImpl.serviceRepairedTable(search));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        tblArrived = new JTable();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblRepaired = new JTable();
        panel1 = new JPanel();
        btnCustomerAdd = new JButton();
        label2 = new JLabel();
        btnServiceAdd = new JButton();
        txtSearch = new JTextField();
        btnArchive = new JButton();
        lblName = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/DashboardPage.png")).getImage());
        setTitle("Tecnical Service");
        Container contentPane = getContentPane();

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, null, null, null), "Incoming Product", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            panel2.setFont(new Font("Arial", Font.PLAIN, 14));
            panel2.setBackground(Color.lightGray);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(tblArrived);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, null, null, null), "Repaired Product", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            panel3.setBackground(Color.lightGray);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tblRepaired);
            }

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1)
                        .addContainerGap())
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 172, 238));

            //---- btnCustomerAdd ----
            btnCustomerAdd.setIcon(new ImageIcon(getClass().getResource("/AddCustomerBtn.png")));
            btnCustomerAdd.setToolTipText("Customer Add");
            btnCustomerAdd.setFocusable(false);
            btnCustomerAdd.setForeground(new Color(0, 172, 238));
            btnCustomerAdd.addActionListener(e -> btnCustomerAddClick(e));

            //---- label2 ----
            label2.setText("Search");

            //---- btnServiceAdd ----
            btnServiceAdd.setIcon(new ImageIcon(getClass().getResource("/Service.png")));
            btnServiceAdd.setToolTipText("Service Add");
            btnServiceAdd.setFocusable(false);
            btnServiceAdd.addActionListener(e -> btnServiceAddClick(e));

            //---- txtSearch ----
            txtSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSearchKeyReleased(e);
                }
            });

            //---- btnArchive ----
            btnArchive.setIcon(new ImageIcon(getClass().getResource("/archiveIcon.png")));
            btnArchive.setToolTipText("Archive");
            btnArchive.setFocusable(false);
            btnArchive.addActionListener(e -> btnArchiveClick(e));

            //---- lblName ----
            lblName.setText(" ");
            lblName.setHorizontalAlignment(SwingConstants.RIGHT);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 227, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                                .addGap(223, 223, 223))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnCustomerAdd)
                                .addGap(31, 31, 31)
                                .addComponent(btnServiceAdd)
                                .addGap(31, 31, 31)
                                .addComponent(btnArchive)
                                .addGap(0, 220, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnServiceAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(panel3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 1, Short.MAX_VALUE))
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JTable tblArrived;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTable tblRepaired;
    private JPanel panel1;
    private JButton btnCustomerAdd;
    private JLabel label2;
    private JButton btnServiceAdd;
    private JTextField txtSearch;
    private JButton btnArchive;
    private JLabel lblName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
