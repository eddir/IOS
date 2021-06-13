package рф.пинж.ios.command;

import java.lang.reflect.InvocationTargetException;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean execute(CommandSender sender, String commandLabel, String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    public String getName() {
        return this.name;
    }

}
