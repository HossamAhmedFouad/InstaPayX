package walletapis.vodafoneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:src/walletapis/vodafoneapi/application-vodafone.properties")
public class VodafoneServer {
    public static void main(String[] args) {
        SpringApplication.run(VodafoneServer.class, args);
        System.out.println("Running on http://localhost:8004/Vodafoneapi/accounts");
    }
}