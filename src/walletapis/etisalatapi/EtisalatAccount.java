package walletapis.etisalatapi;

public class EtisalatAccount {
    private int accountId;
    private String accountHolder;
    private double balance;
    private String phone;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EtisalatAccount(int accountId, String accountHolder, double balance, String phone) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.phone = phone;
    }
}
