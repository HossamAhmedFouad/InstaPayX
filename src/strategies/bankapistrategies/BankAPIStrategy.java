package strategies.bankapistrategies;

public interface BankAPIStrategy {
    boolean verify(String accountNumber, String phone);
    boolean transferTo(String srcAccNumber, String destAccNumber, double amount, String targetUrl);
    double getBalance(String accountNumber);
    boolean withdraw(String accountNumber, double amount);
    boolean deposit(String accountNumber, double amount);
}
