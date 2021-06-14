package рф.пинж.ios.command.defaults.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.InputAction;
import рф.пинж.ios.controller.action.InputableAction;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Input;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;

public class SetFileCommand extends Command {
    public SetFileCommand() { super("setFile"); }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return Server.getInstance().dispatchView(sender, "iosFile/iosFile/create?do=" + args[0]);
    }
}
