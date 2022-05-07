/*
 * Created by JFormDesigner on Tue Apr 12 00:04:06 TRT 2022
 */

package views;

import java.awt.event.*;

import models.ArchiveImpl;
import models.UserImpl;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends Base {
    ArchiveImpl archiveImpl= new ArchiveImpl();
    public Archive() {
        initComponents();
        lblName.setText("Sn "+ UserImpl.name);
        tblServiceArchive.setModel(archiveImpl.serviceArchiveTable(null));
    }

    private void thisWindowClosed(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String txtSearch=txtSearchServiceArchive.getText().toLowerCase(Locale.ROOT).trim();
        tblServiceArchive.setModel(archiveImpl.serviceArchiveTable(txtSearch));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        scrollPane1 = new JScrollPane();
        tblServiceArchive = new JTable();
        txtSearchServiceArchive = new JTextField();
        label3 = new JLabel();

        //======== this ========
        setResizable(false);
        setTitle("Products Archive");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/ArchivePage.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblServiceArchive);
        }

        //---- txtSearchServiceArchive ----
        txtSearchServiceArchive.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearchKeyReleased(e);
            }
        });

        //---- label3 ----
        label3.setText("Service Search");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(163, 163, 163)
                    .addComponent(label3)
                    .addGap(18, 18, 18)
                    .addComponent(txtSearchServiceArchive, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 530, Short.MAX_VALUE)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearchServiceArchive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JScrollPane scrollPane1;
    private JTable tblServiceArchive;
    private JTextField txtSearchServiceArchive;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
