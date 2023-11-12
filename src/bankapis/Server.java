package bankapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "bankapis") // Scan both packages
@PropertySource("file:src/bankapis/application-bank.properties")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
