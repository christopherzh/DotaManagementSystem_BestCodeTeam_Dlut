/*
 * Created by Agonsle on Tue Nov 24 10:22:06 CST 2020
 */

package gui;

import java.awt.event.*;

import sql.AdminSql;
import sql.PlayerSql;
import user.Admin;
import user.Player;
import user.UserType;
import util.MPanel;
import util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;

/**
 * @author Agonsle
 */
public class Login extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Login login = new Login();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public Login() {
        initComponents();
        setIdentifyComboBox();
//        Start start = new Start();
//        this.add(start);
//        MPanel pan=new MPanel(this.getClass().getResource(
//                "img\\1.png"));
//        pan.setLayout(new GridBagLayout());
//        this.getContentPane().add(pan,BorderLayout.CENTER);
//        setBackGroundImg("img\\1.png");
//        setBackGround();
        setVisible(true);
    }
    private void setBackGround(Container container){
        Icon icon = new ImageIcon("img\\background.jpg");
        JLabel bgLabel = new JLabel(icon);
        bgLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        container.add(bgLabel);
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        bgLabel.setOpaque(false);
    }
    private void setBackGround(){
        Icon icon = new ImageIcon("img\\background.jpg");
        JLabel bgLabel = new JLabel(icon);
        bgLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        this.getLayeredPane().add(bgLabel,Integer.MIN_VALUE);
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        bgLabel.setOpaque(false);
    }
    private void setBackGroundImg(String filePath){
        ImageIcon img =  new ImageIcon(filePath);
        JLabel imgLabel = new JLabel((img));
        imgLabel.setBounds(this.getBounds());
        this.getLayeredPane().add(imgLabel,Integer.MIN_VALUE);
        ((JPanel)this.getContentPane()).setOpaque(false);
    }
    private void setIdentifyComboBox(){
//        String[] nameList = {"PLAYER","ADMIN"};
//        identifyComboBox.setModel(new DefaultComboBoxModel(nameList));
        identifyComboBox.setModel(new DefaultComboBoxModel(new UserType[]{
                UserType.PLAYER,
                UserType.ADMIN,
        }));
    }
    private void setIdLabel(){
        UserType selectedItem = (UserType)identifyComboBox.getSelectedItem();
//        System.out.println(selectedItem.getName());
        String user = "";
        if ("ADMIN".equals(selectedItem.getName()))// the identity of the user is administrator
            user = "admin";
        else if ("PLAYER".equals(selectedItem.getName()))// the identity of the user is player
            user = "player";
        else // wrong case
        JOptionPane.showMessageDialog(this,"please choose a legal user type：","error",JOptionPane.ERROR_MESSAGE);
        user += "ID:";
        idLabel.setText(user);
    }
    private void loginAction(){
        String userId = idTextField.getText();
//        System.out.println(userId);
        // id must be a numeric string
        if (!StringUtil.isNumeric(userId)){
            JOptionPane.showMessageDialog(this,"ID must be numeric string","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userId.length()>10){
            JOptionPane.showMessageDialog(this,"the length of id is at most 10","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        char[] passwordChar = passwordField.getPassword();
        String password = new String (passwordChar);
        if (!StringUtil.isLettersAndNumbers(password)){
            JOptionPane.showMessageDialog(this,"password can only consist of letters and numbers","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
//        System.out.println(password);

        // True login part
        UserType selectedItem = (UserType) identifyComboBox.getSelectedItem();
        if ("PLAYER".equals(selectedItem.getName())){
            // player login
            PlayerSql playerSql = new PlayerSql();
            Player player = playerSql.getPlayer(userId,password);// if not found return null
            if (player== null) {
                // Error
                JOptionPane.showMessageDialog(this, "The username and password do not match", "Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = "Welcome【"+selectedItem.getName()+"】 "+player.getId()+" login";
            JOptionPane.showMessageDialog(this,message,"Welcome",JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            new PlayerMain(player).setVisible(true);

        }
        else if ("ADMIN".equals(selectedItem.getName())){
            // admin login
            AdminSql adminSql = new AdminSql();
            Admin admin = adminSql.getAdmin(userId,password);
            if (admin == null){
                // Error
                JOptionPane.showMessageDialog(this, "The username and password do not match", "Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = "Welcome【"+selectedItem.getName()+"】 "+admin.getId()+" login";
            JOptionPane.showMessageDialog(this,message,"Welcome",JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            new AdminMain(admin).setVisible(true);
        }
    }
    private void identifyComboBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        setIdLabel();
    }
    private void loginButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        loginAction();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        titleLabel = new JLabel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        passwordLabel = new JLabel();
        loginButton = new JButton();
        identityLabel = new JLabel();
        identifyComboBox = new JComboBox();
        passwordField = new JPasswordField();

        //======== this ========
        setTitle("Dota Account Management System");
        setIconImage(new ImageIcon(getClass().getResource("/icon/Login.png")).getImage());
        setResizable(false);
        setForeground(SystemColor.windowText);
        var contentPane = getContentPane();
//        setBackGround(contentPane);
        //---- titleLabel ----
        titleLabel.setText("Dota Account Management System");
        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getSize() + 12f));

        //---- idLabel ----
        idLabel.setText("playerID:");
        idLabel.setIcon(new ImageIcon(getClass().getResource("/icon/ID.png")));

        //---- passwordLabel ----
        passwordLabel.setText("password");
        passwordLabel.setIcon(new ImageIcon(getClass().getResource("/icon/password .png")));

        //---- loginButton ----
        loginButton.setText("login");
        loginButton.setIcon(new ImageIcon(getClass().getResource("/icon/login (1).png")));
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));

        //---- identityLabel ----
        identityLabel.setText("Please choose your identity:");
        identityLabel.setIcon(new ImageIcon(getClass().getResource("/icon/perm-identity.png")));

        //---- identifyComboBox ----
        identifyComboBox.addActionListener(e -> identifyComboBoxActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(74, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(identityLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(identifyComboBox))
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup()
                                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(idLabel)
                                                .addGap(32, 32, 32))
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(passwordLabel)
                                                .addGap(28, 28, 28)))
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(idTextField)
                                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))))
                            .addGap(100, 100, 100))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(identityLabel)
                        .addComponent(identifyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(idLabel)
                        .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(loginButton)
                    .addContainerGap(49, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JLabel titleLabel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel identityLabel;
    private JComboBox identifyComboBox;
    private JPasswordField passwordField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
