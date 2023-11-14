package services;

import informations.BankUrl;
import informations.User;
import providers.Provider;

import java.util.Scanner;

public class GasBill implements Bill{


    @Override
    public boolean pay(User user, String code, BankUrl bankUrl, double amount) {
        if(user.getProvider().transfer(code, bankUrl.getApiUrl(), amount)){
            generateBill(user.getUsername(), code, amount);
        }
        return true;
    }

    @Override
    public void generateBill(String name, String code, double amount) {
        System.out.println("************ Gas Bill ************");
        System.out.println("Name: " + name);
        System.out.println("Payment Code: " + code);
        System.out.println("Details: ");
        System.out.println("Gas consumption for current month (paid monthly)");
        System.out.println("Amount:" + amount);
        System.out.println("*****************************************");
    }
}
