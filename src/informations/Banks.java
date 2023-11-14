package informations;

public enum Banks {
    QNB("http://localhost:8001/api/bank/qnb/accounts", "QNB"),
    HSBC("http://localhost:8001/api/bank/hsbc/accounts", "HSBC");

    private final String apiUrl;
    private final String bankName;

    Banks(String apiUrl, String bankName) {
        this.apiUrl = apiUrl;
        this.bankName = bankName;
    }

    public String getApiUrl() {
        return apiUrl;
    }
    public String getBankName(){
        return bankName;
    }
    public static Banks contains(String url) {
        for (Banks myEnum : Banks.values()) {
            if (myEnum.apiUrl.equals(url)) {
                return myEnum;
            }
        }
        return null;
    }

}
