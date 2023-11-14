package transfering;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class InstaPayXTransferService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String buildUrl(String apiUrl, String accountId, String action, double amount) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/{accountId}/{action}")
                .queryParam("amount", amount);

        return builder.buildAndExpand(accountId, action).toUriString();
    }
    public static void transferBetweenUsers(String sourceApiUrl, String targetApiUrl, String sourceUsername, String targetUsername, double amount) {

        String sourceUrl = buildUrl(sourceApiUrl, sourceUsername, "withdraw", amount);
        String targetUrl = buildUrl(targetApiUrl, targetUsername ,"deposit", amount);
        System.out.println("URL Source: " + sourceUrl);
        System.out.println("URL Target: " + targetUrl);

        restTemplate.put(sourceUrl, null); // Assuming no request body for simplicity
        restTemplate.put(targetUrl, null); // Assuming no request body for simplicity
    }
}