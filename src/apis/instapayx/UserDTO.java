package apis.instapayx;

import informations.Banks;
import informations.Wallets;
import providers.BankProvider;
import providers.Provider;
import providers.WalletProvider;
import strategies.bankapistrategies.HSBCAPIStrategy;
import strategies.bankapistrategies.QNBAPIStrategy;
import strategies.walletapistrategies.EtisalatAPIStrategy;
import strategies.walletapistrategies.VodafoneAPIStrategy;

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

    public Provider getProvider(){
        Provider provider = null;
        if(Banks.contains(providerName) != null){
            Banks banks = Banks.contains(providerName);
            if(banks.getBankName().equals(Banks.QNB.getBankName())){
                provider = new BankProvider(new QNBAPIStrategy(), providerIdentifier, phone);
            }
            if(banks.getBankName().equals(Banks.HSBC.getBankName())){
                provider = new BankProvider(new HSBCAPIStrategy(), providerIdentifier, phone);
            }
        }else{
            Wallets wallets = Wallets.contains(providerName);
            if(wallets.getWalletName().equals(Wallets.ETISALAT.getWalletName())){
                provider = new WalletProvider(new EtisalatAPIStrategy(), providerIdentifier);
            }
            if(wallets.getWalletName().equals(Wallets.VODAFONE.getWalletName())){
                provider = new WalletProvider(new VodafoneAPIStrategy(), providerIdentifier);
            }
        }
        return provider;
    }

}
