package services;

import informations.User;

public interface Bill {
    boolean pay(User user, String code);
    double codeValue(String code);
    void generateBill(String name, String code, double amount);
}
