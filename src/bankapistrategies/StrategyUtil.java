
package bankapistrategies;
import apis.bankapis.BankDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

public class StrategyUtil{
    String accountNumber;
    String phone ;
    String baseUrl;
    public StrategyUtil(String accountNumber,String phone,String baseUrl){
        this.accountNumber = accountNumber;
        this.phone = phone;
        this.baseUrl = baseUrl;
    }
    public boolean verify() {
        String apiUrl = baseUrl + "/" + accountNumber;

        // Use RestTemplate to make the GET request
        RestTemplate restTemplate = new RestTemplate();
        BankDTO bankDTO = restTemplate.getForObject(apiUrl, BankDTO.class);
        return bankDTO != null && phone.equals(bankDTO.getPhone());
    }

    public boolean transferTo(String accountNumber, double amount,String targetUrl) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + targetUrl + "&sourceAccountId=" + this.accountNumber + "&targetAccountId=" + accountNumber + "&amount=" + amount;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, null, String.class);
        }catch (Throwable t){
            return false;
        }

        // Check the response status and return true if successful
        return responseEntity.getStatusCode().is2xxSuccessful();

    }

    public double getBalance() {
        String apiUrl = baseUrl + "/" + accountNumber + "/balance";

        // Use RestTemplate to make the GET request
        RestTemplate restTemplate = new RestTemplate();
        Double balance = restTemplate.getForObject(apiUrl, Double.class);
        return balance;
    }
}
