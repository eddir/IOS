package рф.пинж.ios.controller.action;

import рф.пинж.ios.command.CommandSender;

/**
 * Action - это действие в CLI, которое производится пользователем.
 */
public interface Action {

    /**
     * При нажатии на опцию в меню будет вызван этот метод
     */
    public void execute(CommandSender sender);

}
