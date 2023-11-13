package providers;

public class WalletProvider implements Provider {

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
