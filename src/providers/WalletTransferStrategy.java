package providers;

public class WalletTransferStrategy implements TransferStrategy{
    @Override
    public boolean transfer(String receiver, double amount) {
        return false;
    }
}
