package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

public class RegisterCommand extends Command {

    public RegisterCommand() {
        super("reg");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return true;
    }
}
