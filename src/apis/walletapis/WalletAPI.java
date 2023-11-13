package apis.walletapis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface WalletAPI {

    WalletDTO getWalletAccount(@PathVariable String phone);

    double getBalance(@PathVariable String phone);

    ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountPhone, @RequestParam String targetAccountPhone, @RequestParam double amount);

    ResponseEntity<String> deposit(@PathVariable String phone, @RequestParam double amount);

    ResponseEntity<String> withdraw(@PathVariable String phone, @RequestParam double amount);

    WalletAccount getAccountByPhone(String phone);

    String getApiUrl();
}
