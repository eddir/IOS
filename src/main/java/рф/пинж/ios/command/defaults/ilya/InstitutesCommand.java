package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.ilya.Institute;

public class InstitutesCommand extends Command {

    public InstitutesCommand() {
        super("institutes");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage("\tСписок институтов:\n\r" + Institute.titlesToString());
        return true;
    }
}
