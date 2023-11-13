import authentication.Authenticator;
import authentication.OTPHandler;
import authentication.RegisterAuthenticator;
import informations.User;
import providers.WalletProvider;
import strategies.bankapistrategies.BankAPIStrategy;
import strategies.bankapistrategies.HSBCAPIStrategy;
import providers.BankProvider;
import providers.Provider;
import strategies.bankapistrategies.QNBAPIStrategy;
import strategies.walletapistrategies.EtisalatAPIStrategy;
import strategies.walletapistrategies.VodafoneAPIStrategy;

import java.util.Scanner;

public class Client {
    private Scanner scanner;
    private User user;
    private Authenticator authenticator;
    Client(){
        scanner = new Scanner(System.in);
        user = null;
    }
    public void start(){
        //Implement template method
        register();
    }

    private boolean mainMenu(){
        System.out.println("Welcome to InstaPay X\nPlease Choose an option from below");
        System.out.println("1 - Login");
        System.out.println("2 - Register");
        System.out.println("3 - Exit");
        return false;
    }

    private boolean innerMenu(){
        System.out.println("Welcome Back! " + user.getUsername());
        System.out.println("Please Choose an option from below");
        System.out.println("1 - Transfer Money");
        System.out.println("2 - Pay Bills");
        System.out.println("3 - View Balance");
        System.out.println("4 - Log Out");
        return false;
    }

    private boolean login(){
        //Implement login logic
        return false;
    }
    private boolean logout(){
        //Implement logout
        return false;
    }
    private boolean register(){ //Registering a new InstaPayX account
        //Implement register logic
        System.out.println("Please Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Please Enter Password: ");
        String password = scanner.nextLine();
        String phone = null;
        Provider provider = null;
        int choice;
        System.out.println("Please Choose Provider\n1 - Bank Account\n2 - Wallet Account");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1){
            System.out.println("Please Enter Your Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.println("Please Enter your Phone Number: ");
            phone = scanner.nextLine();
            System.out.println("Please Choose Your Bank Provider");
            availableBanks();
            choice = scanner.nextInt();
            if(choice == 1){
                provider = new BankProvider(new HSBCAPIStrategy(),accountNumber,phone);
            }
            if(choice == 2){
                provider = new BankProvider(new QNBAPIStrategy(), accountNumber, phone);

            }
        }
        if(choice == 2){
            System.out.println("Please Enter your phone number: ");
            phone = scanner.nextLine();
            System.out.println("Please Choose Your Wallet Provider");
            availableWallets();
            choice = scanner.nextInt();
            if(choice == 1){
                provider = new WalletProvider(new EtisalatAPIStrategy(), phone);
            }
            if(choice == 2){
                provider = new WalletProvider(new VodafoneAPIStrategy(), phone);
            }
        }

        if(provider != null && provider.verify()){
            System.out.println("Provider Has Been Validated !");
        }else{
            System.out.println("Invalid Credentials, please try again");
            return false;
        }
        OTPHandler otpHandler = new OTPHandler();
        user = new User(username, password, phone, provider);
        authenticator = new RegisterAuthenticator(user, otpHandler);
        if(authenticator.verify()){
            System.out.println("Registration has been successful");
            return true;
        }else{
            System.out.println("Registration has stopped unsuccessfully");
            return false;
        }
    }

    private boolean transferMoney(){
        //Implement transfer money logic
        return false;
    }

    private boolean viewBalance(){
        //Implement viewing balance
        return false;
    }
    public boolean payBills(){
        //Implement pay bills logic
        return false;
    }

    private void availableBanks(){
        System.out.println("1 - HSBC Bank");
        System.out.println("2 - QNB  Bank");
    }

    private void availableWallets(){
        System.out.println("1 - Etisalat Wallet");
        System.out.println("2 - Vodafone Wallet");
    }

}
