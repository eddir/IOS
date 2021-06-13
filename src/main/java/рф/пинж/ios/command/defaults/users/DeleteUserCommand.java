package рф.пинж.ios.command.defaults.users;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.users.UserRepository;

public class DeleteUserCommand extends Command {
    public DeleteUserCommand() {
        super("delete");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 1) {
            commandNotSupportedSuchArguments(sender, args.length);
            return false;
        }
        int id = 0;
        try {
            id = Integer.parseInt(args[0]);
        } catch (Exception ignored) {
        }
        if (id == 0)
            id = UserRepository.getIdByLogin(args[0]);
        int countRow;
        countRow = UserRepository.deleteById(id);
        sender.sendMessage("Затронут" + (countRow==0?"о":"а") + " " + countRow + " строк" + (countRow==0?"":"а") + ".");
        return true;
    }
}
