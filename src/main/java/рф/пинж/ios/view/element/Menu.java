package рф.пинж.ios.view.element;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.View;

import java.util.*;

public class Menu extends View implements Interface {

    private final LinkedHashMap<String, Action> options;
    private String message = "Выберите действие:";

    public Menu(String message, LinkedHashMap<String, Action> options) {
        super(null);
        this.options = options;
        this.message = message;
    }

    public Menu(LinkedHashMap<String, Action> options) {
        super(null);
        this.options = options;
    }

    @Override
    public String prepare() {
        StringBuilder page = new StringBuilder(this.message + "\r\n");

        int i = 1;
        for (Map.Entry<String, Action> entry : options.entrySet()) {
            page.append(String.format("%d. %s\r\n", i++, entry.getKey()));
        }
        page.append("\r\n");

        return page.toString();
    }

    public boolean handle(CommandSender sender, String action) {

        try {
            int i = 1;
            for (Map.Entry<String, Action> entry : options.entrySet()) {
                if (entry.getKey().equals(action)) {
                    options.get(action).execute(sender);
                    return true;
                }
                if (i++ == Integer.parseInt(action)) {
                    entry.getValue().execute(sender);
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        MainLogger.getLogger().debug("Неизвестное действие меню " + action);
        return false;
    }
}
