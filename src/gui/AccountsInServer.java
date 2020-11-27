/*
 * Created by LZR on Wed Nov 25 09:38:17 CST 2020
 */

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import sql.AdminSql;
import user.Admin;
import util.Account;
import util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.*;

/**
 * @author LZR
 */
public class AccountsInServer extends JFrame {
    public AccountsInServer(HashMap<Integer,String> map,int server) {
        this.map = map;
        this.server = server;
        initComponents();
        init();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    private void init(){
        setInfoTable();
        selectedItem = -1;// the first line of the table is 0, so we use -1 to represent the illegal case
    }
    private void setInfoTable(){
        DefaultTableModel dtm = (DefaultTableModel) infoTable.getModel();
        dtm.setRowCount(0);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int  distinctID = (int )entry.getKey();
            String playerId = (String) entry.getValue();
            Vector v = new Vector();
            v.add(distinctID);
            v.add(playerId);
            dtm.addRow(v);
        }
        infoTable.setModel(dtm);
    }

    private void infoTableMouseClicked(MouseEvent e) {
        // TODO add your code here
        selectedItem = infoTable.getSelectedRow();
        if (selectedItem == -1) return;
        selectedAccountId = (int )infoTable.getValueAt(selectedItem,0);
        selectedPlayerId = (String )infoTable.getValueAt(selectedItem,1);
        System.out.println(selectedAccountId +" " + selectedPlayerId);
    }

    private void editButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
//        List<Object> list = new LinkedList<>();
//        list.add(server);// the server id
//        list.add(selectedAccountId);
//        list.add(selectedPlayerId);
//        new AdminEdit(list).setVisible(true);// do not use selectedItem we use the unique item accountId to edit it
        if (StringUtil.isEmpty(selectedPlayerId)){
            JOptionPane.showMessageDialog(this,"please select a legal account to delete","Unselect Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int choice = JOptionPane.showConfirmDialog( this,"the account deleted can not be recovered","Delete Confirm",JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION){
            AdminSql  adminSql = new AdminSql();
            boolean isDeleted = adminSql.deleteAccount(server,selectedAccountId,selectedPlayerId);
            if (isDeleted){
                JOptionPane.showMessageDialog(this,"Delete Successfully","SQL Return",JOptionPane.PLAIN_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(this,"Failed to delete","SQL Return",JOptionPane.PLAIN_MESSAGE);
                return;
            }
        }

    }
    void setTable(String searchText){
        if (StringUtil.isEmpty(searchText)){
            setInfoTable();
            return;
        }

        DefaultTableModel dtm = (DefaultTableModel) infoTable.getModel();
        dtm.setRowCount(0);
        AdminSql adminSql = new AdminSql();
        HashMap<Integer,String> tmpMap = adminSql.findAccountsInDistrict(searchText,server);
        Iterator iterator = tmpMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int  distinctID = (int )entry.getKey();
            String playerId = (String) entry.getValue();
            Vector v = new Vector();
            v.add(distinctID);
            v.add(playerId);
            dtm.addRow(v);
        }
        infoTable.setModel(dtm);
    }
    private void searchButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String searchText = searchTextField.getText();
        setTable(searchText);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        new InsertRecord(server,selectedAccountId).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        scrollPane1 = new JScrollPane();
        infoTable = new JTable();
        editButton = new JButton();
        searchTextField = new JTextField();
        searchButton = new JButton();
        button1 = new JButton();

        //======== this ========
        setResizable(false);
        setTitle("Server Introduction");
        setIconImage(new ImageIcon(getClass().getResource("/icon/server.png")).getImage());
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- infoTable ----
            infoTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", null},
                    {null, null},
                },
                new String[] {
                    "account_id", "player_id"
                }
            ));
            infoTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    infoTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(infoTable);
        }

        //---- editButton ----
        editButton.setText("delete this account");
        editButton.setIcon(new ImageIcon(getClass().getResource("/icon/delete.png")));
        editButton.addActionListener(e -> editButtonActionPerformed(e));

        //---- searchButton ----
        searchButton.setText("search");
        searchButton.setIcon(new ImageIcon(getClass().getResource("/icon/Search.png")));
        searchButton.addActionListener(e -> searchButtonActionPerformed(e));

        //---- button1 ----
        button1.setText("insert new record into this account");
        button1.setIcon(new ImageIcon(getClass().getResource("/icon/Search.png")));
        button1.addActionListener(e -> button1ActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(73, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(editButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                    .addGap(46, 46, 46))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton)
                        .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(editButton))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1)
                    .addContainerGap(39, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JScrollPane scrollPane1;
    private JTable infoTable;
    private JButton editButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private HashMap<Integer,String> map;
    private int selectedItem;// selectedItem
    private int selectedAccountId;
    private String  selectedPlayerId;
    private int server;
}
