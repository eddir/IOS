package рф.пинж.ios.command.defaults;

import рф.пинж.ios.IOS;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.permission.Permission;

public class VersionCommand extends Command {
    public VersionCommand() {
        super("version");
    }

    @Override
    @Permission("command.version")
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage(String.format("Версия прототипа ИОС - %s", IOS.version));
        return true;
    }
}
