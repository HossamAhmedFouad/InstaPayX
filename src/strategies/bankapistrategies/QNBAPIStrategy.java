
package strategies.bankapistrategies;

import apis.bankapis.BankDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class QNBAPIStrategy implements BankAPIStrategy {
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8001/api/bank/qnb/accounts";


    @Override
    public boolean verify(String accountNumber, String phone) {
        String apiUrl = baseUrl + "/" + accountNumber;
        BankDTO bankDTO = restTemplate.getForObject(apiUrl, BankDTO.class);
        return bankDTO != null && phone.equals(bankDTO.getPhone());
    }

    @Override
    public boolean transferTo(String srcAccNumber, String destAccNumber, double amount, String targetUrl) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + targetUrl + "&sourceAccountId=" + srcAccNumber + "&targetAccountId=" + destAccNumber + "&amount=" + amount;
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, null, String.class);
        }catch (Throwable t){
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
    @Override
    public double getBalance(String accountNumber) {
        String apiUrl = baseUrl + "/" + accountNumber + "/balance";
        Double balance = restTemplate.getForObject(apiUrl, Double.class);
        return balance;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        String apiUrl = baseUrl + "/" + accountNumber + "/withdraw?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        String apiUrl = baseUrl + "/" + accountNumber + "/deposit?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }

}
