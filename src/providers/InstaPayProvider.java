package providers;

public class InstaPayProvider implements Provider{


    @Override
    public boolean transfer(double amount) {
        return false;
    }

    @Override
    public void setTransferStrategy(TransferStrategy transferStrategy) {

    }

    @Override
    public boolean transfer(String receiver, double amount) {
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
