package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.ilya.Institute;
import рф.пинж.ios.repository.ilya.InstituteRepository;

public class InstituteCommand extends Command {

    public InstituteCommand() {
        super("institute");
    }

    // args[1] - айди института
    // args[2] - title
    // args[3] - abb

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
                if (args.length != 4) {
                    sender.sendMessage("Что-то пошло не так!");
                } else {
                    Institute temp = new Institute(Integer.parseInt(args[1]));
                    temp.setTitle(args[2]);
                    temp.setAbbreviation(args[3]);
                }
                break;
        }

        return true;
    }
}
