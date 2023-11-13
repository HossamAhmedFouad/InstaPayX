// Client.java
import bankapistrategies.BankAPIStrategy;
import bankapistrategies.QNBAPIStrategy;
import bankapis.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        Server.startServer(args);


    }
}
