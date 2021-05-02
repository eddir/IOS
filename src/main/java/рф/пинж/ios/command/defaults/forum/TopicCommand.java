package рф.пинж.ios.command.defaults.forum;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

public class TopicCommand extends Command {
    public TopicCommand() {
        super("topic");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Server.getInstance().dispatchView(sender, "forum/topic/first");
        return true;
    }
}
