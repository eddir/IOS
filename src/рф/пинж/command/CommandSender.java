package рф.пинж.command;

import рф.пинж.Server;

public interface CommandSender {
    void sendMessage(String message);

    Server getServer();

    boolean isClient();
}
