package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.users.UserRepository;

public class RestorePasswordCommand extends Command {
    public RestorePasswordCommand() {
        super("restore-pwd");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 0) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        try {
            Server.getInstance().dispatchView(sender, "users/restorePassword/restore_pwd");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
