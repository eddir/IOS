package рф.пинж.command;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean execute(CommandSender sender, String commandLabel, String[] args);

    public String getName() {
        return this.name;
    }

}
