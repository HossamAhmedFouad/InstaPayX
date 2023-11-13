package authentication;


import providers.WalletProvider;

public class WalletAuthenticator implements Authenticator{
    private final WalletProvider provider;

    public WalletAuthenticator(WalletProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean verify() {

        try {
            boolean isVerified = provider.verify();

            if (isVerified) {
                System.out.println("Wallet authentication successful. ");
                return true;
            } else {
                System.out.println("Wallet authentication failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during Wallet authentication: " + e.getMessage());
            return false;
        }
    }
}
