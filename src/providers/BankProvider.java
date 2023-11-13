package providers;

import bankapistrategies.BankAPIStrategy;

public class BankProvider implements Provider {
    private String accountNumber;
    private String phone;
    private BankAPIStrategy strategy;

    public BankProvider(BankAPIStrategy strategy, String accountNumber, String phone) {
        this.strategy = strategy;
        this.accountNumber = accountNumber;
        this.phone = phone;
    }

    @Override
    public boolean transfer(String receiverId, String receiverName, double amount) {
        return strategy.transferTo(accountNumber, receiverId, amount, receiverName);
    }

    @Override
    public boolean verify() {
        return strategy.verify(accountNumber, phone);
    }

    @Override
    public double getBalance() {
        return strategy.getBalance(accountNumber);
    }

    public void setAccountNumber(String accountNumber) {
    }

    public void setPhone(String phone) {
    }
}
