package walletapistrategies;

public class EtisalatAPIStrategy implements WalletAPIStrategy{
    @Override
    public boolean verify(String phone) {
        return false;
    }

    @Override
    public boolean transferTo(String phone, double amount) {
        return false;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
