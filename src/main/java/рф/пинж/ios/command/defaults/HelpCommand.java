package рф.пинж.ios.command.defaults;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.permission.Permission;

public class HelpCommand extends Command {
    public HelpCommand(String name) {
        super("Help");
    }

    public HelpCommand() {
        super("Help");
    }

    @Override
    @Permission("command.help")
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage("version - узнать версию сервера.");
        return true;
    }
}
