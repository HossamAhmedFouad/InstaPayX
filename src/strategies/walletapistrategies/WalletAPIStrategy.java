package strategies.walletapistrategies;

public interface WalletAPIStrategy {
    boolean verify(String phone);
    boolean transferTo(String srcPhone, String destPhone, double amount, String targetUrl);
    double getBalance(String phone);
    boolean withdraw(String phone, double amount);
    boolean deposit(String phone, double amount);
}
