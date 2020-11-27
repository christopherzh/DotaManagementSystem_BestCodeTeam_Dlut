package gui;
import plt.Plot;
import sql.AccountSql;
import user.Player;
import util.Account;
import util.Hero;
import util.OneMatch;
import util.Server;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
/*
 * Created by LZR on Tue Nov 24 12:56:27 CST 2020
 */



/**
 * @author LZR
 */
public class PlayerRecord extends JFrame {
    public PlayerRecord(Account account) {
//        this.player = player;
        this.account = account;
        initMap();
        initComponents();
        init();
    }
    void initMap(){
        map = new HashMap<>();
        map.put(1,"result");
        map.put(5,"rank");
    }
    private void plot(){
        plt.Plot plot = new Plot();
        int cap= matchList.size();
        String fileName= "";
        List<String> stringList = new LinkedList<>();
        double tmpSum =0.0;
        for (int i=1;i<=cap;++i)
            stringList.add(Integer.toString(i));
        List<Double> doubleList = new LinkedList<>();
        if (selectedItemToBePlot == 1){
            for (OneMatch match : matchList){
                doubleList.add((double)(match.getResult()?1:0));
            }
            fileName = "result";
        }
        else if (selectedItemToBePlot == 5){
            for (OneMatch match : matchList){
                tmpSum += match.getRank();
                doubleList.add(tmpSum);
            }
            fileName = "rank";
        }
        plot.plot(stringList,doubleList,fileName,"Line Chart","session",map.get(selectedItemToBePlot));
    }
    private void init(){
        setImgLabel();
        accountNicknameLabel.setText(account.getNickname());
        districtLabel.setText(Server.values()[account.getServer()-1].toString());
        levelLabel.setText("Lv."+account.getLevel());
        noLabel.setText("No." + account.getNumber());
        setView_list_table();
        setView_list_table2();
        setGame_list_views();
        setTable1();
//        System.out.println(this.account);
    }
    private void setTable1(){
        // search from account_num_hero
        // using List<Hero> to transfer the parameter
        AccountSql accountSql = new AccountSql();
        List<Hero> heroList = accountSql.getHeroesOfThisAccount(account.getServer(),account.getAccountId(),null);
        this.account.setHeroes(heroList);
        if (heroList== null) return;
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        for(Hero hero: heroList){
            Vector v = new Vector();
            v.add(hero.getHeroName());
            v.add(hero.winRate());
            v.add(hero.getTotalGames());
            v.add(hero.K_D_A());
            v.add(hero.getTotalHeroPoints());
            v.add(hero.getHeroRank());
            dtm.addRow(v);
        }
        table1.setModel(dtm);
    }
    private void setGame_list_views(){
        // search from account_num_record
        // using List<OneMatch> to transfer the parameter
        AccountSql accountSql = new AccountSql();
        matchList = accountSql.getMatchesOfThisAccount(account.getServer(),account.getAccountId());
        this.account.setMatches(matchList);
        if (matchList == null) return;
        DefaultTableModel dtm = (DefaultTableModel) game_list_views.getModel();
        dtm.setRowCount(0);
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
        dtm.addRow(v);
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
        dtm.addRow(v);
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

    private void game_list_viewsMouseClicked(MouseEvent e) {
        // TODO add your code here
        selectedItemToBePlot = game_list_views.getSelectedColumn();
        System.out.println(selectedItemToBePlot);
    }

    private void plotButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String message = "Sorry for that we do not support fot plot this column temporary";
        if (!(selectedItemToBePlot==1|| selectedItemToBePlot==5)){
            JOptionPane.showMessageDialog(this,message,"Tips",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        plot();
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
        noLabel = new JLabel();
        plotButton = new JButton();

        //======== this ========
        setTitle("My Record");
        setIconImage(new ImageIcon(getClass().getResource("/icon/analyze.png")).getImage());
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
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
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
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "heroes", "result", "game_time", "hero_point", "K/D/A", "rank"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            game_list_views.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game_list_viewsMouseClicked(e);
                }
            });
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
                },
                new String[] {
                    "hero", "win_rates", "total_games", "K/D/A", "total_hero_points", "hero_rank"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
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
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            view_list2.setViewportView(view_list_table2);
        }

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(150, 150));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(124, Short.MAX_VALUE)
                        .addComponent(imgLabel)
                        .addGap(26, 26, 26))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(114, Short.MAX_VALUE)
                        .addComponent(imgLabel)
                        .addGap(36, 36, 36))
            );
        }

        //---- accountNicknameLabel ----
        accountNicknameLabel.setText("accountNickname");
        accountNicknameLabel.setFont(accountNicknameLabel.getFont().deriveFont(accountNicknameLabel.getFont().getSize() + 24f));

        //---- districtLabel ----
        districtLabel.setText("district");

        //---- levelLabel ----
        levelLabel.setText("level");

        //---- noLabel ----
        noLabel.setText("No.");

        //---- plotButton ----
        plotButton.setText("plot");
        plotButton.setIcon(new ImageIcon(getClass().getResource("/icon/scatter_plot.png")));
        plotButton.addActionListener(e -> plotButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(common_heros)
                                .addComponent(recent_games)
                                .addComponent(game_list, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 43, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(50, 50, 50)
                                            .addComponent(accountNicknameLabel, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(64, 64, 64)
                                            .addComponent(districtLabel)
                                            .addGap(59, 59, 59)
                                            .addComponent(levelLabel)
                                            .addGap(62, 62, 62)
                                            .addComponent(noLabel)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(plotButton)
                                            .addGap(14, 14, 14))))
                                .addComponent(view_list, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                                .addComponent(view_list2, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                            .addContainerGap(43, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(accountNicknameLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(districtLabel)
                                        .addComponent(levelLabel)
                                        .addComponent(noLabel))
                                    .addGap(67, 67, 67))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(plotButton)
                                    .addGap(38, 38, 38))))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
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
                    .addContainerGap())
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
    private JLabel noLabel;
    private JButton plotButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Account account;
//    private Player player;
    private int selectedItemToBePlot;
    private HashMap<Integer,String> map;
    java.util.List<OneMatch> matchList;
}
