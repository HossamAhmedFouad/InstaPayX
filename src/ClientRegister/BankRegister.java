package ClientRegister;

import apis.instapayx.UserDTO;
import informations.Banks;
import providers.BankProvider;
import providers.Provider;
import strategies.bankapistrategies.HSBCAPIStrategy;
import strategies.bankapistrategies.QNBAPIStrategy;

import java.util.Scanner;

public class BankRegister extends RegisterTemplate{

    public BankRegister(String password, String username, Scanner scanner) {
        super(password, username,scanner);
    }

    @Override
    void setData() {
        System.out.println("Please Enter Your Account Number: ");
        accountNumber = readLine();
        providerIdentifier = accountNumber;
        System.out.println("Please Enter your Phone Number: ");
        phone = readLine();
        System.out.println("Please Choose Your Bank Provider");
        availableBanks();
        int choice;
        choice = scanner.nextInt();
        if (choice == 1) {
            providerName = Banks.HSBC.getApiUrl();
            provider = new BankProvider(new HSBCAPIStrategy(), accountNumber, phone);
        }
        if (choice == 2) {
            providerName = Banks.QNB.getApiUrl();
            provider = new BankProvider(new QNBAPIStrategy(), accountNumber, phone);

        }
    }
    private void availableBanks(){
        System.out.println("1 - HSBC Bank");
        System.out.println("2 - QNB  Bank");
    }
}
