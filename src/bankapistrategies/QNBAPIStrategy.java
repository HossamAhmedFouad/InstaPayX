package bankapistrategies;

public class QNBAPIStrategy implements BankAPIStrategy{

    @Override
    public boolean verify(String accountNumber, String phone) {
        return false;
    }

    @Override
    public boolean transferTo(String accountNumber, double amount) {
        return false;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
