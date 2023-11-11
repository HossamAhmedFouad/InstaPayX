package authentication;

import providers.BankProvider;

public class BankAuthenticator extends Authenticator{
    private String accountNumber;
    private BankProvider provider;

    @Override
    public boolean verify(){
        return false;
    }
}
