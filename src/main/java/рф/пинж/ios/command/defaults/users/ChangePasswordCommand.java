package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.users.UserRepository;
import static рф.пинж.ios.utils.Utils.isValidPassword;

public class ChangePasswordCommand extends Command {
    public ChangePasswordCommand() {
        super("change-pwd");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 2) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        try {
            if (sender.getUser() != null) {
                if (args[0].equals(args[1]))
                    if (isValidPassword(args[0], sender))
                        UserRepository.changeUserPassword(sender.getUser().getLogin(), args[0]);
                else
                    sender.sendMessage("Пароли не совпадают.");
            }
            else
                sender.sendMessage("Вы не можете сменить пароль без авторизации в системе ИОС");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
