package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.ilya.Direction;
import рф.пинж.ios.model.prototype.ilya.Institute;

import java.lang.reflect.InvocationTargetException;

public class DirectionsCommand extends Command {

    public DirectionsCommand() {
        super("directions");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Institute temp = new Institute(Integer.parseInt(args[0]));
        sender.sendMessage("\tСписок направлений -> " + temp.getTitle() + " -> " + temp.getCathedras().get(Integer.parseInt(args[1]) - 1).getTitle()
                + ":\n\r" + Direction.titlesToString(temp.getCathedras().get(Integer.parseInt(args[1]) - 1).getId()));
        return true;
    }
}
