package bankapistrategies;

public interface BankAPIStrategy {
    boolean verify();
    boolean transferTo(String accountNumber, double amount,String targetUrl);
    double getBalance();
}
