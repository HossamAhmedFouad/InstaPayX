package services;

import informations.User;

public class ElectricityBill implements Bill{
    String ELECTRICITY_BILL_ACCOUNT = "CREATE ACCOUNT";
    @Override
    public boolean pay(User user, String code) {
        if(user.getProvider().transfer(code, ELECTRICITY_BILL_ACCOUNT, codeValue(code))){
            generateBill(user.getUsername(), code, codeValue(code));
            return true;
        }else{
            System.out.println("Error in transferring");
            return false;
        }
    }
    @Override
    public double codeValue(String code){
        return Double.parseDouble(code) / 15 * 6 + 7;
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
