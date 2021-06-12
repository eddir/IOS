package рф.пинж.ios.command;

import рф.пинж.ios.Server;
import рф.пинж.ios.permission.Permissible;
import рф.пинж.ios.view.View;

public interface CommandSender extends Permissible {

    /**
     * Вывести обычное текстовое сообщение
     * @param message тело сообщения
     */
    void sendMessage(String message);

    /**
     * Вывести полноценное окно с отформатированными данными в консоли
     */
    void sendView(View body);

    void setAction(String action);

    Server getServer();

    boolean isClient();
}
