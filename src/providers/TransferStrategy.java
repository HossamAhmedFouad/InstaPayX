package providers;

public interface TransferStrategy {
    boolean transfer(String receiver, double amount);
}
