package рф.пинж.ios;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.network.NetworkThread;
import рф.пинж.ios.network.protocol.*;
import рф.пинж.ios.view.View;
import рф.пинж.ios.view.element.Interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client implements CommandSender {
    // Я бы назвал этот класс User, но это имя зарезрервировано под модуль пользоваля.

    private final NetworkThread thread;
    private final String ip;
    private final Server server;

    private String action;
    private Interface currentInterface;
    private final Map<String, String> session = new HashMap<>();

    private List<String> permissions = new ArrayList<>();

    public Client(Server server, NetworkThread thread, String ip) {
        this.server = server;
        this.thread = thread;
        this.ip = ip;
        this.recalculatePermissions();
    }

    public void sendMessage(String message) {
        this.thread.send(message);
    }

    public void sendView(View view) {
        if (view instanceof Interface) {
            this.setAction("!interface");
            this.currentInterface = (Interface) view;
        }

        this.thread.show(view.prepare());
    }

    public void handlePacket(DataPacket packet) {
        if (packet.getPid() == ProtocolInfo.COMMAND_PACKET) {
            CommandPacket commandPacket = (CommandPacket) packet;
            Server.getLogger().debug("Принята команда.");

            if (!this.getServer().dispatchCommand(this, commandPacket.getCommand())) {
                Server.getLogger().debug("При выполнении команды что-то пошло не так.");
            }
        } else if (packet.getPid() == ProtocolInfo.VIEW_PACKET) {
            ViewPacket viewPacket = (ViewPacket) packet;
            Server.getLogger().debug("Принято действие в UI.");
            if (!this.getServer().dispatchView(this, this.action.split("do=")[0] + viewPacket.getRequest())) {
                Server.getLogger().debug("При выполнении действия что-то пошло не так.");
            }
        } else if (packet.getPid() == ProtocolInfo.MENU_PACKET) {
            this.currentInterface.handle(this, ((MenuPacket) packet).getChoice());
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

    public String getAction() {
        return action;
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

    @Override
    public boolean hasPermission(String permission) {
        return this.permissions.stream().anyMatch(s -> s.equals(permission));
    }

    @Override
    public List<String> getEffectivePermissions() {
        return new ArrayList<>(this.permissions);
    }

    @Override
    public void recalculatePermissions() {
        this.permissions = this.getServer().getDefaultPermissions(this);
    }
}
