package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.defaults.ilya.InstituteController;
import рф.пинж.ios.model.prototype.ilya.Cathedra;
import рф.пинж.ios.model.prototype.ilya.Institute;

public class CathedrasCommand extends Command {

    public CathedrasCommand() {
        super("cathedras");
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Institute temp = new Institute(Integer.parseInt(args[0]));
        sender.sendMessage("\tСписок кафедр " + temp.getTitle() + ":\n\r" + Cathedra.titlesToString(temp.getId()));
        return true;
    }
}
