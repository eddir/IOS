package рф.пинж.ios.command.defaults.ilya;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.ilya.InstituteRepository;

public class InstituteCommand extends Command {

    public InstituteCommand() {
        super("institute");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        switch (args[0]) {
            case "create":
                InstituteRepository.insertNewInstitute(args[1], args[2]);
                sender.sendMessage("Институт добавлен!");
                break;
            case "delete":
                InstituteRepository.deleteInstitute(args[1]);
                sender.sendMessage("Институт удален!");
                break;
        }

        return true;
    }
}
