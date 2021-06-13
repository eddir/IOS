package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

public class QuitCommand extends Command {
    public QuitCommand() {
        super("quit");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 0) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        sender.setUser(null);
        Server.getInstance().dispatchView(sender, "users/auth/signIn");
        return true;
    }
}
