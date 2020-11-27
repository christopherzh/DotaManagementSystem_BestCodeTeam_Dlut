package sql;

import util.*;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AccountSql extends Sql {
    public List<OneMatch> getMatchesOfThisAccount(int server,int accountId){
        // search from account_num_record
        // using List<OneMatch> to transfer the parameter
        List<OneMatch> rtnList = new LinkedList<>();
        String selectSql= "select * from server_"+server +"_account_"+accountId+"_match";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                OneMatch match = new OneMatch();
                match.setHero(resultSet.getString("hero"));
                match.setResult(resultSet.getBoolean("result"));
                match.setStartTime(StringUtil.getTimeFromSql(resultSet.getString("start_time")));
                match.setEndTime(StringUtil.getTimeFromSql(resultSet.getString("end_time")));
                match.setHeroPoint(resultSet.getDouble("hero_point"));
                match.setKill(resultSet.getInt("kill"));
                match.setDeath(resultSet.getInt("death"));
                match.setAssist(resultSet.getInt("assist"));
                match.setRank(resultSet.getInt("rank"));
                rtnList.add(match);
            }
            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (rtnList.size() == 0) return null;
        return  rtnList;
    }
    public List<Hero> getHeroesOfThisAccount(int server,int accountId,String heroName){
        // search from account_num_hero
        // using List<Hero> to transfer the parameter
        List<Hero> rtnList = new LinkedList<>();
        String selectSql= "select * from server_"+server +"_account_"+accountId+"_hero";
        if (!StringUtil.isEmpty(heroName))
            selectSql += (" where hero_name = "+heroName);
            try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Hero hero = new Hero();
                hero.setHeroName(resultSet.getString("hero_name"));
                hero.setVictory(resultSet.getInt("victory"));
                hero.setTotalGames(resultSet.getInt("total_game"));
                hero.setKill(resultSet.getInt("kill"));
                hero.setDeath(resultSet.getInt("death"));
                hero.setAssist(resultSet.getInt("assist"));
                hero.setTotalHeroPoints(resultSet.getInt("total_hero_point"));
                hero.setHeroRank( resultSet.getInt("hero_rank"));
                rtnList.add(hero);
            }
            closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (rtnList.size() == 0) return null;
        return  rtnList;
    }
    public boolean insertMatch(int server, int accountId,OneMatch match){
        // need to insert into server_num_account_num_match
        // need to update server_num_account_num_hero
        // need to update server_num
        String insertSql = "insert into server_"+server +"_account_"+ accountId +"_" +
                "match(hero,start_time,end_time,hero_point,kill,death,assist,rank,triple_kill,quadra_kill,rampage,compliment,result,mvp,escaped)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String selectSql1 = "";
        String updateSql1  = "";
        String selectSql2 = "";
        String updateSql2  = "";
        try{
            PreparedStatement  preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1,match.getHero());
            preparedStatement.setString(2,match.getStartTime().toString());
            preparedStatement.setString(3,match.getEndTime().toString());
            preparedStatement.setDouble(4,match.getHeroPoint());
            preparedStatement.setInt(5,match.getKill());
            preparedStatement.setInt(6,match.getDeath());
            preparedStatement.setInt(7,match.getAssist());
            preparedStatement.setInt(8,match.getRank());
            preparedStatement.setInt(9,match.getTripleKill());
            preparedStatement.setInt(10,match.getQuadraKill());
            preparedStatement.setInt(11,match.getRampage());
            preparedStatement.setInt(12,match.getCompliment());
            preparedStatement.setBoolean(13,match.getResult());
            preparedStatement.setBoolean(14,match.getMvp());
            preparedStatement.setBoolean(15,match.getEscape());
            if (preparedStatement.executeUpdate()<=0){
                closeConnection();
                return false;
            }

            List<Hero> heroList =  getHeroesOfThisAccount(server,accountId,match.getHero());
            if (heroList.size() ==0){
                // have no this record
                String insertHero = "insert into server_"+server +"_account_"+ accountId +"_" +
                        "hero(hero_name,victory,total_game,kill,death,assist,total_hero_point,hero_rank) "+
                        "values(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(insertHero);
                preparedStatement1.setString(1,match.getHero());
                preparedStatement1.setInt(2,match.getResult()?1:0);
                preparedStatement1.setInt(3,1);
                preparedStatement1.setInt(4,match.getKill());
                preparedStatement1.setInt(5,match.getDeath());
                preparedStatement1.setInt(6,match.getAssist());
                preparedStatement1.setDouble(7,match.getHeroPoint());
                preparedStatement1.setInt(8,match.getRank());
                if (preparedStatement1.executeUpdate()<=0){
                    closeConnection();
                    return false;
                }

            }
            else if (heroList.size() ==1){
                // have this record
                Hero hero = heroList.get(0);
                String updateHero = "update server_"+server +"_account_"+ accountId +"_hero set "+
                        "victory=?,total_game=?,kill=?,death=?,assist=?,total_hero_point=?,hero_rank=? "+
                        "where hero_name = "+ match.getHero();
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateHero);
                preparedStatement2.setString(8,match.getHero());
                preparedStatement2.setInt(1,match.getResult()?1:0+hero.getVictory());
                preparedStatement2.setInt(2,1+hero.getTotalGames());
                preparedStatement2.setInt(3,hero.getKill()+ match.getKill());
                preparedStatement2.setInt(4,hero.getDeath()+match.getDeath());
                preparedStatement2.setInt(5,hero.getAssist()+match.getAssist());
                preparedStatement2.setDouble(6,hero.getTotalHeroPoints()+match.getHeroPoint());
                preparedStatement2.setInt(7,hero.getHeroRank() +match.getRank());
                if (preparedStatement2.executeUpdate()<=0){
                    closeConnection();
                    return false;
                }
            }

            String selectServerNUM = "select * from server_"+ server + " where account_id = ?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(selectServerNUM);
            preparedStatement3.setInt(1,accountId);
            ResultSet resultSet1 = preparedStatement3.executeQuery();
            Account account = new Account();
            while (resultSet1.next()){
                account.setAccountId(accountId);
                account.setServer(server);
                account.setPlayerId(resultSet1.getString("player_id"));
                // Time is stored as string
                String timeStr = resultSet1.getString("time");
//                    List<String> t = StringUtil.seperateString(timeStr,':');
//                    account.setTotalTime(new Time(Integer.parseInt(t.get(0)),Integer.parseInt(t.get(1)),Integer.parseInt(t.get(2))));
                account.setTotalTime(StringUtil.getTimeFromSql(timeStr));
                account.setLevel(resultSet1.getInt("level"));
                account.setScore(resultSet1.getInt("score"));
                account.setImgPath(resultSet1.getString("img_path"));
                account.setNickname(resultSet1.getString("nickname"));
                account.setVictories(resultSet1.getInt("victory"));
                account.setTotalMatches(resultSet1.getInt("total_match"));
                account.setEscape(resultSet1.getInt("escaped"));// it seems that escape is a key word in mysql
                account.setMvp(resultSet1.getInt("mvp"));
                account.setRampage(resultSet1.getInt("rampage"));
                account.setCompliment(resultSet1.getInt("compliment"));
                account.setKill(resultSet1.getInt("kill"));
                account.setAssist(resultSet1.getInt("assist"));
                account.setDeath(resultSet1.getInt("death"));
            }
            String updateServerNUM = "update server_"+server +" set victory =?, total_match =?, time=?" +
                    "kill=?,death=?,assist=?,rank =?, mvp=?,triple_kill=?,quadra_kill=?,rampage=?," +
                    "compliment =?, escaped =?        where accountId = "+ accountId;
            PreparedStatement preparedStatement4 = connection.prepareStatement(updateServerNUM);
            preparedStatement4.setInt(1,account.getVictories()+ (match.getResult()?1:0));
            preparedStatement4.setInt(2,account.getTotalMatches()+1);
            Time time = match.getStartTime().deltaTime(match.getEndTime());
            account.getTotalTime().addTime(time);
            preparedStatement4.setString(3,account.getTotalTime().toString());
            preparedStatement4.setInt(4,account.getKill() + match.getKill());
            preparedStatement4.setInt(5,account.getDeath() + match.getDeath());
            preparedStatement4.setInt(6,account.getAssist() + match.getAssist());
            preparedStatement4.setInt(7,account.getTotalRank() + match.getRank());
            preparedStatement4.setInt(8,account.getMvp() +(match.getMvp()?1:0));
            preparedStatement4.setInt(9,account.getTripleKill() +match.getTripleKill());
            preparedStatement4.setInt(10,account.getQuadraKill() + match.getQuadraKill());
            preparedStatement4.setInt(11,account.getRampage() + match.getRampage());
            preparedStatement4.setInt(12,account.getCompliment()+ match.getCompliment());
            preparedStatement4.setInt(13,account.getEscape() + (match.getEscape()?0:1));
            if (preparedStatement4.executeUpdate()<=0){
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
