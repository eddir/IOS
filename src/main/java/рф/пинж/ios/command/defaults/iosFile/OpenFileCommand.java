package рф.пинж.ios.command.defaults.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

public class OpenFileCommand extends Command {
    public OpenFileCommand() { super("openFile"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return Server.getInstance().dispatchView(sender, "iosFile/iosFile/file?do=" + args[0]);
    }
}
