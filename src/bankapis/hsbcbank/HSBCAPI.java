package bankapis.hsbcbank;

import bankapis.BankAPI;
import bankapis.BankAccount;
import bankapis.BankDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import transfering.BankTransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hsbc/bank-accounts") // Updated RequestMapping
public class HSBCAPI implements BankAPI {
    private final List<BankAccount> bankAccounts = new ArrayList<>();
    public HSBCAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new BankAccount("1", "Yassuo Willington", 1605.0, "0777500"));
        bankAccounts.add(new BankAccount("2", "Darius Smith", 2510.0, "044112335"));
    }

    @GetMapping("/{accountId}")
    public BankDTO getBankAccount(@PathVariable String accountId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return new BankDTO(account.getAccountId(), account.getPhone());
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @GetMapping("/{accountId}/balance")
    public double getBalance(@PathVariable String accountId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return account.getBalance();
            }
        }
        return Double.NaN; // Return null or handle not found scenarios as needed
    }
    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountId, @RequestParam String targetAccountId, @RequestParam double amount) {
        BankAccount sourceAccount = getAccountById(sourceAccountId);
        if (sourceAccount == null || sourceAccount.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Invalid source account or insufficient balance");
        }

        BankTransferService.transferBetweenBanks(getApiUrl(), targetApiUrl, sourceAccountId, targetAccountId, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    public String getApiUrl() {
        // Get the base URL of this API dynamically
        // In a real-world scenario, you might have a configuration or discovery service
        return "http://localhost:8001/api/hsbc/bank-accounts";
    }
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String accountId, @RequestParam double amount) {
        BankAccount account = getAccountById(accountId);
        if (account != null) {
            account.deposit(amount);
            return ResponseEntity.ok("Deposit successful");
        }
        return ResponseEntity.badRequest().body("Invalid account");
    }

    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String accountId, @RequestBody double amount) {
        BankAccount account = getAccountById(accountId);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            return ResponseEntity.ok("Withdrawal successful");
        }
        return ResponseEntity.badRequest().body("Invalid account or insufficient balance");
    }

    public BankAccount getAccountById(String accountId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

}