/*
 * Created by JFormDesigner on Tue Nov 24 13:57:19 CST 2020
 */

package gui;

import java.awt.event.*;
import user.Player;
import util.Account;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;

/**
 * @author Agonsle
 */
public class PlayerMain extends JFrame {
    public PlayerMain(Player player) {
        this.player = player;
        initComponents();
        setChooseAccountComboBox();
//        init();
    }
//    private void init(){
//        imgPanel.setSize();
//    }
    private void setChooseAccountComboBox(){
        java.util.List<Account> accounts = this.player.getAccounts();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) chooseAccountComboBox.getModel();
        for (Account account : accounts)
            dcm.addElement(account);
        chooseAccountComboBox.setModel(dcm);

    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerMain(new Player("1234","1234")).setVisible(true);
            }
        });
    }
    private void setIcon (String imgPath){
        Icon icon = new ImageIcon("img\\"+imgPath);
        imgLabel.setIcon(icon);
        imgLabel.setText("");
    }

    private void chooseAccountComboBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        selectedItem = (Account)chooseAccountComboBox.getSelectedItem();
        setIcon(selectedItem.getImgPath());
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        int choice = JOptionPane.showConfirmDialog(this,"Are you sure to exit the system?","Confirm",JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    private void login(){
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this,"please choose an account of you","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.dispose();
        new PlayerRecord(selectedItem).setVisible(true);
    }
    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        login();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        imgPanel = new JPanel();
        imgLabel = new JLabel();
        chooseAccountLabel = new JLabel();
        chooseAccountComboBox = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== imgPanel ========
        {
            imgPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
            .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
            .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
            Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),imgPanel. getBorder()));imgPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
            public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
            )))throw new RuntimeException();}});

            //---- imgLabel ----
            imgLabel.setText("img");

            GroupLayout imgPanelLayout = new GroupLayout(imgPanel);
            imgPanel.setLayout(imgPanelLayout);
            imgPanelLayout.setHorizontalGroup(
                imgPanelLayout.createParallelGroup()
                    .addGroup(imgPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(imgLabel)
                        .addContainerGap(39, Short.MAX_VALUE))
            );
            imgPanelLayout.setVerticalGroup(
                imgPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, imgPanelLayout.createSequentialGroup()
                        .addContainerGap(43, Short.MAX_VALUE)
                        .addComponent(imgLabel)
                        .addGap(39, 39, 39))
            );
        }

        //---- chooseAccountLabel ----
        chooseAccountLabel.setText("please choose your district and nickname:");

        //---- chooseAccountComboBox ----
        chooseAccountComboBox.addActionListener(e -> chooseAccountComboBoxActionPerformed(e));

        //---- button1 ----
        button1.setText("select this account");
        button1.addActionListener(e -> button1ActionPerformed(e));

        //---- button2 ----
        button2.setText("exit");
        button2.addActionListener(e -> button2ActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(241, 241, 241)
                            .addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(chooseAccountLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(chooseAccountComboBox, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseAccountLabel)
                        .addComponent(chooseAccountComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button2))
                    .addContainerGap(96, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JPanel imgPanel;
    private JLabel imgLabel;
    private JLabel chooseAccountLabel;
    private JComboBox chooseAccountComboBox;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Player player;
    Account selectedItem;
}
