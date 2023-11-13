package providers;

import apis.walletapis.WalletAPI;
import bankapistrategies.BankAPIStrategy;

public class WalletProvider implements Provider {

    private String accountNumber;
    private String phone;
    private WalletAPI strategy;

    public WalletProvider(WalletAPI strategy, String accountNumber, String phone) {
        this.strategy = strategy;
        this.accountNumber = accountNumber;
        this.phone = phone;
    }

    @Override
    public boolean transfer(String receiver, String receiverName, double amount) {
        //Call Transfer Strategy
        return false;
    }

    @Override
    public boolean verify() {
        //Call Verify Strategy
        return false;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
