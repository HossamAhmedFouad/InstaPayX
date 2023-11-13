package providers;

public class BankTransferStrategy implements TransferStrategy{
    private String receiverAccountNumber;
    private String receiverBankName;

    public BankTransferStrategy(String receiverAccountNumber, String receiverBankName) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.receiverBankName = receiverBankName;
    }

    @Override
    public boolean transfer(String receiver, double amount) {

        return false;
    }
}
