package apis.instapayx;

public class UserDTO {
    String username;
    String password;
    String phone;
    String providerName;
    String providerIdentifier;
    public UserDTO() {
    }

    public UserDTO(String username, String password, String phone, String providerName, String providerIdentifier){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.providerName = providerName;
        this.providerIdentifier = providerIdentifier;
    }

    public String getProviderIdentifier() {
        return providerIdentifier;
    }

    public void setProviderIdentifier(String providerIdentifier) {
        this.providerIdentifier = providerIdentifier;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }


}
