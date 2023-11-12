package bankapis.hsbcbank;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/HSBCapi/bank-accounts")
public class HSBCAPI {
    private final List<HSBCAccount> bankAccounts = new ArrayList<>();

    public HSBCAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new HSBCAccount(1, "Yassuo Willington", 1605.0));
        bankAccounts.add(new HSBCAccount(2, "Darius Smith", 2510.0));
    }

    @GetMapping
    public List<HSBCAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @GetMapping("/{accountId}")
    public HSBCAccount getBankAccount(@PathVariable int accountId) {
        for (HSBCAccount account : bankAccounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @PostMapping
    public HSBCAccount createBankAccount(@RequestBody HSBCAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{accountId}")
    public HSBCAccount updateBankAccount(@PathVariable int accountId, @RequestBody HSBCAccount updatedAccount) {
        for (HSBCAccount account : bankAccounts) {
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