package рф.пинж.ios.command;

import рф.пинж.ios.Server;

public interface CommandSender {
    void sendMessage(String message);

    Server getServer();

    boolean isClient();
}
