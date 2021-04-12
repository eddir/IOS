package рф.пинж;

import рф.пинж.command.CommandMap;
import рф.пинж.network.Network;

public class Server {

    private boolean isRunning = true;

    private Network network;

    private final String ip;
    private final int port;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;

        this.network = new Network(this);
        this.network.run();
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
}
