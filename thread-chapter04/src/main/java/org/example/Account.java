package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class Account {
    private String accountName;
    private int balance;

    public Account(String accountName, int balance) {
        this.accountName = accountName;
        this.balance = balance;
    }
    public void debit(int amount){//更新转出方余额
        this.balance -= amount;
    }

    public void credbit(int amount){//更新转入方余额
        this.balance += amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
