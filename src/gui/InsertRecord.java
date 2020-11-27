/*
 * Created by JFormDesigner on Thu Nov 26 09:43:10 CST 2020
 */

package gui;

import sql.AccountSql;
import util.OneMatch;
import util.StringUtil;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Agonsle
 */
public class InsertRecord extends JFrame {
    public InsertRecord(int server, int accountId) {
        this.server = server;
        this.accountId = accountId;
        initComponents();
    }
    private void insert(){
        OneMatch match = new OneMatch();
        String hero = heroTextField.getText();
        if (StringUtil.isEmpty(hero)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield hero","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setHero(hero);
        String startTime  =startTimeTextField.getText();
        if (StringUtil.isEmpty(startTime)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield start time","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setStartTime(StringUtil.getTimeFromSql(startTime));
        String endTime  =endTimeTextField.getText();
        if (StringUtil.isEmpty(endTime)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield end time","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setEndTime(StringUtil.getTimeFromSql(endTime));
        
        String  heroPointStr = heroPointTextField.getText();
        if (StringUtil.isEmpty(heroPointStr)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield hero point","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setHeroPoint(Double.parseDouble(heroPointStr));

        String  kill = killTextField.getText();
        if (StringUtil.isEmpty(kill)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield kill","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setKill(Integer.parseInt(kill));

        String  death = deathTextField.getText();
        if (StringUtil.isEmpty(death)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield death","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setDeath(Integer.parseInt(death));

        String  assist = assistTextField.getText();
        if (StringUtil.isEmpty(assist)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield assist","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setAssist(Integer.parseInt(assist));
        
        String  rank = rankTextField.getText();
        if (StringUtil.isEmpty(rank)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield rank","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setRank(Integer.parseInt(rank));
        String  tripleKill = tripleKillTextField.getText();
        if (StringUtil.isEmpty(tripleKill)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield triple kill","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setTripleKill(Integer.parseInt(tripleKill));

        String  quadraKill = quadraKillTextField.getText();
        if (StringUtil.isEmpty(quadraKill)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield quadra kill","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setQuadraKill(Integer.parseInt(quadraKill));
        String  rampage = rampageTextField.getText();
        if (StringUtil.isEmpty(rampage)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield rampage","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setRampage(Integer.parseInt(rampage));

        String  compliment = complimentTextField.getText();
        if (StringUtil.isEmpty(compliment)){
            JOptionPane.showMessageDialog(this,"please fill the blank textfield compliment","No Blank Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        match.setCompliment(Integer.parseInt(compliment));
        boolean mvp = mvpComboBox.getSelectedIndex()==0?true:false;// true -> mvp, false -> not mvp
        match.setMvp(mvp);
        boolean result = resultComboBox.getSelectedIndex()==0?true:false; // true -> win, false -> lose
        match.setResult(result);
        boolean escape = escapeComboBox.getSelectedIndex()==0?false:true; // true -> escape , false -> not escape
        match.setEscape(escape);

        // now start insert
        AccountSql accountSql = new AccountSql();
        boolean isInserted = accountSql.insertMatch(server,accountId,match);
        if (isInserted){
            JOptionPane.showMessageDialog(this,"insert successfully","SQL Return",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        else {
            JOptionPane.showMessageDialog(this,"failed to insert","SQL Return",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void insertButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        insert( );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        label1 = new JLabel();
        heroTextField = new JTextField();
        resultLabel = new JLabel();
        resultComboBox = new JComboBox<>();
        startTimeLabel = new JLabel();
        startTimeTextField = new JTextField();
        endTimeLabel = new JLabel();
        endTimeTextField = new JTextField();
        heroPointTextField = new JTextField();
        killTextField = new JTextField();
        deathTextField = new JTextField();
        assistTextField = new JTextField();
        rankTextField = new JTextField();
        tripleKillTextField = new JTextField();
        heroPointLabel = new JLabel();
        killLabel = new JLabel();
        deathLabel = new JLabel();
        assistLabel = new JLabel();
        rankLabel = new JLabel();
        tripleKillLabel = new JLabel();
        quadraKillLabel = new JLabel();
        quadraKillTextField = new JTextField();
        rampageLabel = new JLabel();
        rampageTextField = new JTextField();
        complimentLabel = new JLabel();
        complimentTextField = new JTextField();
        mvpLabel = new JLabel();
        mvpComboBox = new JComboBox<>();
        escapeLabel = new JLabel();
        escapeComboBox = new JComboBox<>();
        insertButton = new JButton();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("hero");

        //---- resultLabel ----
        resultLabel.setText("result");

        //---- resultComboBox ----
        resultComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "win",
            "lose"
        }));

        //---- startTimeLabel ----
        startTimeLabel.setText("start time");

        //---- endTimeLabel ----
        endTimeLabel.setText("end time");

        //---- heroPointLabel ----
        heroPointLabel.setText("hero point");

        //---- killLabel ----
        killLabel.setText("kill");

        //---- deathLabel ----
        deathLabel.setText("death");

        //---- assistLabel ----
        assistLabel.setText("assist");

        //---- rankLabel ----
        rankLabel.setText("rank");

        //---- tripleKillLabel ----
        tripleKillLabel.setText("triple kill");

        //---- quadraKillLabel ----
        quadraKillLabel.setText("quadra kill");

        //---- rampageLabel ----
        rampageLabel.setText("rampage");

        //---- complimentLabel ----
        complimentLabel.setText("compliment");

        //---- mvpLabel ----
        mvpLabel.setText("mvp");

        //---- mvpComboBox ----
        mvpComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "yes",
            "no"
        }));

        //---- escapeLabel ----
        escapeLabel.setText("escape");

        //---- escapeComboBox ----
        escapeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "no",
            "yes"
        }));

        //---- insertButton ----
        insertButton.setText("insert");
        insertButton.addActionListener(e -> insertButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(endTimeLabel)
                                .addComponent(startTimeLabel)
                                .addComponent(heroPointLabel)
                                .addComponent(killLabel)
                                .addComponent(deathLabel)
                                .addComponent(assistLabel)
                                .addComponent(rankLabel)
                                .addComponent(tripleKillLabel))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tripleKillTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(rankTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(assistTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(deathTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(killTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(heroPointTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                        .addComponent(endTimeTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addComponent(startTimeTextField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(quadraKillLabel)
                                .addComponent(rampageLabel)
                                .addComponent(complimentLabel)
                                .addComponent(mvpLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(mvpComboBox, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(complimentTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(rampageTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(quadraKillTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(resultLabel)
                                .addComponent(escapeLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(heroTextField, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(resultComboBox, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(escapeComboBox, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                        .addComponent(insertButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(78, 78, 78))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(insertButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(heroTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(startTimeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(endTimeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(heroPointTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(killTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(assistTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rankTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tripleKillTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(startTimeLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(endTimeLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(heroPointLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(killLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deathLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(assistLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rankLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tripleKillLabel)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(quadraKillLabel)
                        .addComponent(quadraKillTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(rampageLabel)
                        .addComponent(rampageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(complimentLabel)
                        .addComponent(complimentTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mvpLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(mvpComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(resultLabel)
                        .addComponent(resultComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(escapeLabel)
                        .addComponent(escapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JLabel label1;
    private JTextField heroTextField;
    private JLabel resultLabel;
    private JComboBox<String> resultComboBox;
    private JLabel startTimeLabel;
    private JTextField startTimeTextField;
    private JLabel endTimeLabel;
    private JTextField endTimeTextField;
    private JTextField heroPointTextField;
    private JTextField killTextField;
    private JTextField deathTextField;
    private JTextField assistTextField;
    private JTextField rankTextField;
    private JTextField tripleKillTextField;
    private JLabel heroPointLabel;
    private JLabel killLabel;
    private JLabel deathLabel;
    private JLabel assistLabel;
    private JLabel rankLabel;
    private JLabel tripleKillLabel;
    private JLabel quadraKillLabel;
    private JTextField quadraKillTextField;
    private JLabel rampageLabel;
    private JTextField rampageTextField;
    private JLabel complimentLabel;
    private JTextField complimentTextField;
    private JLabel mvpLabel;
    private JComboBox<String> mvpComboBox;
    private JLabel escapeLabel;
    private JComboBox<String> escapeComboBox;
    private JButton insertButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private int  server;
    private int accountId;
}
