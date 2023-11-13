package providers;

public class InstaPayProvider implements Provider{



    public boolean transfer(double amount) {
        return true;
    }

    @Override
    public boolean transfer(String receiver, String receiverName, double amount) {
        return true;
    }

    @Override
    public boolean verify() {

        return true;
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public boolean withdraw(double amount) {
        return true;
    }

    @Override
    public boolean deposit(double amount) {
        return true;
    }
}
