package providers;

public class InstaPayProvider extends Provider{
    @Override
    public boolean transfer() {
        return false;
    }

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
