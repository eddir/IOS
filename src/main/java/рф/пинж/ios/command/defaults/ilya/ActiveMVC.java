package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

import java.lang.reflect.InvocationTargetException;

public class ActiveMVC extends Command {

    public ActiveMVC() {
        super("ilya");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Server.getInstance().dispatchView(sender, "ilya/institute/institutes");
        return true;
    }
}
