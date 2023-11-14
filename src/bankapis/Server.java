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
    }

    public static void main(String[] args) {
        startServer(args);

        // Log a message after the server has started

    }
}
