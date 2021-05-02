package рф.пинж.ios;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.network.NetworkThread;
import рф.пинж.ios.network.protocol.CommandPacket;
import рф.пинж.ios.network.protocol.DataPacket;
import рф.пинж.ios.network.protocol.ProtocolInfo;
import рф.пинж.ios.network.protocol.ViewPacket;
import рф.пинж.ios.view.View;

import java.util.HashMap;
import java.util.Map;

public class Client implements CommandSender {
    // Я бы назвал этот класс User, но это имя зарезрервировано под модуль пользоваля.

    private final NetworkThread thread;
    private final String ip;
    private final Server server;

    private String action;
    private final Map<String, String> session = new HashMap<>();

    public Client(Server server, NetworkThread thread, String ip) {
        this.server = server;
        this.thread = thread;
        this.ip = ip;
    }

    public void sendMessage(String message) {
        this.thread.send(message);
    }

    public void sendView(View view) {
        this.thread.send(view.prepare());
    }

    public void handlePacket(DataPacket packet) {
        if (packet.getPid() == ProtocolInfo.COMMAND_PACKET) {
            CommandPacket commandPacket = (CommandPacket)packet;
            Server.getLogger().debug("Принята команда.");

            if (!this.getServer().dispatchCommand(this, commandPacket.getCommand())) {
                Server.getLogger().debug("При выполнении команды что-то пошло не так.");
            }
        } else if(packet.getPid() == ProtocolInfo.VIEW_PACKET) {
            ViewPacket viewPacket = (ViewPacket) packet;
            Server.getLogger().debug("Принято действие в UI.");
            if (!this.getServer().dispatchView(this, this.action.split("do=")[0] + viewPacket.getRequest())) {
                Server.getLogger().debug("При выполнении действия что-то пошло не так.");
            }
        } else {
            Server.getLogger().debug("Неизвестный пакет.");
        }
    }

    public Map<String, String> getSession() {
        return session;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public boolean isClient() {
        return true;
    }

    public String getIp() {
        return ip;
    }
}
