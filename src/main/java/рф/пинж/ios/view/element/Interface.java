package рф.пинж.ios.view.element;

import рф.пинж.ios.command.CommandSender;

/**
 * Пользовательский интерфейс. Пошаговое взаимодействие в виде меню.
 */
public interface Interface {
    /**
     * Обработка выбора польщователя
     * @param sender пользователь, который пользуется интерфейсом
     * @param action выбранное действие пользователем
     */
    public boolean handle(CommandSender sender, String action);
}
