package sql;
import user.Player;
import util.Account;
import util.DbUtil;
import util.StringUtil;
import util.Time;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class PlayerSql extends Sql {
    public Player getPlayer(String id,String password){
        // when searching the player, we must search the accounts together, this is predetermined
        // this is only a model
        //p.setAccounts(accounts);// do not use setter for List<Account>, we use constructor
        String selectSql = "select * from player where id = ? and password = ? ";
//        List<String> serverAndAccount = new LinkedList<>();
        String serverAndAccount = "";
        List<Account> accountList = new LinkedList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next())
               // here can only have at most one record
                serverAndAccount = resultSet.getString("server_and_account");
            if (StringUtil.isEmpty(serverAndAccount)) {
                // have no this player or the id and the password mismatched
//                closeConnection();
                return null;
            }
            accountList = getAccountList(serverAndAccount);
//            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        Player player = new Player(id,password,accountList);
        // search from table players -> confirm -> table server_num -> table account
        return player;
    }
    private List<Account> getAccountList(String serverAndAccount ){
        // serverAndAccount format: server1:account1,server2:account2
        List<String> pairs = StringUtil.seperateString(serverAndAccount,',');
        List<Account> accountList = new LinkedList<>();
        for (String  pair:pairs){
            List<String> p = StringUtil.seperateString(pair,':');
            int server = Integer.parseInt(p.get(0));
            int accountId = Integer.parseInt(p.get(1));
            String selectSql = "select * from server_" + server +" where account_id = ? order by rank_game DESC";
            int cnt = 1;// the counter of the Number
            Account account = new Account();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1,accountId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    account.setNumber(cnt++);
                    account.setAccountId(accountId);
                    account.setServer(server);
                    account.setPlayerId(resultSet.getString("player_id"));
                    // Time is stored as string
                    String timeStr = resultSet.getString("time");
//                    List<String> t = StringUtil.seperateString(timeStr,':');
//                    account.setTotalTime(new Time(Integer.parseInt(t.get(0)),Integer.parseInt(t.get(1)),Integer.parseInt(t.get(2))));
                    account.setTotalTime(StringUtil.getTimeFromSql(timeStr));
//                    account.setLevel(resultSet.getInt("level"));
                    account.setScore(resultSet.getInt("rank_game"));
                    account.setImgPath(resultSet.getString("img_path"));
                    account.setNickname(resultSet.getString("nickname"));
                    account.setVictories(resultSet.getInt("victory"));
                    account.setTotalMatches(resultSet.getInt("total_game"));
                    account.setEscape(resultSet.getInt("escape_game"));// it seems that escape is a key word in mysql
                    account.setMvp(resultSet.getInt("mvp"));
                    account.setRampage(resultSet.getInt("rampage"));
                    account.setCompliment(resultSet.getInt("compliment"));
                    account.setKill(resultSet.getInt("kills"));
                    account.setAssist(resultSet.getInt("assist"));
                    account.setDeath(resultSet.getInt("death"));
                    // matches and heroes left, we put it in AccountSql
                    accountList.add(account);
                }
            }catch (Exception e ){
                e.printStackTrace();
            }
            // do not need to close the connection, because we put it outside
        }
        if (accountList.size() == 0)
            return  null;
        return accountList;
    }

}
