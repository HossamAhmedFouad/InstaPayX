package transfering;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class APITransferService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String buildUrl(String apiUrl, String accountId, String action, double amount) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/{accountId}/{action}")
                .queryParam("amount", amount);

        return builder.buildAndExpand(accountId, action).toUriString();
    }
    public static void transferBetweenAPIs(String sourceApiUrl, String targetApiUrl, String sourceAccountId, String targetAccountId, double amount) {
        String sourceUrl = buildUrl(sourceApiUrl, sourceAccountId, "withdraw", amount);
        String targetUrl = buildUrl(targetApiUrl, targetAccountId ,"deposit", amount);

        restTemplate.put(sourceUrl, null);
        restTemplate.put(targetUrl, null);
    }

}
