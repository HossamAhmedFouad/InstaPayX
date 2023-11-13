import authentication.Authenticator;
import bankapistrategies.HSBCAPIStrategy;
import providers.BankProvider;
import providers.BankTransferStrategy;
import providers.Provider;
import providers.WalletProvider;
import services.Bill;
import services.ElectricityBill;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your bank account");

        Provider bankprovider = new BankProvider();
        bankprovider.setTransferStrategy(new BankTransferStrategy("receiverAccountNumber", "receiverBankName"));
        bankprovider.transfer("asdf",100);
    }
}
