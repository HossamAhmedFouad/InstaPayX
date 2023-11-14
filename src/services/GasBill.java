package services;

import informations.User;

public class GasBill implements Bill{
    String GAS_BILL_ACCOUNT = "http://localhost:8001/api/bank/qnb/accounts";
    String ACCOUNT_NUMBER = "2";
    @Override
    public boolean pay(User user, String code) {
        if(user.getProvider().transfer(ACCOUNT_NUMBER, GAS_BILL_ACCOUNT, codeValue(code))){
            generateBill(user.getUsername(), code, codeValue(code));
        }
        return true;
    }
    @Override
    public double codeValue(String code){
        return Double.parseDouble(code) / 10 * 3 + 5;
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
