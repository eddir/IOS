package рф.пинж.ios.controller.action;

import рф.пинж.ios.command.CommandSender;

import static рф.пинж.ios.Server.getInstance;

/**
 * Действие с видами. Вызывается окно (вид) и управление передаётся ему.
 */
public class ViewAction implements Action{
    // Ссылка на вид.
    private String url;

    public ViewAction(String url) {
        this.url = url;
    }

    public void execute(CommandSender sender) {
        getInstance().dispatchView(sender, url);
    }

}
