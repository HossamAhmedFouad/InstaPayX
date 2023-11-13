package providers;

public class InstaPayProvider implements Provider{



    public boolean transfer(double amount) {
        return false;
    }

    @Override
    public boolean transfer(String receiver, String receiverName, double amount) {
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

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public boolean deposit(double amount) {
        return false;
    }
}
