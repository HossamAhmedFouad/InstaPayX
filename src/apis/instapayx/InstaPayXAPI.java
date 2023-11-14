package apis.instapayx;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import transfering.APITransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/instapayx/accounts")
public class InstaPayXAPI {
    private final List<UserDTO> bankAccounts = new ArrayList<>();

    public InstaPayXAPI() {
        bankAccounts.add(new UserDTO("johnsamy","johnS@my1234","01128216266","http://localhost:8001/api/bank/hsbc/accounts","2"));
        bankAccounts.add(new UserDTO("hossamfouad","Hoss@mfouad1234","01116535351","http://localhost:8001/api/bank/hsbc/accounts","1"));

    }

    @GetMapping("/{username}")
    public UserDTO getAccount(@PathVariable String username) {
        for (UserDTO account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    @PostMapping("/transfer-to")
    public ResponseEntity<String> transferTo(@RequestParam String targetApiUrl, @RequestParam String sourceAccountUser, @RequestParam String targetAccountUser, @RequestParam double amount) {
        UserDTO sourceAccount = getAccountByUsername(sourceAccountUser);
        if (sourceAccount == null) {
            return ResponseEntity.badRequest().body("Invalid source account");
        }
        double balance = new RestTemplate().getForObject(sourceAccount.getProviderName() + "/" + sourceAccount.getProviderIdentifier() + "/balance", Double.class);

        if(balance < amount){
            return ResponseEntity.badRequest().body("Insufficient Balance");
        }
        APITransferService.transferBetweenAPIs(getApiUrl(), targetApiUrl, sourceAccountUser, targetAccountUser, amount);

        return ResponseEntity.ok("Balance transferred successfully");
    }

    public String getApiUrl() {
        return "http://localhost:8001/api/instapayx/accounts";
    }

    @PutMapping("/{username}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String username, @RequestParam double amount) {
        UserDTO account = getAccountByUsername(username);
        if(account == null){
            return ResponseEntity.badRequest().body("Invalid account");
        }
        new RestTemplate().put(account.getProviderName()+"/" + account.getProviderIdentifier() + "/deposit?amount=" + amount, null);
        return ResponseEntity.ok("Deposit successful");
    }

    @PutMapping("/{username}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String username, @RequestParam double amount) {
        UserDTO account = getAccountByUsername(username);
        if(account == null){
            return ResponseEntity.badRequest().body("Invalid account");
        }
        new RestTemplate().put(account.getProviderName()+"/" + account.getProviderIdentifier() + "/withdraw?amount=" + amount, null);
        return ResponseEntity.ok("Deposit successful");
    }

    public UserDTO getAccountByUsername(String username) {
        for (UserDTO account : bankAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    @PutMapping("/create")
    public ResponseEntity<UserDTO> createBankAccount(@RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String providerName, @RequestParam String providerIdentifier) {
        if(getAccountByUsername(username) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = new UserDTO(username, password, phone, providerName, providerIdentifier);
        bankAccounts.add(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
