package apis.walletapis;

public class WalletDTO {
    private String phone;
    private double balance;

    public WalletDTO(String phone, double balance){
        this.phone = phone;
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
