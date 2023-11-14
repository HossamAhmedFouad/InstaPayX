package services;

import informations.BankUrl;
import informations.User;
import providers.Provider;

public interface Bill {
    boolean pay(User user, String code, BankUrl bankUrl, double amount);
    void generateBill(String name, String code, double amount);
}
