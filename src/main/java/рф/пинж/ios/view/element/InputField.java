package рф.пинж.ios.view.element;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.utils.MainLogger;

import java.util.Map;

public class InputField implements Interface {

    @Override
    public boolean handle(CommandSender sender, String action) {
        try {

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        MainLogger.getLogger().debug("Неизвестное действие меню " + action);
        return false;
    }
}
