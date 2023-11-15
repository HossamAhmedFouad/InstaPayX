package ClientRegister;

import apis.instapayx.UserDTO;
import informations.Banks;
import informations.Wallets;
import providers.BankProvider;
import providers.Provider;
import providers.WalletProvider;
import strategies.bankapistrategies.HSBCAPIStrategy;
import strategies.bankapistrategies.QNBAPIStrategy;
import strategies.walletapistrategies.EtisalatAPIStrategy;
import strategies.walletapistrategies.VodafoneAPIStrategy;

import java.util.Scanner;

public class WalletRegister extends RegisterTemplate{

    public WalletRegister(String password, String username, Scanner scanner) {
        super(password, username,scanner);
    }

    @Override
    void setData() {
        System.out.println("Please Enter your phone number: ");
        phone = readLine();
        providerIdentifier = phone;
        System.out.println("Please Choose Your Wallet Provider");
        availableWallets();
        int choice;
        choice = scanner.nextInt();
        if(choice == 1){
            providerName = Wallets.ETISALAT.getApiUrl();
            provider = new WalletProvider(new EtisalatAPIStrategy(), phone);
        }
        if(choice == 2){
            providerName = Wallets.VODAFONE.getApiUrl();
            provider = new WalletProvider(new VodafoneAPIStrategy(), phone);
        }
    }

    private void availableWallets(){
        System.out.println("1 - Etisalat Wallet");
        System.out.println("2 - Vodafone Wallet");
    }
}
