package services;

import informations.BankUrl;
import informations.User;
import providers.Provider;

public class ElectricityBill implements Bill{
    @Override
    public boolean pay(User user, String code, BankUrl bankUrl, double amount) {
        if(user.getProvider().transfer(code, bankUrl.getApiUrl(), amount)){
            generateBill(user.getUsername(), code, amount);
            return true;
        }else{
            System.out.println("Error in transferring");
            return false;
        }
    }

    @Override
    public void generateBill(String name, String code, double amount) {
        System.out.println("************ Electricity Bill ************");
        System.out.println("Name: " + name);
        System.out.println("Payment Code: " + code);
        System.out.println("Details: ");
        System.out.println("Electricity consumption for current month (paid monthly)");
        System.out.println("Amount:" + amount);
        System.out.println("*****************************************");
    }

}
