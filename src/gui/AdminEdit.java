/*
 * Created by Agonsle on Wed Nov 25 10:08:04 CST 2020
 */

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.List;

/**
 * @author Agonsle
 */
public class AdminEdit extends JFrame {
    public AdminEdit(List<Object> list) {
        this.accountId = (int) list.get(1);
        this.server = (int)list.get(0);
        this.playerId = (String) list.get(2);
        initComponents();
        init();
    }
    private void init(){
        accountIdTextField.setText(Integer.toString(accountId));
        playerIdTextField.setText(playerId);
        serverTextField.setText(Integer.toString(server));
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String message = "edit the textfield and click the confirm edit button\nkeep the textfield blank can delete the record";
        JOptionPane.showMessageDialog(this,message,"Notification",JOptionPane.PLAIN_MESSAGE);
    }

    private void confirmButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        int choice = JOptionPane.showConfirmDialog(this,"Are you sure to edit this record?","Confirm",JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        // confirm to edit this record
//        if(accountId == Integer.parseInt(accountIdTextField.getText()) && server)
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        accountIdTextField = new JTextField();
        accountIdLabel = new JLabel();
        playerIdLabel = new JLabel();
        playerIdTextField = new JTextField();
        serverLabel = new JLabel();
        serverTextField = new JTextField();
        button1 = new JButton();
        confirmButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- accountIdLabel ----
        accountIdLabel.setText("accountId:");

        //---- playerIdLabel ----
        playerIdLabel.setText("playerId:");

        //---- serverLabel ----
        serverLabel.setText("server:");

        //---- button1 ----
        button1.setText("help");
        button1.addActionListener(e -> button1ActionPerformed(e));

        //---- confirmButton ----
        confirmButton.setText("confirm edit");
        confirmButton.addActionListener(e -> confirmButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup()
                            .addComponent(playerIdLabel)
                            .addComponent(accountIdLabel)
                            .addComponent(serverLabel))
                        .addComponent(button1, GroupLayout.Alignment.TRAILING))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(accountIdTextField, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(playerIdTextField, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(serverTextField, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addComponent(confirmButton))
                    .addGap(84, 84, 84))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accountIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(accountIdLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(playerIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerIdLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(serverTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(serverLabel))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmButton)
                        .addComponent(button1))
                    .addContainerGap(44, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JTextField accountIdTextField;
    private JLabel accountIdLabel;
    private JLabel playerIdLabel;
    private JTextField playerIdTextField;
    private JLabel serverLabel;
    private JTextField serverTextField;
    private JButton button1;
    private JButton confirmButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private int accountId;// this is the id of this account in this server
    private int server;
    private String playerId;
}
