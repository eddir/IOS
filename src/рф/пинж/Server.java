package рф.пинж;

import рф.пинж.command.CommandMap;
import рф.пинж.command.CommandSender;
import рф.пинж.network.Network;
import рф.пинж.utils.MainLogger;

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

    public Server(MainLogger logger, String ip, int port) {
        instance = this;
        this.logger = logger;

        this.ip = ip;
        this.port = port;

        this.clients = new HashSet<Client>();

        this.commandMap = new CommandMap(this);

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
