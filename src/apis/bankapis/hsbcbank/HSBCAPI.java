package apis.bankapis.hsbcbank;

import apis.bankapis.BankAPI;
import apis.bankapis.BankAccount;
import apis.bankapis.BankDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfering.APITransferService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bank/hsbc/accounts")
public class HSBCAPI implements BankAPI {
    private final List<BankAccount> bankAccounts = new ArrayList<>();
    public HSBCAPI() {
        bankAccounts.add(new BankAccount("1", "hossamfouad", 1605.0, "01116535351"));
        bankAccounts.add(new BankAccount("2", "johnsamy", 2510.0, "01128216266"));
    }

    @GetMapping("/{accountId}")
    public BankDTO getBankAccount(@PathVariable String accountId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return new BankDTO(account.getAccountId(), account.getPhone());
            }
        }
        return null;
    }

    @GetMapping("/{accountId}/balance")
    public double getBalance(@PathVariable String accountId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return account.getBalance();
            }
        }
        return Double.NaN;
    }
    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountId, @RequestParam String targetAccountId, @RequestParam double amount) {
        BankAccount sourceAccount = getAccountById(sourceAccountId);
        if (sourceAccount == null || sourceAccount.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Invalid source account or insufficient balance");
        }

        APITransferService.transferBetweenAPIs(getApiUrl(), targetApiUrl, sourceAccountId, targetAccountId, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    public String getApiUrl() {
        return "http://localhost:8001/api/bank/hsbc/accounts";
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
    public ResponseEntity<String> withdraw(@PathVariable String accountId, @RequestParam double amount) {
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