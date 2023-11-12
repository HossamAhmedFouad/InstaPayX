package bankapis.qnbbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:src/bankapis/qnbbank/application-qnb.properties")
public class QNBServer {
    public static void main(String[] args) {
        SpringApplication.run(QNBServer.class, args);
        System.out.println("Running on http://localhost:8002/QNBapi/bank-accounts");
    }
}
