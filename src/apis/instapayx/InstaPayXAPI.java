package apis.instapayx;

import informations.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfering.InstaPayXTransferService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/instapayx/accounts") // Updated RequestMapping
public class InstaPayXAPI {
    private final List<User> bankAccounts = new ArrayList<>();

    public InstaPayXAPI() {

    }

    @GetMapping("/{username}")
    public User getAccount(@PathVariable String username) {
        for (User account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountUser, @RequestParam String targetAccountUser, @RequestParam double amount) {
        User sourceAccount = getAccountByUsername(sourceAccountUser);
        if (sourceAccount == null || sourceAccount.getProvider().getBalance() < amount) {
            return ResponseEntity.badRequest().body("Invalid source account or insufficient balance");
        }
        InstaPayXTransferService.transferBetweenUsers(getApiUrl(), targetApiUrl, sourceAccountUser, targetAccountUser, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    public String getApiUrl() {
        return "http://localhost:8001/api/instapay/accounts";
    }

    @PutMapping("/{username}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String username, @RequestParam double amount) {
        User account = getAccountByUsername(username);
        if (account != null && account.getProvider().deposit(amount) ) {
            return ResponseEntity.ok("Deposit successful");
        }
        return ResponseEntity.badRequest().body("Invalid account");
    }

    @PutMapping("/{username}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String username, @RequestParam double amount) {
        User account = getAccountByUsername(username);
        if(account != null && account.getProvider().getBalance() >= amount && account.getProvider().withdraw(amount)){
            return ResponseEntity.ok("Withdrawal successful");
        }
        return ResponseEntity.badRequest().body("Invalid account or insufficient balance");
    }

    public User getAccountByUsername(String username) {
        for (User account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createBankAccount(@RequestBody User user) {
        if (user == null || user.getUsername() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(getAccountByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bankAccounts.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
