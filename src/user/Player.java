package user;

import util.Account;

import java.util.List;

public class Player {
    public Player (String id, String password){
        // we put the error check into JFrame, so we can use constructor securely
        this.password = password;
        this.id = id;
    }
    public Player(String id, String password, List<Account> accounts){
        this.password = password;
        this.id = id;
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String password; // password is also consisted of letters and numbers
    private String id;
    private List<Account> accounts;


}
