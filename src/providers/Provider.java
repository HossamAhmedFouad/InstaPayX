package providers;

public interface Provider {
    boolean transfer(String receiver, String receiverName, double amount);
    boolean verify();
    double getBalance();
    boolean withdraw(double amount);
    boolean deposit(double amount);
}
