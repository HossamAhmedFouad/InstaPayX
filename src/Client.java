import bankapistrategies.HSBCAPIStrategy;
import providers.BankProvider;
import providers.Provider;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your bank account");

        Provider bankprovider = new BankProvider(new HSBCAPIStrategy(), "accNumber", "phone");

    }
}
