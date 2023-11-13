package bankapistrategies;

public interface BankAPIStrategy {
    boolean verify(String accountNumber, String phone);
    boolean transferTo(String srcAccNumber, String destAccNumber, double amount, String targetUrl);
    double getBalance(String accountNumber);
}
