/*
 * Created by Agonsle on Tue Nov 24 20:27:07 CST 2020
 */

package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import sql.AdminSql;
import user.Admin;
import util.Account;
import util.Server;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Agonsle
 */
public class AdminMain extends JFrame {
    public AdminMain(Admin admin) {
        this.admin = admin;
        initComponents();
        init();
    }
    private void init(){
        initIdLabel();
        initServerCombobox();
    }
    private void initIdLabel(){
        idLabel.setText("Administrator: "+admin.getId());
    }
    private void initServerCombobox(){
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) districtComboBox.getModel();
        for (Server server: Server.values())
            dcm.addElement(server);
        districtComboBox.setModel(dcm);
    }

    private void districtComboBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        selectedItem =districtComboBox.getSelectedIndex();// selectedItem equals serverId - 1
//        System.out.println(selectedItem);
    }
    private void viewDistrict(){
        AdminSql adminSql = new AdminSql();
        HashMap<Integer,String> map = adminSql.findAccountsInDistrict(selectedItem+1);
        if (map == null){
            // have no accounts or have no this server
            String message = "this server do not exist";
            JOptionPane.showMessageDialog(this,message,"Return Message",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        new AccountsInServer(map,selectedItem+1).setVisible(true);// do not need to dispose this window
    }
    private void viewButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        viewDistrict();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        idLabel = new JLabel();
        districtLabel = new JLabel();
        districtComboBox = new JComboBox();
        viewButton = new JButton();

        //======== this ========
        setTitle("Admin");
        setIconImage(new ImageIcon(getClass().getResource("/icon/admin.png")).getImage());
        var contentPane = getContentPane();

        //---- idLabel ----
        idLabel.setText("text");
        idLabel.setIcon(new ImageIcon(getClass().getResource("/icon/admin (1).png")));

        //---- districtLabel ----
        districtLabel.setText("Please choose the district you want to view: ");

        //---- districtComboBox ----
        districtComboBox.addActionListener(e -> districtComboBoxActionPerformed(e));

        //---- viewButton ----
        viewButton.setText("view this district");
        viewButton.setIcon(new ImageIcon(getClass().getResource("/icon/view.png")));
        viewButton.addActionListener(e -> viewButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(viewButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(districtComboBox, GroupLayout.Alignment.LEADING)
                        .addComponent(districtLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(38, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(idLabel)
                    .addGap(18, 18, 18)
                    .addComponent(districtLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(districtComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(viewButton)
                    .addContainerGap(59, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JLabel idLabel;
    private JLabel districtLabel;
    private JComboBox districtComboBox;
    private JButton viewButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Admin admin;
    private int selectedItem;
}
