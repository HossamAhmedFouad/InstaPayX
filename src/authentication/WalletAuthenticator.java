package authentication;

import providers.WalletProvider;

public class WalletAuthenticator extends Authenticator{
    private WalletProvider provider;

    @Override
    public boolean verify() {
        return false;
    }
}
