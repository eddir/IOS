package рф.пинж;

import рф.пинж.command.CommandSender;
import рф.пинж.network.NetworkThread;
import рф.пинж.network.protocol.CommandPacket;
import рф.пинж.network.protocol.DataPacket;
import рф.пинж.network.protocol.ProtocolInfo;

import java.net.InetSocketAddress;

public class Client implements CommandSender {
    // Я бы назвал этот класс User, но это имя зарезрервировано под модуль пользоваля.

    private NetworkThread thread;
    private String ip;
    private Server server;

    public Client(Server server, NetworkThread thread, String ip) {
        this.server = server;
        this.thread = thread;
        this.ip = ip;
        this.sendMessage("Соединение установлено. Введите help для помощи.");
    }

    public void sendMessage(String message) {
        this.thread.send(message);
    }

    public void handlePacket(DataPacket packet) {
        if (packet.getPid() == ProtocolInfo.COMMAND_PACKET) {
            CommandPacket commandPacket = (CommandPacket)packet;
            Server.getLogger().debug("Принята команда.");

            if (!this.getServer().dispatchCommand(this, commandPacket.getCommand())) {
                Server.getLogger().debug("При выполнении команды что-то пошло не так.");
            }
        } else {
            Server.getLogger().debug("Неизвестный пакет.");
        }
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
