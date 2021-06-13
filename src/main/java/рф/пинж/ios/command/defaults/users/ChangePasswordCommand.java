package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.users.UserRepository;
import static рф.пинж.ios.utils.Utils.isValidPassword;
import static рф.пинж.ios.utils.Utils.sha512Generator;

public class ChangePasswordCommand extends Command {
    public ChangePasswordCommand() {
        super("change-pwd");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 3) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        try {
            if (sender.getUser() != null) {
                if (sender.getUser().getPassword().equals(sha512Generator(args[0])))
                    if (args[1].equals(args[2]))
                        if (isValidPassword(args[1])) {
                            sender.getUser().setPassword(sha512Generator(args[1]));
                            UserRepository.changeUserPassword(sender.getUser().getLogin(), args[1]);
                            sender.sendMessage("Пароль успешно изменён.");
                        }
                        else {
                            sender.sendMessage("\r\nПароль не соответствует требованиям безопасности");
                            sender.sendMessage("Пароль должен содержать большие и маленькие заглавные буквы.");
                            sender.sendMessage("Хотя бы 1 цифру или один из спецсимволы '@', '#', '$', '%', '!'.");
                            sender.sendMessage("Длина пароля от 8 до 40 символов");
                        }
                    else
                        sender.sendMessage("Пароли не совпадают.");
                else
                    sender.sendMessage("Указанный пароль не является паролем от данной учётной записи");
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
