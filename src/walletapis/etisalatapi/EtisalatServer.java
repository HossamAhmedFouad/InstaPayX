package walletapis.etisalatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:src/walletapis/etisalatapi/application-etisalat.properties")
public class EtisalatServer {
    public static void main(String[] args) {
        SpringApplication.run(EtisalatServer.class, args);
        System.out.println("Running on http://localhost:8003/Etisalatapi/accounts");
    }
}
