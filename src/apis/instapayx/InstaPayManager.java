package apis.instapayx;

import informations.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InstaPayManager {
    RestTemplate restTemplate = new RestTemplate();

    String baseUrl = "http://localhost:8001/api/instapayx/accounts";
    String username;
    String password;

    public InstaPayManager(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean transfer(String receiver, String receiverName, double amount) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + receiverName + "&sourceAccountId=" + username + "&targetAccountId=" + receiver + "&amount=" + amount;
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, null, String.class);
        }catch (Throwable t){
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }


    public boolean verify() {
        String apiUrl = baseUrl + "/" + username;
        User user = restTemplate.getForObject(apiUrl, User.class);
        return user != null;
    }

    public boolean withdraw(double amount) {
        String apiUrl = baseUrl + "/" + username + "/withdraw?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }


    public boolean deposit(double amount) {
        String apiUrl = baseUrl + "/" + username + "/deposit?amount=" + amount;
        restTemplate.put(apiUrl, null);
        return true;
    }

    public boolean createAccount(User user){
        String apiUrl = baseUrl + "/" + username + "/create";
        ResponseEntity<User> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, user, User.class);
        }catch (Throwable t){
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
}
