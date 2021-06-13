package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.ilya.Cathedra;
import рф.пинж.ios.model.prototype.ilya.Institute;
import рф.пинж.ios.repository.ilya.CathedraRepository;

public class CathedraCommand extends Command {

    public CathedraCommand() {
        super("cathedra");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        switch (args[1]) {
            case "create":

                if (args.length != 3) {
                    sender.sendMessage("\"create\" ожидает 3 параметра.");
                } else {
                    Institute temp = new Institute(Integer.parseInt(args[0]));
                    Cathedra test = new Cathedra(CathedraRepository.getNewId(), temp, args[2]);
                    if (temp.addCathedra(test)) {
                        sender.sendMessage("Кафедра добавлена!");
                    } else {
                        sender.sendMessage("Что-то пошло не так!");
                    }
                }

                break;
            case "delete":
                if (args.length != 3) {
                    sender.sendMessage("\"delete\" ожидает 3 параметра.");
                } else {
                    Institute temp = new Institute(Integer.parseInt(args[0]));
                    if (temp.removeCathedra(Integer.parseInt(args[2]))) {
                        sender.sendMessage("Кафедра удалена!");
                    } else {
                        sender.sendMessage("Что-то пошло не так!");
                    }
                }

                break;
        }

        return true;
    }
}
