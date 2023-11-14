package strategies.walletapistrategies;

import apis.bankapis.BankDTO;
import apis.walletapis.WalletAPI;
import apis.walletapis.WalletDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EtisalatAPIStrategy implements WalletAPIStrategy{
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8001/api/wallet/etisalat/accounts";

    @Override
    public boolean verify(String phone) {
        String apiUrl = baseUrl + "/" + phone;
        WalletDTO walletDTO = restTemplate.getForObject(apiUrl, WalletDTO.class);
        return walletDTO != null && phone.equals(walletDTO.getPhone());
    }

    @Override
    public boolean transferTo(String srcPhone, String destPhone, double amount, String targetUrl) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + targetUrl + "&sourceAccountId=" + srcPhone + "&targetAccountId=" + destPhone + "&amount=" + amount;
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, null, String.class);
        }catch (Throwable t){
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    @Override
    public double getBalance(String phone) {
        String apiUrl = baseUrl + "/" + phone + "/balance";
        return restTemplate.getForObject(apiUrl, Double.class);
    }

    @Override
    public boolean withdraw(String phone, double amount) {
        String apiUrl = baseUrl + "/" + phone + "/withdraw?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }

    @Override
    public boolean deposit(String phone, double amount) {
        String apiUrl = baseUrl + "/" + phone + "/deposit?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }

}
