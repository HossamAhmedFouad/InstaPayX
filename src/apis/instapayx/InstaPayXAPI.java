package apis.instapayx;

import apis.bankapis.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfering.BankTransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/instapayx/accounts") // Updated RequestMapping
public class InstaPayXAPI {
    private final List<InstaPayXAccount> bankAccounts = new ArrayList<>();

    public InstaPayXAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new InstaPayXAccount("user1", "password1", "user1@instapay.com", "0777500"));
        bankAccounts.add(new InstaPayXAccount("user2", "password2", "user2@instapay.com", "044112335"));
    }

    @GetMapping("/{username}")
    public InstaPayXAccount getAccount(@PathVariable String username) {
        for (InstaPayXAccount account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountId, @RequestParam String targetAccountId, @RequestParam double amount) {
        InstaPayXAccount sourceAccount = getAccountByUsername(sourceAccountId);
        if (sourceAccount == null || sourceAccount.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Invalid source account or insufficient balance");
        }

        BankTransferService.transferBetweenBanks(getApiUrl(), targetApiUrl, sourceAccountId, targetAccountId, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    public String getApiUrl() {
        return "http://localhost:8001/api/instapay/accounts";
    }

    @PutMapping("/{username}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String username, @RequestParam double amount) {
        InstaPayXAccount account = getAccountByUsername(username);
        if (account != null) {
            account.deposit(amount);
            return ResponseEntity.ok("Deposit successful");
        }
        return ResponseEntity.badRequest().body("Invalid account");
    }

    @PutMapping("/{username}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String username, @RequestParam double amount) {
        InstaPayXAccount account = getAccountByUsername(username);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            return ResponseEntity.ok("Withdrawal successful");
        }
        return ResponseEntity.badRequest().body("Invalid account or insufficient balance");
    }

    public InstaPayXAccount getAccountByUsername(String username) {
        for (InstaPayXAccount account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
