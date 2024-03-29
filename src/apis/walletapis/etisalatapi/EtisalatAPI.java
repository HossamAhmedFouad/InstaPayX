package apis.walletapis.etisalatapi;

import apis.walletapis.WalletAPI;
import apis.walletapis.WalletAccount;
import apis.walletapis.WalletDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfering.APITransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wallet/etisalat/accounts")
public class EtisalatAPI implements WalletAPI {
    private final List<WalletAccount> bankAccounts = new ArrayList<>();

    public EtisalatAPI() {
        bankAccounts.add(new WalletAccount("1", "Tyson Kite Willington", 1605.0, "01116535351"));
        bankAccounts.add(new WalletAccount("2", "John Man Smith", 2510.0, "01116535351"));
    }

    @GetMapping("/{phone}")
    public WalletDTO getWalletAccount(@PathVariable String phone) {
        for (WalletAccount account : bankAccounts) {
            if (account.getPhone().equals(phone)) {
                return new WalletDTO(account.getPhone(), account.getBalance());
            }
        }
        return null;
    }

    @GetMapping("/{phone}/balance")
    public double getBalance(@PathVariable String phone) {
        for (WalletAccount account : bankAccounts) {
            if (account.getPhone().equals(phone)) {
                return account.getBalance();
            }
        }
        return Double.NaN;
    }

    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String  sourceAccountPhone, @RequestParam String targetAccountPhone, @RequestParam double amount) {
        WalletAccount sourceAccount = getAccountByPhone(sourceAccountPhone);
        if (sourceAccount == null || sourceAccount.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Invalid source account or insufficient balance");
        }

        APITransferService.transferBetweenAPIs(getApiUrl(), targetApiUrl, sourceAccountPhone, targetAccountPhone, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    @PutMapping("/{phone}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String phone, @RequestParam double amount) {
        WalletAccount account = getAccountByPhone(phone);
        if (account != null) {
            account.deposit(amount);
            return ResponseEntity.ok("Deposit successful");
        }
        return ResponseEntity.badRequest().body("Invalid account");
    }

    @PutMapping("/{phone}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String phone, @RequestParam double amount) {
        WalletAccount account = getAccountByPhone(phone);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            return ResponseEntity.ok("Withdrawal successful");
        }
        return ResponseEntity.badRequest().body("Invalid account or insufficient balance");
    }

    public WalletAccount getAccountByPhone(String phone) {
        for (WalletAccount account : bankAccounts) {
            if (account.getPhone().equals(phone)) {
                return account;
            }
        }
        return null;
    }

    public String getApiUrl() {
        return "http://localhost:8001/api/wallet/etisalat/accounts";
    }

}