package user;

public enum UserType {
    ADMIN("ADMIN",0),PLAYER("PLAYER",1);
    private String name;
    private int index;
    private UserType(String name,int index){
        this.index=index;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
