package bankapis.hsbcbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:src/bankapis/hsbcbank/application-hsbc.properties")
public class HSBCServer {
    public static void main(String[] args) {
        SpringApplication.run(HSBCServer.class, args);
        System.out.println("Running on http://localhost:8001/HSBCapi/bank-accounts");
    }
}
