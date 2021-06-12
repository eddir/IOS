package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.ilya.Institute;
import рф.пинж.ios.repository.ilya.InstituteRepository;

public class InstituteCommand extends Command {

    public InstituteCommand() {
        super("institute");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        switch (args[0]) {
            case "create":

                if (args.length != 3) {
                    sender.sendMessage("\"create\" ожидает 2 параметра.");
                }

                boolean result = InstituteRepository.insertNewInstitute(args[1], args[2]);
                if (result) {
                    sender.sendMessage("Институт добавлен!");
                } else {
                    sender.sendMessage("Что-то пошло не так!");
                }
                break;
            case "delete":

                if (args.length != 2) {
                    sender.sendMessage("\"delete\" ожидает 1 параметр (аббревиатуру)");
                }

                boolean result2 = InstituteRepository.deleteInstitute(Integer.parseInt(args[1]));
                if (result2) {
                    sender.sendMessage("Институт удален!");
                } else {
                    sender.sendMessage("Что-то пошло не так!");
                }
                break;
            case "update":
                // args[0] - айди института
                // args[1] - title
                // args[2] - abb
                Institute temp = new Institute(Integer.parseInt(args[0]));

                if (args[1].isEmpty() || args[1] == null) {
                    sender.sendMessage("Что-то пошло не так!");
                } else {
                    temp.setTitle(args[1]);
                }

                if (args[2].isEmpty() || args[2] == null) {
                    sender.sendMessage("Что-то пошло не так!");
                } else {
                    temp.setTitle(args[2]);
                }

                break;
        }

        return true;
    }
}
