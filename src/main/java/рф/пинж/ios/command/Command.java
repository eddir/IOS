package рф.пинж.ios.command;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean execute(CommandSender sender, String commandLabel, String[] args);

    public String getName() {
        return this.name;
    }

    public String getEnding(int argsLength) {
        if (argsLength % 10 == 1 && argsLength % 100 != 11)
            return "";
        else if (argsLength % 10 > 4 || argsLength % 10 < 2)
            return "ов";
        return "а";
    }

    public void commandNotSupportedSuchArguments(CommandSender sender, int argsLength) {
        sender.sendMessage("Комманда auth не принимает " + argsLength + " аргумент" + getEnding(argsLength));
    }
}
