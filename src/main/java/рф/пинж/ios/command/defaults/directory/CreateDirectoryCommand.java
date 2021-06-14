package рф.пинж.ios.command.defaults.directory;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

public class CreateDirectoryCommand extends Command {
    public CreateDirectoryCommand() { super("createDirectory"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return Server.getInstance().dispatchView(sender, "directory/directory/create?do=" + args[0]);
    }
}
