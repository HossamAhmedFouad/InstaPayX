package authentication;

import providers.BankProvider;

public class BankAuthenticator implements Authenticator{
    private String accountNumber;
    private final BankProvider provider;
    private String phone;
    public BankAuthenticator(String phone, String accountNumber, BankProvider provider) {
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.provider = provider;
    }


    @Override
    public boolean verify(){
        provider.setAccountNumber(accountNumber);
        provider.setPhone(phone);
        try {
            boolean isVerified = provider.verify();

            if (isVerified) {
                System.out.println("Bank authentication successful.");
                return true;
            } else {
                System.out.println("Bank authentication failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during bank authentication: " + e.getMessage());
            return false;
        }
    }
}
