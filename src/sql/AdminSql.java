package sql;

import user.Admin;
import util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class AdminSql extends Sql {
    public Admin getAdmin(String id,String password){
        // search table admin
        // model
//        return new Admin("1213","luozi");
        // if wrong return null
        String selectSql = "select * from admin where id=? and password = ?";
        Admin admin = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                admin = new Admin(id,password);
            }
            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return admin;
    }
    public HashMap<Integer,String> findAccountsInDistrict(int server){
//        String tableName = "server_"+ server;
//        List<Account> accounts = new LinkedList<>();
//        // search table server_num and return a list of Account
        // return List<HashMap<Integer,String>>
        HashMap<Integer,String> rtnMap= new HashMap<>();
        String selectSql = "select account_id and player_id from server_"+server; // select all the records in the database
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int accountId= resultSet.getInt("account_id");
                String playerId = resultSet.getString("player_id");
                rtnMap.put(accountId,playerId);
            }
            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (rtnMap.size() == 0) return null;
        return rtnMap;
//        // if not found return null
//        return null;
    }
    public HashMap<Integer,String> findAccountsInDistrict(String searchText,int server){
        // return List<HashMap<Integer,String>>
        HashMap<Integer,String> rtnMap= new HashMap<>();
        String selectSql = "select * from server_"+ server +" where account_id like '%" + searchText + "%' or " +
                "player_id like '%" + searchText + "%'" ;
        try{
            PreparedStatement preparedStatement  = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                rtnMap.put(resultSet.getInt("account_id"),resultSet.getString("player_id"));
            }
            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        // if not found return null
        // return null;
        if(rtnMap.size() == 0) return null;
        return rtnMap;
    }
    public boolean deleteAccount(int server,int accountId, String playerId){
        // need to update the table player and server_num,
        // need to delete the table server_num1_account_num_match and server_num_account_num_hero
        String selectSql = "select server_and_account from player where id = ?";

        String updateSql1 = "update player set server_and_account =? where id = ?";
        String updateSql2 = "delete from server_"+ server + " where account_id = ?";
        String deleteSql1 = "drop table server_"+ server +  "_account_" + accountId +"_match";
        String deleteSql2 = "drop table server_"+ server +  "_account_" + accountId +"_hero";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1,playerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String serverAndAccount = resultSet.getString("server_and_account");
            List<String> pair = StringUtil.seperateString(serverAndAccount,',');
            String checkStr = server +":" + accountId;
            for (String p: pair){
                if (checkStr.equals(p))
                    pair.remove(p);
            }
            String newServerAndAccount = StringUtil.mergeString(pair,',');
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql1);
            preparedStatement1.setString(2,playerId);
            preparedStatement1.setString(1,newServerAndAccount);
            int updateResult = preparedStatement1.executeUpdate();
            if (updateResult <=0) {
                closeConnection();
                return false;
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(updateSql2);
            preparedStatement2.setInt(1,accountId);
            if (preparedStatement.executeUpdate()<=0){
                closeConnection();
                return false;
            }
            PreparedStatement preparedStatement3 = connection.prepareStatement(deleteSql1);
            if (!preparedStatement3.execute()){
                closeConnection();
                return false;
            }
            PreparedStatement preparedStatement4 = connection.prepareStatement(deleteSql2);
            if (!preparedStatement4.execute()){
                closeConnection();
                return false;
            }

            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
