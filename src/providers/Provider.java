package providers;

public interface Provider {
    public boolean transfer();
    public boolean verify();
    public double getBalance();
}
