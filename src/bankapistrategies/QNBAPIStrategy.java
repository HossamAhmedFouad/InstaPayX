
package bankapistrategies;

import apis.bankapis.BankDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class QNBAPIStrategy implements BankAPIStrategy {
    String baseUrl = "http://localhost:8001/api/bank/qnb/accounts";


    @Override
    public boolean verify(String accountNumber, String phone) {
        String apiUrl = baseUrl + "/" + accountNumber;
        RestTemplate restTemplate = new RestTemplate();
        BankDTO bankDTO = restTemplate.getForObject(apiUrl, BankDTO.class);
        return bankDTO != null && phone.equals(bankDTO.getPhone());
    }

    @Override
    public boolean transferTo(String srcAccNumber, String destAccNumber, double amount, String targetUrl) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + targetUrl + "&sourceAccountId=" + srcAccNumber + "&targetAccountId=" + destAccNumber + "&amount=" + amount;
        RestTemplate restTemplate = new RestTemplate();
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

        // Use RestTemplate to make the GET request
        RestTemplate restTemplate = new RestTemplate();
        Double balance = restTemplate.getForObject(apiUrl, Double.class);
        return balance;
    }

}
