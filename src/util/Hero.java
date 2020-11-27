package util;

public class Hero {
    private String HeroName;
    private int victory;
    private int totalGames;
    //    private String K_D_A;// means K/D/A
    // use <int> kill, <int> death, <int> assist
    private int kill;
    private int death;
    private int assist;
    private int totalHeroPoints;
    private int heroRank;
    private int mvp;
    private int tripleKill;
    private int quadraKill;
    private int rampage;
    private int compliment;
    private int escape;

    public int getKill() {
        return kill;
    }

    public int getDeath() {
        return death;
    }

    public int getAssist() {
        return assist;
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

    public int getEscape() {
        return escape;
    }

    public void setEscape(int escape) {
        this.escape = escape;
    }

    public double winRate(){
        return victory/totalGames;
    }

    public String getHeroName() {
        return HeroName;
    }

    public void setHeroName(String heroName) {
        HeroName = heroName;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }


    public int getTotalHeroPoints() {
        return totalHeroPoints;
    }

    public void setTotalHeroPoints(int totalHeroPoints) {
        this.totalHeroPoints = totalHeroPoints;
    }

    public int getHeroRank() {
        return heroRank;
    }

    public void setHeroRank(int heroRank) {
        this.heroRank = heroRank;
    }
    public void setKill(int kill) {
        this.kill = kill;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }
    public String K_D_A() {
        return kill+"/"+death+"/"+assist;
    }
}
