package authentication;

import apis.instapayx.InstaPayManager;

public class LoginAuthenticator implements Authenticator{
    String username;
    String password;
    private InstaPayManager manager;

    public LoginAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
        manager = new InstaPayManager(username, password);
    }

    @Override
    public boolean verify() {

        try {
            boolean isVerified = manager.verify();

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
