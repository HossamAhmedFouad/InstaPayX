package authentication;

import providers.BankProvider;

public class BankAuthenticator implements Authenticator{
    private final BankProvider provider;
    public BankAuthenticator(BankProvider provider) {
        this.provider = provider;
    }


    @Override
    public boolean verify(){
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
