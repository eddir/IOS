package рф.пинж.ios.command.defaults.directory;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.directory.Directory;
import рф.пинж.ios.repository.directory.DirectoryRepository;

import java.util.List;

public class OpenDirectoryCommand extends Command {
    public OpenDirectoryCommand() {
        super("openDirectory");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + args[0]);
    }
}
