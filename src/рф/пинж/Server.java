package рф.пинж;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import рф.пинж.command.CommandMap;
import рф.пинж.command.CommandSender;
import рф.пинж.network.Network;
import рф.пинж.utils.Config;
import рф.пинж.utils.MainLogger;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private boolean isRunning = true;

    private Network network;

    private final String ip;
    private final int port;

    private final Set<Client> clients;

    private final CommandMap commandMap;

    private final MainLogger logger;

    private static Server instance;

    private Config config;

    private Connection database;

    public Server(MainLogger logger, String ip, int port) {
        instance = this;
        this.logger = logger;

        this.ip = ip;
        this.port = port;

        this.clients = new HashSet<Client>();

        this.commandMap = new CommandMap(this);

        this.config = new Config();

        try {
            this.config.load();
        } catch (FileNotFoundException e) {
            this.crash(e.getMessage());
        }

        logger.info("Подключение к бд MySQL...");

        Sql2o sql2o = new Sql2o(
                this.config.getProperty("db-address"),
                this.config.getProperty("db-login"),
                this.config.getProperty("db-password"));

        try {
            this.database = sql2o.open();
        } catch (Sql2oException e) {
            this.crash(e.getMessage());
        }

        logger.info("Подключение к бд готово");

        this.network = new Network(this);
        this.network.run();
    }

    public boolean dispatchCommand(CommandSender sender, String command) {
        if (this.commandMap.dispatch(sender, command)) {
            return true;
        }

        sender.sendMessage("Неизвестная команда. Вы ввели " + command);
        return false;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void crash(String message) {
        logger.alert(message);
        this.stop();
    }

    public void stop() {
        this.isRunning = false;
        logger.alert("Завершение.");
        System.exit(0);
    }

    public Connection getDatabase() {
        return database;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public static MainLogger getLogger() {
        return instance.logger;
    }

    public static Server getInstance() {
        return instance;
    }
}
