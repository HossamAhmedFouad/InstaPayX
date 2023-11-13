package informations;

public enum WalletUrl {
    ETISALAT("http://localhost:8001/api/wallet/etisalat/accounts"),
    VODAFONE("http://localhost:8001/api/wallet/vodafone/accounts");
    private final String apiUrl;

    WalletUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
