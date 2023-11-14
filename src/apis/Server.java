// Server.java
package apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = "apis") // Scan both packages
@PropertySource("file:src/apis/application-api.properties")
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void startServer(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    public static void main(String[] args) {
        startServer(args);
    }
}
