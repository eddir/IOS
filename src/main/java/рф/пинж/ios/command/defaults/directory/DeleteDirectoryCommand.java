package рф.пинж.ios.command.defaults.directory;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.directory.Directory;
import рф.пинж.ios.repository.directory.DirectoryRepository;
import рф.пинж.ios.utils.MainLogger;

public class DeleteDirectoryCommand extends Command {
    public DeleteDirectoryCommand() { super("deleteDirectory"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try {
            Directory directory = (new DirectoryRepository()).get(Integer.parseInt(args[0]));
            (new DirectoryRepository()).delete(directory);

            return Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + directory.getParent_dir());
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
            return false;
        }
    }
}
