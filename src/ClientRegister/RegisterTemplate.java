package ClientRegister;

import apis.instapayx.InstaPayManager;
import apis.instapayx.UserDTO;
import authentication.Authenticator;
import authentication.OTPHandler;
import authentication.RegisterAuthenticator;
import informations.User;
import providers.Provider;

import java.util.Scanner;

public abstract class RegisterTemplate {
    String password;
    String username;
    String phone = null;
    Provider provider = null;
    String accountNumber = null;
    String providerName = null;
    String providerIdentifier = null;
    protected Scanner scanner;

    RegisterTemplate(String password,String username,Scanner scanner){
        this.password = password;
        this.username = username;
        this.scanner = scanner;

    }
    abstract void setData();
    public User getUser(){
        setData();
        User user;
        if(provider != null && provider.verify()){
            System.out.println("Provider Has Been Validated !");
        }else{
            System.out.println("Invalid Credentials, please try again");
            return null;
        }
        OTPHandler otpHandler = new OTPHandler();
        user = new User(username, password, phone);
        Authenticator authenticator = new RegisterAuthenticator(user, otpHandler);
        if(authenticator.verify()){
            System.out.println("Registration has been successful");
            UserDTO userDTO = new UserDTO(username,password,phone,providerName,providerIdentifier);
            InstaPayManager instaPayManager = new InstaPayManager(username,password);
            instaPayManager.createAccount(userDTO);
            user.setProvider(userDTO.getProvider());
            return user;
        }else{
            System.out.println("Registration has stopped unsuccessfully");
            return null;
        }
    };
    protected String readLine(){
        String res = scanner.nextLine();
        if(res.isEmpty()) res = scanner.nextLine();
        return res;
    }
}
