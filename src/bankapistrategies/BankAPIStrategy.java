package bankapistrategies;

public interface BankAPIStrategy {
    boolean verify(String accountNumber, String phone);
    boolean transferTo(String accountNumber, double amount);
    double getBalance();
}
