package gui;
import com.sun.jdi.event.VMDeathEvent;
import sql.AccountSql;
import user.Player;
import util.Account;
import util.Hero;
import util.OneMatch;
import util.Server;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Tue Nov 24 12:56:27 CST 2020
 */



/**
 * @author Agonsle
 */
public class PlayerRecord extends JFrame {
    public PlayerRecord(Account account) {
//        this.player = player;
        this.account = account;
        initComponents();
        init();
    }
    private void init(){
        setImgLabel();
        accountNicknameLabel.setText(account.getNickname());
        districtLabel.setText(Server.values()[account.getServer()-1].toString());
        levelLabel.setText("Lv."+account.getLevel());
        setView_list_table();
        setView_list_table2();
        setGame_list_views();
        setTable1();
    }
    private void setTable1(){
        // search from account_num_hero
        // using List<Hero> to transfer the parameter
        AccountSql accountSql = new AccountSql();
        List<Hero> heroList = accountSql.getHeroesOfThisAccount(account.getServer(),account.getAccountId(),null);
        this.account.setHeroes(heroList);
        if (heroList== null) return;
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        for(Hero hero: heroList){
            Vector v = new Vector();
            v.add(hero.getHeroName());
            v.add(hero.winRate());
            v.add(hero.getTotalGames());
            v.add(hero.K_D_A());
            v.add(hero.getTotalHeroPoints());
            v.add(hero.getHeroRank());
        }
        table1.setModel(dtm);
    }
    private void setGame_list_views(){
        // search from account_num_record
        // using List<OneMatch> to transfer the parameter
        AccountSql accountSql = new AccountSql();
        java.util.List<OneMatch> matchList = accountSql.getMatchesOfThisAccount(account.getServer(),account.getAccountId());
        this.account.setMatches(matchList);
        if (matchList == null) return;
        DefaultTableModel dtm = (DefaultTableModel) game_list_views.getModel();
        for (OneMatch match:matchList){
            Vector v = new Vector();
            v.add(match.getHero());
            v.add(match.getResult()?"Victory":"Defeat");
            v.add(match.deltaTime());
            v.add(match.getHeroPoint());
            v.add(match.K_D_A());
            v.add(match.getRank());
            dtm.addRow(v);
        }
        game_list_views.setModel(dtm);

    }
    private  void setView_list_table2(){
        DefaultTableModel dtm = (DefaultTableModel) view_list_table2.getModel();
        dtm.setRowCount(0);
        Vector v = new Vector();
        v.add(account.getVictories());
        v.add(account.getDefeats());
        v.add(account.escapeRate());
        v.add(account.getMvp());
        v.add(account.getRampage());
        v.add(account.getCompliment());
        view_list_table2.setModel(dtm);
    }
    private void setView_list_table(){
        DefaultTableModel dtm = (DefaultTableModel) view_list_table.getModel();
        dtm.setRowCount(0);
        Vector v = new Vector();
        v.add(account.getTotalMatches());
        v.add(account.KDA());
        v.add(account.winRate());
        v.add(account.getTier());
        v.add(account.getTotalRank());
        view_list_table.setModel(dtm);
    }
    private void setImgLabel(){
        Icon icon = new ImageIcon("img\\"+account.getImgPath());
        imgLabel.setIcon(icon);
        imgLabel.setText("");
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerRecord(null).setVisible(true);
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Agonsle
        view_list = new JScrollPane();
        view_list_table = new JTable();
        recent_games = new JLabel();
        game_list = new JScrollPane();
        game_list_views = new JTable();
        common_heros = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        view_list2 = new JScrollPane();
        view_list_table2 = new JTable();
        panel1 = new JPanel();
        imgLabel = new JLabel();
        accountNicknameLabel = new JLabel();
        districtLabel = new JLabel();
        levelLabel = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== view_list ========
        {

            //---- view_list_table ----
            view_list_table.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null},
                },
                new String[] {
                    "total games", "KDA", "win rate", "tier", "rank"
                }
            ));
            {
                TableColumnModel cm = view_list_table.getColumnModel();
                cm.getColumn(0).setResizable(false);
            }
            view_list.setViewportView(view_list_table);
        }

        //---- recent_games ----
        recent_games.setText("temporary matches");

        //======== game_list ========
        {

            //---- game_list_views ----
            game_list_views.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, ""},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "heroes", "result", "game_time", "hero_point", "K/D/A", "rank"
                }
            ));
            game_list.setViewportView(game_list_views);
        }

        //---- common_heros ----
        common_heros.setText("proficient heros");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, "", null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "hero", "win_rates", "total_games", "K/D/A", "total_hero_points", "hero_rank"
                }
            ));
            scrollPane1.setViewportView(table1);
        }

        //======== view_list2 ========
        {

            //---- view_list_table2 ----
            view_list_table2.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "victories", "defeats", "escape rate", "MVP", "rampage", "compliment"
                }
            ));
            view_list2.setViewportView(view_list_table2);
        }

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );

            //---- imgLabel ----
            imgLabel.setText("text");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(54, Short.MAX_VALUE)
                        .addComponent(imgLabel)
                        .addGap(23, 23, 23))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addComponent(imgLabel)
                        .addGap(32, 32, 32))
            );
        }

        //---- accountNicknameLabel ----
        accountNicknameLabel.setText("accountNickname");
        accountNicknameLabel.setFont(accountNicknameLabel.getFont().deriveFont(accountNicknameLabel.getFont().getSize() + 24f));

        //---- districtLabel ----
        districtLabel.setText("district");

        //---- levelLabel ----
        levelLabel.setText("level");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(accountNicknameLabel, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(districtLabel)
                            .addGap(82, 82, 82)
                            .addComponent(levelLabel)))
                    .addContainerGap(141, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(view_list, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                                .addComponent(view_list2, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                            .addContainerGap(28, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)
                                .addComponent(game_list, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)
                                .addComponent(recent_games)
                                .addComponent(common_heros))
                            .addGap(0, 28, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(accountNicknameLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(districtLabel)
                                .addComponent(levelLabel))))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(view_list, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(view_list2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(recent_games)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(game_list, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(common_heros)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Agonsle
    private JScrollPane view_list;
    private JTable view_list_table;
    private JLabel recent_games;
    private JScrollPane game_list;
    private JTable game_list_views;
    private JLabel common_heros;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane view_list2;
    private JTable view_list_table2;
    private JPanel panel1;
    private JLabel imgLabel;
    private JLabel accountNicknameLabel;
    private JLabel districtLabel;
    private JLabel levelLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Account account;
//    private Player player;
}
