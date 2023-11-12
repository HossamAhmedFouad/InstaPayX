package authentication;

import providers.InstaPayProvider;

public class LoginAuthenticator implements Authenticator{
    String username;
    String password;
    private final InstaPayProvider provider;

    public LoginAuthenticator(String username, String password, InstaPayProvider provider) {
        this.provider = provider;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean verify() {

        try {
            boolean isVerified = provider.verify();

            if (isVerified) {
                System.out.println("Login authentication successful.");
                return true;
            } else {
                System.out.println("Login authentication failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login authentication: " + e.getMessage());
            return false;
        }
    }
}
