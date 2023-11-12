package walletapis.etisalatapi;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Etisalatapi/accounts")
public class EtisalatAPI {
    private final List<EtisalatAccount> bankAccounts = new ArrayList<>();

    public EtisalatAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new EtisalatAccount(1, "Tyson Kite Willington", 1605.0, "0700500"));
        bankAccounts.add(new EtisalatAccount(2, "John Man Smith", 2510.0, "0556515"));
    }

    @GetMapping
    public List<EtisalatAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @GetMapping("/{accountId}")
    public EtisalatAccount getBankAccount(@PathVariable int accountId) {
        for (EtisalatAccount account : bankAccounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @PostMapping
    public EtisalatAccount createBankAccount(@RequestBody EtisalatAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{accountId}")
    public EtisalatAccount updateBankAccount(@PathVariable int accountId, @RequestBody EtisalatAccount updatedAccount) {
        for (EtisalatAccount account : bankAccounts) {
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