package bankapis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface BankAPI {
    BankDTO getBankAccount(@PathVariable String accountId);
    double getBalance(@PathVariable String accountId);
    ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountId, @RequestParam String targetAccountId, @RequestParam double amount);
    String getApiUrl();
    ResponseEntity<String> deposit(@PathVariable String accountId, @RequestParam double amount);
    ResponseEntity<String> withdraw(@PathVariable String accountId, @RequestBody double amount);
    BankAccount getAccountById(String accountId);
}
