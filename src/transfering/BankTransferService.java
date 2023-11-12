package transfering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankTransferService {
    private final RestTemplate restTemplate;

    @Autowired
    public BankTransferService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void transferBetweenBanks(String sourceApiUrl, String targetApiUrl, int sourceAccountId, int targetAccountId, double amount) {
        // Simulate the transfer logic
        String sourceUrl = sourceApiUrl + "/" + sourceAccountId + "/withdraw";
        String targetUrl = targetApiUrl + "/" + targetAccountId + "/deposit";

        restTemplate.put(sourceUrl, amount);
        restTemplate.put(targetUrl, amount);
    }
}
