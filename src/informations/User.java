package informations;

import providers.Provider;

public class User {
    String username;
    String password;
    String phone;
    String name;
    String email;

    boolean accountStatus;
    Provider provider;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
//MyBank   ->   InstaPay=>check my friend account if(wallet) ->   MyFriendWallet else -> myfriendbank


//1-bank --> username of reciever
//2-instapy call with bank to withdraw (amount) from sender bank account to instapay account
//3-instapy account +=amount
//4- instapy account want to know receiver account => wallet or bank
//5- instapy deposite --
// amount wallet += amount
//amount bank +=amount