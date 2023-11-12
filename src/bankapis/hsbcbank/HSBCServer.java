package bankapis.hsbcbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HSBCServer {
    public static void main(String[] args) {
        SpringApplication.run(HSBCAccount.class, args);
    }
}
