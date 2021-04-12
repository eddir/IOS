package рф.пинж.command.defaults;

import рф.пинж.IOS;
import рф.пинж.command.Command;
import рф.пинж.command.CommandSender;

public class VersionCommand extends Command {
    public VersionCommand() {
        super("version");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage(String.format("Версия прототипа ИОС - %s", IOS.version));
        return true;
    }
}
