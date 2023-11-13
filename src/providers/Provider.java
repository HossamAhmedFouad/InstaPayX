package providers;

public interface Provider {
    void setTransferStrategy(TransferStrategy transferStrategy);
    public boolean transfer(String receiver, double amount);
    public boolean verify();
    public double getBalance();
//    withdraw(amount)
//    deposit(amount)
}
