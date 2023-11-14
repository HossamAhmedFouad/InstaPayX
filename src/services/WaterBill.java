package services;

import informations.User;

public class WaterBill implements Bill{
    String WATER_BILL_ACCOUNT = "http://localhost:8001/api/bank/qnb/accounts";
    String ACCOUNT_NUMBER = "3";
    @Override
    public boolean pay(User user, String code) {
        if(user.getProvider().transfer(ACCOUNT_NUMBER, WATER_BILL_ACCOUNT, codeValue(code))){
            generateBill(user.getUsername(), code, codeValue(code));
            return true;
        }else{
            System.out.println("Error in transferring");
            return false;
        }
    }
    @Override
    public double codeValue(String code){
        return Double.parseDouble(code) / 20 * 9 + 8;
    }
    @Override
    public void generateBill(String name, String code, double amount) {
        System.out.println("************ Water Bill ************");
        System.out.println("Name: " + name);
        System.out.println("Payment Code: " + code);
        System.out.println("Details: ");
        System.out.println("Water consumption for current month (paid monthly)");
        System.out.println("Amount:" + amount);
        System.out.println("*****************************************");
    }
}
