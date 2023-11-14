package walletapis.vodafoneapi;

import org.springframework.web.bind.annotation.*;
import walletapis.etisalatapi.EtisalatAccount;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Vodafoneapi/accounts")
public class VodafoneAPI {
    private final List<VodafoneAccount> bankAccounts = new ArrayList<>();

    public VodafoneAPI() {
        // Populate the in-memory database with some sample data
        bankAccounts.add(new VodafoneAccount(1, "Vodafone 1", 1605.0, "0700500"));
        bankAccounts.add(new VodafoneAccount(2, "Vodafone 2", 2510.0, "0556515"));
    }

    @GetMapping
    public List<VodafoneAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @GetMapping("/{accountId}")
    public VodafoneAccount getBankAccount(@PathVariable int accountId) {
        for (VodafoneAccount account : bankAccounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; // Return null or handle not found scenarios as needed
    }

    @PostMapping
    public VodafoneAccount createBankAccount(@RequestBody VodafoneAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{accountId}")
    public VodafoneAccount updateBankAccount(@PathVariable int accountId, @RequestBody VodafoneAccount updatedAccount) {
        for (VodafoneAccount account : bankAccounts) {
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