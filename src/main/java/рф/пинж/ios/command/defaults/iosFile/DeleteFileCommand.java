package рф.пинж.ios.command.defaults.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;

public class DeleteFileCommand extends Command {
    public DeleteFileCommand() { super("deleteFile"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try {
            IosFile file = (new IosFileRepository()).get(Integer.parseInt(args[0]));
            (new IosFileRepository()).delete(file);

            return Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + file.getIdDirectory());
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
            return false;
        }
    }
}
