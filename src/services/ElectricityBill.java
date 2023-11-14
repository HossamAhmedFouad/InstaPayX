package services;

import providers.Provider;

import java.util.Scanner;

public class ElectricityBill implements Bill{


    @Override
    public boolean pay(Provider provider, String code) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter code:");
            String userInputCode = scanner.nextLine();

            boolean paymentSuccessful = provider.transfer();

            if (paymentSuccessful) {
                System.out.println("Electricity bill paid successfully using " + provider.getClass().getSimpleName());
                return true;
            } else {
                System.out.println("Failed to pay electricity bill using " + provider.getClass().getSimpleName());
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during Bill: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void generateBill() {
        System.out.println("************ Electricity Bill ************");
        System.out.println("Name: John Doe");
        System.out.println("Address: 123 Main Street, Cityville");
        System.out.println("Payment Code: ABC123");
        System.out.println("Details: ");
        System.out.println("Electricity consumption details go here.");
        System.out.println("Amount: $100.00");
        System.out.println("*****************************************");
    }

}
