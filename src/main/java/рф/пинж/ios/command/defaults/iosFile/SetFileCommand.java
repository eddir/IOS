package рф.пинж.ios.command.defaults.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;

import java.util.Scanner;

public class SetFileCommand extends Command {
    public SetFileCommand() { super("setFile"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Scanner in = new Scanner(System.in);
        sender.sendMessage("Введите название файла: ");
        String fileName = in.nextLine();
        sender.sendMessage("\r\nВведите url файла: ");
        String url = in.nextLine();

        try {
            (new IosFileRepository()).save(new IosFile(fileName, Integer.parseInt(args[0]), url));
            return Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + args[0]);
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
            return false;
        }
    }
}
