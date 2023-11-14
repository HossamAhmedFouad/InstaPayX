// Server.java
package bankapis;

import bankapistrategies.BankAPIStrategy;
import bankapistrategies.HSBCAPIStrategy;
import bankapistrategies.QNBAPIStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = "bankapis") // Scan both packages
@PropertySource("file:src/bankapis/application-bank.properties")
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void startServer(String[] args) {
        SpringApplication.run(Server.class, args);
        logger.info("Server has started!");
        BankAPIStrategy st = new QNBAPIStrategy("2", "11335");
        BankAPIStrategy st2 = new HSBCAPIStrategy("1", "0777500");

        logger.info("Verifying bank API...");
            double am = 100;
            boolean res = st2.transferTo("2",am,"http://localhost:8001/api/qnb/bank-accounts");
            logger.info("Transaction result: " + res);
            logger.info("Balance result: " + st.getBalance());
        logger.info("Balance result: " + st2.getBalance());

    }

    public static void main(String[] args) {
        startServer(args);

        // Log a message after the server has started

    }
}
