package providers;

import bankapistrategies.BankAPIStrategy;

public class BankProvider implements Provider {
    private String accountNumber;
    private String phone;
    private BankAPIStrategy strategy;
    private TransferStrategy transferStrategy;

//    public BankProvider(BankAPIStrategy strategy, String accountNumber, String phone) {
//        this.strategy = strategy;
//        this.accountNumber = accountNumber;
//        this.phone = phone;
//    }


    @Override
    public void setTransferStrategy(TransferStrategy transferStrategy) {
        this.transferStrategy = transferStrategy;
    }

    @Override
    public boolean transfer(String receiver, double amount) {
        return transferStrategy.transfer(receiver,amount);
    }

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
