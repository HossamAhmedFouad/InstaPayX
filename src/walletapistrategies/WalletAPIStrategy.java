package walletapistrategies;

public interface WalletAPIStrategy {
    boolean verify(String phone);
    boolean transferTo(String phone, double amount);
    double getBalance();
}
