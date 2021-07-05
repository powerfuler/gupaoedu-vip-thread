package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class TransferAccount implements Runnable{
    public Account fromAccount; //转出账户
    public Account toAccount; //转入账户
    public int amount; //金额

    public TransferAccount(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        while(true){
            synchronized (fromAccount){
                synchronized (toAccount){
                    if(fromAccount.getBalance()>=amount){
                        fromAccount.debit(amount);//更新转出账户余额
                        toAccount.credbit(amount);//更新转入账户余额
                    }
                }
            }
            //打印转出账户的余额
            System.out.println(fromAccount.getAccountName() + "......" + fromAccount.getBalance());
            //打印转入账户的余额
            System.out.println(toAccount.getAccountName() + "---" + toAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        Account fromAccount = new Account("张三",100000);
        Account toAccount = new Account("李四",200000);

        Thread a = new Thread(new TransferAccount(fromAccount,toAccount,1));
        Thread b = new Thread(new TransferAccount(toAccount,fromAccount,2));

        a.start();
        b.start();
    }
}
