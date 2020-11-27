package util;

import java.security.PublicKey;
import java.util.List;

public class Account {
    // a player can have an id, but can have many accounts
    private Time totalTime; //the total amount of time the player participated in the match
    private String nickname;// nickname is not unique, and nickname can only consist of letters and numbers
    private int server;// the server the player is on  (Here using the id of the server)
    private int level;// the level of the player
    private int score;// the score of the player
    private String imgPath;// the path of the icon image of the current account
    private int accountId;// unique in each district
    private int victories;// the number of victory games
//    private int  defeats;// the number of defeated games
    // defeat = total -  victory
    private double escapeRate;// the escape number of this account
    private int mvp;// the number of mvp
    private int tripleKill;
    private int quadraKill;
    private int rampage;
    private int kill;
    private int assist;
    private int death;
    private int compliment;
    private List<Hero> heroes;
    private List<OneMatch> matches;
    private int escape;// escape rate == escape/totalMatches
    private String playerId;
    private int totalMatches;// the total games
    private String tier;
    private int totalRank;
    private int number; // The ranking of player accounts within the server

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(int totalRank) {
        this.totalRank = totalRank;
    }

    public String getTier() {
        if (this.totalRank<=100)
            return "BRONZE";
        else if (this.totalRank<=300)
            return "SILVER";
        else if (totalRank<=800)
            return "GOLD";
        else if (totalRank<=2000)
            return "DIAMOND";
        else return "KING";
    }


    public Account(){}
    public double winRate(){
        return victories/totalMatches;
    }
    public double KDA(){
        return (kill+assist)/death;
    }
    public double escapeRate(){
        return escape/totalMatches;
    }
    public int getEscape() {
        return escape;
    }

    public void setEscape(int escape) {
        this.escape = escape;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public int getLevel() {
        return totalMatches/20;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }



    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public int getTripleKill() {
        return tripleKill;
    }

    public void setTripleKill(int tripleKill) {
        this.tripleKill = tripleKill;
    }

    public int getQuadraKill() {
        return quadraKill;
    }

    public void setQuadraKill(int quadraKill) {
        this.quadraKill = quadraKill;
    }

    public int getRampage() {
        return rampage;
    }

    public void setRampage(int rampage) {
        this.rampage = rampage;
    }


    public int getCompliment() {
        return compliment;
    }

    public void setCompliment(int compliment) {
        this.compliment = compliment;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<OneMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<OneMatch> matches) {
        this.matches = matches;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public int getDefeats(){
        return totalMatches - victories;
    }
    @Override
    public String toString() {
//        MapUtil mapUtil = new MapUtil();
        return Server.values()[server-1].getServerName() + "/"
                + this.nickname +"/ "
                + "Lv."+this.getLevel();
    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "totalTime=" + totalTime +
//                ", nickname='" + nickname + '\'' +
//                ", server=" + server +
//                ", level=" + level +
//                ", score=" + score +
//                ", imgPath='" + imgPath + '\'' +
//                ", accountId=" + accountId +
//                ", victories=" + victories +
//                ", escapeRate=" + escapeRate +
//                ", mvp=" + mvp +
//                ", tripleKill=" + tripleKill +
//                ", quadraKill=" + quadraKill +
//                ", rampage=" + rampage +
//                ", kill=" + kill +
//                ", assist=" + assist +
//                ", death=" + death +
//                ", compliment=" + compliment +
//                ", heroes=" + heroes +
//                ", matches=" + matches +
//                ", escape=" + escape +
//                ", playerId='" + playerId + '\'' +
//                ", totalMatches=" + totalMatches +
//                ", tier='" + tier + '\'' +
//                ", totalRank=" + totalRank +
//                '}';
//    }
}
