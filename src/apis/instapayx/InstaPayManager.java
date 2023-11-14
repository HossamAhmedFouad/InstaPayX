package apis.instapayx;

import informations.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InstaPayManager {
    static RestTemplate restTemplate = new RestTemplate();

    static String baseUrl = "http://localhost:8001/api/instapayx/accounts";
    String username;
    String password;

    public InstaPayManager(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean transfer(String receiver, String receiverName, double amount) {
        String apiUrl = baseUrl + "/transfer-to?targetApiUrl=" + receiverName + "&sourceAccountUser=" + username + "&targetAccountUser=" + receiver + "&amount=" + amount;
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(apiUrl, null, String.class);
        }catch (Throwable t){
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
    public UserDTO getAccount(){
        String apiUrl = baseUrl+"/"+username;
        UserDTO user = restTemplate.getForObject(apiUrl, UserDTO.class);
        return user;
    }
    public boolean verify() {
        String apiUrl = baseUrl + "/" + username;
        System.out.println("Username: " + username);
        UserDTO user = restTemplate.getForObject(apiUrl, UserDTO.class);
        return user != null && username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    public static String getProviderName(String username){
        String apiUrl = baseUrl + "/" + username;
        System.out.println("Username: " + username);
        UserDTO user = restTemplate.getForObject(apiUrl, UserDTO.class);
        if(user != null){
            return user.getProviderName();
        }
        return null;
    }

    public static UserDTO getAccount(String username){
        String apiUrl = baseUrl+"/"+username;
        UserDTO user = restTemplate.getForObject(apiUrl, UserDTO.class);
        return user;
    }

    public static boolean exists(String username){
        String apiUrl = baseUrl + "/" + username;
        System.out.println("Username: " + username);
        UserDTO user = restTemplate.getForObject(apiUrl, UserDTO.class);
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

    public boolean createAccount(UserDTO user){
        String apiUrl = baseUrl + "/create?username=" + user.getUsername() + "&password=" + user.getPassword() + "&phone=" + user.getPhone() + "&providerName=" + user.getProviderName() + "&providerIdentifier=" + user.getProviderIdentifier();
        restTemplate.put(apiUrl, null);
        return true;
    }
}
