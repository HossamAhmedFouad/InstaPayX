package informations;

import apis.walletapis.WalletDTO;

public enum Wallets {
    ETISALAT("http://localhost:8001/api/wallet/etisalat/accounts", "ETISALAT"),
    VODAFONE("http://localhost:8001/api/wallet/vodafone/accounts", "VODAFONE");
    private final String apiUrl;
    private final String walletName;

    Wallets(String apiUrl, String walletName) {
        this.apiUrl = apiUrl;
        this.walletName = walletName;
    }

    public String getApiUrl() {
        return apiUrl;
    }
    public String getWalletName(){
        return walletName;
    }

    public static Wallets contains(String url) {
        for (Wallets myEnum : Wallets.values()) {
            if (myEnum.apiUrl.equals(url)) {
                return myEnum;
            }
        }
        return null;
    }
}
