package bankapis.hsbcbank;

import bankapis.BankAccount;
import bankapis.BankDTO;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hsbc/bank-accounts") // Updated RequestMapping
public class HSBCAPI {
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

}