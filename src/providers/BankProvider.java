package providers;

public class BankProvider implements Provider {
    private String accountNumber;
    private String phone;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean transfer() {
        return false;
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
