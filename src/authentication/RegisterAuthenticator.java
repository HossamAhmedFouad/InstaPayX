package authentication;

public class RegisterAuthenticator extends Authenticator{

    String username;
    String password;
    OTPHandler otpHandler;

    @Override
    public boolean verify() {
        return false;
    }
}
