package informations;

public enum BankUrl {
    QNB("http://localhost:8001/api/bank/qnb/accounts"),
    HSBC("http://localhost:8001/api/bank/hsbc/accounts");

    private final String apiUrl;

    BankUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
