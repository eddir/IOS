package рф.пинж.ios.controller.action;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.view.element.Input;

/**
 * Пользовательский ввод данных
 */
public class InputAction implements Action{

    // Кто будет обрабатывать ввод
    private Input handler;

    private String message;

    private String input;

    public InputAction(Input handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender) {
        sender.sendView(handler);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
