/*
 * Created by Agonsle on Thu Nov 26 23:45:29 CST 2020
 */

package gui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;

/**
 * @author Agonsle
 */
public class Plot extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Plot(null).setVisible(true);
            }
        });
    }
    public Plot(String fileName) {
		this.fileName = fileName;
        //setLogo();
        setBackGround();
        initComponents();
    }
    private void setBackGround(){
        Icon icon = new ImageIcon("img\\output\\"+ fileName);
        JLabel bgLabel = new JLabel(icon);
        bgLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        this.getLayeredPane().add(bgLabel,Integer.MIN_VALUE);
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        bgLabel.setOpaque(false);
    }
    private void setLogo(){
        Icon icon = new ImageIcon("img\\logo(1).png");
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(70,80,icon.getIconWidth(),icon.getIconHeight());
        this.getLayeredPane().add(logoLabel,Integer.MAX_VALUE);
        JPanel j  = (JPanel) this.getContentPane();
        j.setOpaque(false);
        logoLabel.setOpaque(false);

    }
    private void linkToLoginLabelKeyTyped(KeyEvent e) {
        // TODO add your code here
        if ('l' == e.getKeyChar()){
            this.dispose();
            new Login().setVisible(true);
        }
    }

    private void button1KeyTyped(KeyEvent e) {
        // TODO add your code here
        if ('l' == e.getKeyChar()){
            this.dispose();
            new Login().setVisible(true);
        }

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new Login().setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        button1 = new JButton();

        //======== this ========
        setTitle("Plot");
        setIconImage(new ImageIcon(getClass().getResource("/icon/chart-scatter-plot.png")).getImage());
        setResizable(true);
        var contentPane = getContentPane();

        //---- button1 ----
        button1.setText("l");
        button1.setSelected(true);
        button1.setOpaque(false);
        button1.addActionListener(e -> button1ActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 526, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 364, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
	private String fileName;
}
