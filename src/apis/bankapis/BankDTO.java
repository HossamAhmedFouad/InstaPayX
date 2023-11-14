package apis.bankapis;

public class BankDTO {
    private String accountNumber;
    private String phone;
    public BankDTO() {
    }
    public BankDTO(String accountNumber, String phone){
        this.accountNumber = accountNumber;
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
