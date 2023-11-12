package bankapis.qnbbank;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/QNBapi/bank-accounts")
public class QNBAPI {
    private final List<QNBAccount> bankAccounts = new ArrayList<>();

    public QNBAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new QNBAccount(1, "John Doe", 1000.0));
        bankAccounts.add(new QNBAccount(2, "Jane Smith", 2500.0));
    }

    @GetMapping
    public List<QNBAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @GetMapping("/{accountId}")
    public QNBAccount getBankAccount(@PathVariable int accountId) {
        for (QNBAccount account : bankAccounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @PostMapping
    public QNBAccount createBankAccount(@RequestBody QNBAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{accountId}")
    public QNBAccount updateBankAccount(@PathVariable int accountId, @RequestBody QNBAccount updatedAccount) {
        for (QNBAccount account : bankAccounts) {
            if (account.getAccountId() == accountId) {
                account.setAccountHolder(updatedAccount.getAccountHolder());
                account.setBalance(updatedAccount.getBalance());
                return account;
            }
        }
        return null; // Handle not found scenarios as needed
    }

    @DeleteMapping("/{accountId}")
    public void deleteBankAccount(@PathVariable int accountId) {
        bankAccounts.removeIf(account -> account.getAccountId() == accountId);
    }
}
