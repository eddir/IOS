package рф.пинж.ios.controller.action;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;

/**
 * Действие с командой. Выполняется определённая команда.
 */
public class CommandAction implements Action{
    // Команда с аргументами
    private String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender) {
        Server.getInstance().dispatchCommand(sender, command);
    }
}
