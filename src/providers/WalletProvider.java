package providers;

import strategies.walletapistrategies.WalletAPIStrategy;

public class WalletProvider implements Provider {
    private String phone;
    private WalletAPIStrategy strategy;

    public WalletProvider(WalletAPIStrategy strategy, String phone) {
        this.strategy = strategy;
        this.phone = phone;
    }

    @Override
    public boolean transfer(String receiver, String receiverName, double amount) {
        return strategy.transferTo(phone,receiver,amount,receiverName);
    }

    @Override
    public boolean verify() {
        return strategy.verify(phone);
    }

    @Override
    public double getBalance() {
        return strategy.getBalance(phone);
    }

    @Override
    public boolean withdraw(double amount) {
        return strategy.withdraw(phone, amount);
    }

    @Override
    public boolean deposit(double amount) {
        return strategy.deposit(phone, amount);
    }
}
