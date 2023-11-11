package authentication;

public class LoginAuthenticator extends Authenticator{
    String username;
    String password;


    @Override
    public boolean verify() {
        return false;
    }
}
