package providers;

public interface Provider {
    public boolean transfer(String receiver, String receiverName, double amount);
    public boolean verify();
    public double getBalance();
//    withdraw(amount)
//    deposit(amount)
}
