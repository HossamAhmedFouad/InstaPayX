package walletapistrategies;

public class VodafoneAPIStrategy implements WalletAPIStrategy{

    @Override
    public boolean verify(String phone) {
        return false;
    }

    @Override
    public boolean transferTo(String srcPhone, String destPhone, double amount, String targetUrl) {
        return false;
    }

    @Override
    public double getBalance(String phone) {
        return 0;
    }
}
