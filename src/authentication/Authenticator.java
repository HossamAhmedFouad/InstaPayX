package authentication;

public abstract class Authenticator {
    private String phone;
    public abstract boolean verify();
}
