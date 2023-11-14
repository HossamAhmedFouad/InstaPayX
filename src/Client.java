import apis.Server;
import apis.instapayx.InstaPayManager;
import apis.instapayx.UserDTO;
import authentication.Authenticator;
import authentication.LoginAuthenticator;
import authentication.OTPHandler;
import authentication.RegisterAuthenticator;
import informations.Banks;
import informations.User;
import informations.Wallets;
import providers.WalletProvider;
import services.Bill;
import services.ElectricityBill;
import services.GasBill;
import services.WaterBill;
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
    private InstaPayManager manager;
    Client(){
        scanner = new Scanner(System.in);
        user = null;
    }
    public void start(){
        Server.startServer();
        while (true){
            while (user == null || !user.getAccountStatus()){
                mainMenu();
                int choice = scanner.nextInt();
                if(choice == 1){
                    if(login()){
                        break;
                    }
                }else if(choice == 2){
                    register();
                }else if(choice == 3){
                    System.out.println("Come Back Again !");
                    System.exit(0);
                }
            }
            while (user.getAccountStatus()){
                innerMenu();
                int choice = scanner.nextInt();
                if(choice == 1){
                    transferMoney();
                }else if(choice == 2){
                    payBills();
                }else if(choice == 3){
                    viewBalance();
                }else if(choice == 4){
                    logout();
                }
            }
        }

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
        System.out.println("Please Enter your InstapayX Username: ");
        String username = readLine();
        System.out.println(username);
        System.out.println("Please Enter your Password: ");
        String password = readLine();

        LoginAuthenticator authService = new LoginAuthenticator(username,password);
        boolean isAuthenticated = authService.verify();

        if (!isAuthenticated){
            System.out.println("Login failed. Please check your username and password.");
            return false;
        }
        manager = new InstaPayManager(username,password);
        UserDTO userDTO = manager.getAccount();
        user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getPhone());
        user.setProvider(userDTO.getProvider());
        user.setAccountStatus(true);
        System.out.println("Login successful!");
        return true;
    }

    private boolean logout(){
        if (user != null && user.getAccountStatus()) {
            user.setAccountStatus(false);
            System.out.println("Logout successful!");
            return true;
        } else {
            System.out.println("Logout failed. User is not logged in.");
            return false;
        }
    }

    private boolean register(){
        System.out.println("Please Enter Username: ");
        String username = readLine();
        if(InstaPayManager.exists(username)){
            System.out.println("Username already exists");
            return false;
        }
        System.out.println("Please Enter Password: ");
        String password = readLine();
        String phone = null;
        Provider provider = null;
        String accountNumber = null;
        String providerName = null;
        String providerIdentifier = null;
        int choice;
        System.out.println("Please Choose Provider\n1 - Bank Account\n2 - Wallet Account");
        choice = scanner.nextInt();
        if (choice == 1){
            System.out.println("Please Enter Your Account Number: ");
            accountNumber = readLine();
            providerIdentifier = accountNumber;
            System.out.println("Please Enter your Phone Number: ");
            phone = readLine();
            System.out.println("Please Choose Your Bank Provider");
            availableBanks();
            choice = scanner.nextInt();
            if(choice == 1){
                providerName = Banks.HSBC.getApiUrl();
                provider = new BankProvider(new HSBCAPIStrategy(),accountNumber,phone);
            }
            if(choice == 2){
                providerName = Banks.QNB.getApiUrl();
                provider = new BankProvider(new QNBAPIStrategy(), accountNumber, phone);

            }
        }else if(choice == 2){
            System.out.println("Please Enter your phone number: ");
            phone = readLine();
            providerIdentifier = phone;
            System.out.println("Please Choose Your Wallet Provider");
            availableWallets();
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

        if(provider != null && provider.verify()){
            System.out.println("Provider Has Been Validated !");
        }else{
            System.out.println("Invalid Credentials, please try again");
            return false;
        }
        OTPHandler otpHandler = new OTPHandler();
        user = new User(username, password, phone);
        authenticator = new RegisterAuthenticator(user, otpHandler);
        if(authenticator.verify()){
            System.out.println("Registration has been successful");
            UserDTO userDTO = new UserDTO(username,password,phone,providerName,providerIdentifier);
            InstaPayManager instaPayManager = new InstaPayManager(username,password);
            instaPayManager.createAccount(userDTO);
            user.setProvider(userDTO.getProvider());
            return true;
        }else{
            System.out.println("Registration has stopped unsuccessfully");
            return false;
        }
    }

    private boolean transferMoney(){
        System.out.println("Please Select Your Transferring Method: ");
        System.out.println("1 - Via your provider");
        System.out.println("2 - Via InstaPayX");

        int choice = scanner.nextInt();
        String receiverID = null;
        String targetUrl = null;
        if(choice == 1){
            if(user.getProvider() instanceof BankProvider){
                System.out.println("Please Enter Account Number: ");
                scanner.nextLine();
                receiverID = readLine();
                System.out.println("Please Choose The Bank Provider: ");
                availableBanks();
                choice = scanner.nextInt();
                if(choice == 1){
                    targetUrl = Banks.HSBC.getApiUrl();
                }else{
                    targetUrl = Banks.QNB.getApiUrl();
                }
            }else{
                System.out.println("Please Enter Phone Number: ");
                receiverID = readLine();
                System.out.println("Please Choose The Wallet Provider: ");
                availableWallets();
                choice = scanner.nextInt();
                if(choice == 1){
                    targetUrl = Wallets.ETISALAT.getApiUrl();
                }else{
                    targetUrl = Wallets.VODAFONE.getApiUrl();
                }
            }
            System.out.println("Please Enter Amount To Transfer: ");
            double amount = scanner.nextDouble();
            if( user.getProvider().transfer(receiverID, targetUrl, amount)){
                System.out.println("Transfer Has Been Successful");
            }else{
                System.out.println("Transfer Failed, please try again later");
            }
        }else{
            String username;
            double amount;
            System.out.println("Please Enter Username to transfer to: ");
            username = readLine();
            System.out.println("Please Enter amount: ");
            amount = scanner.nextDouble();
            if(!InstaPayManager.exists(username)){
                System.out.println("Username does not exist");
                return false;
            }
            UserDTO receiver = InstaPayManager.getAccount(username);
            if(!manager.transfer(receiver.getProviderIdentifier(), receiver.getProviderName(), amount)){
                System.out.println("Transferring Failed");
                return false;
            }
            System.out.println("Transferring has been successful");
        }
        return true;
    }

    private boolean viewBalance(){
        System.out.println("Your balance has: " + user.getProvider().getBalance() + "E.G.");
        return true;
    }
    public boolean payBills(){
        System.out.println("Please Choose Type of Bill");
        availableBills();
        int choice = scanner.nextInt();
        Bill bill = null;
        if(choice == 1){
            bill = new ElectricityBill();
        }
        if(choice == 2){
            bill = new GasBill();
        }
        if(choice == 3){
            bill = new WaterBill();
        }
        System.out.println("Please Enter Payment Code");
        String code = readLine();
        if(!bill.pay(user,code)){
            System.out.println("Error in payment");
            return false;
        }
        System.out.println("Payment Successful");
        return true;
    }

    private void availableBanks(){
        System.out.println("1 - HSBC Bank");
        System.out.println("2 - QNB  Bank");
    }

    private void availableWallets(){
        System.out.println("1 - Etisalat Wallet");
        System.out.println("2 - Vodafone Wallet");
    }

    private void availableBills(){
        System.out.println("1 - Electricity Bill");
        System.out.println("2 - Gas Bill");
        System.out.println("3 - Water Bill");
    }
    private String readLine(){
        String res = scanner.nextLine();
        if(res.length() == 0) res = scanner.nextLine();
        return res;
    }

}
