package transfering;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WalletTransferService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String buildUrl(String apiUrl, String accountId, String action, double amount) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/{accountId}/{action}")
                .queryParam("amount", amount);

        return builder.buildAndExpand(accountId, action).toUriString();
    }
    public static void transferBetweenWallets(String sourceApiUrl, String targetApiUrl, String sourceAccountPhone, String targetAccountPhone, double amount) {

        String sourceUrl = buildUrl(sourceApiUrl, sourceAccountPhone, "withdraw", amount);
        String targetUrl = buildUrl(targetApiUrl, targetAccountPhone ,"deposit", amount);

        restTemplate.put(sourceUrl, null); // Assuming no request body for simplicity
        restTemplate.put(targetUrl, null); // Assuming no request body for simplicity
    }
}
