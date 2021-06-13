package рф.пинж.ios.controller.action;

import рф.пинж.ios.command.CommandSender;

public interface InputableAction{
    void execute(CommandSender sender, String input) throws Exception;
}
