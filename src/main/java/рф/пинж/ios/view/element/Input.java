package рф.пинж.ios.view.element;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.action.InputableAction;
import рф.пинж.ios.model.IModel;
import рф.пинж.ios.view.View;

public class Input extends View implements Interface {
    private final InputableAction handler;
    private final String message;

    public Input(InputableAction handler, String message) {
        super(null);
        this.handler = handler;
        this.message = message;
    }

    public String prepare() {
        return message;
    }

    @Override
    public boolean handle(CommandSender sender, String input) throws Exception {
        this.handler.execute(sender, input);
        return true;
    }
}
