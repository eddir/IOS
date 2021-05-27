package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.users.User;
import рф.пинж.ios.repository.users.UserRepository;

public class AuthComand extends Command {
    public AuthComand() {
        super("auth");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 2) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        User user = UserRepository.getUserByLogin(args[0]);
        if (user != null) {
            if (user.isCorrectPassword(args[1]))
                sender.setUser(user);
            else
                sender.sendMessage("Введён неправильный пароль");
        }
        else {
            sender.sendMessage("Логина " + args[0] + " не существует в системе ИОС.");
        }
        return true;
    }
}
