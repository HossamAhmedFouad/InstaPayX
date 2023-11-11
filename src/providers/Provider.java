package providers;

public abstract class Provider {
    public abstract boolean transfer();
    public abstract boolean verify();
    public abstract double getBalance();
}
