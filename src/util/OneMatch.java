package util;

public class OneMatch {
    // Record the performance in a game
    private String hero;
    private boolean result;// true->win, false-> lose
    private Time startTime;
    private Time endTime;
    private double heroPoint;
//    private String K_D_A;// means K/D/A
    // use <int> kill, <int> death, <int> assist
    private int kill;
    private int death;
    private int assist;
    private int rank;
    private boolean mvp;
    private int tripleKill;
    private int quadraKill;
    private int rampage;
    private int compliment;
    private boolean escape;
    public boolean getResult(){
        return result;
    }
    public void setMvp(boolean mvp) {
        this.mvp = mvp;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }


    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public int getKill() {
        return kill;
    }

    public int getDeath() {
        return death;
    }

    public int getAssist() {
        return assist;
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

    public Time deltaTime(){
        return startTime.deltaTime(endTime);
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

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }


    public boolean getMvp() {
        return mvp;
    }

    public boolean getEscape() {
        return escape;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public double getHeroPoint() {
        return heroPoint;
    }

    public void setHeroPoint(double heroPoint) {
        this.heroPoint = heroPoint;
    }

    public String K_D_A() {
        return kill+"/"+death+"/"+assist;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "OneMatch{" +
                "hero='" + hero + '\'' +
                ", result=" + result +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", heroPoint=" + heroPoint +
                ", kill=" + kill +
                ", death=" + death +
                ", assist=" + assist +
                ", rank=" + rank +
                ", mvp=" + mvp +
                ", tripleKill=" + tripleKill +
                ", quadraKill=" + quadraKill +
                ", rampage=" + rampage +
                ", compliment=" + compliment +
                ", escape=" + escape +
                '}';
    }
}
