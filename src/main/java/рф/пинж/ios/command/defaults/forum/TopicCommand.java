package рф.пинж.ios.command.defaults.forum;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.forum.TopicController;
import рф.пинж.ios.model.prototype.forum.Topic;
import рф.пинж.ios.repository.forum.TopicRepository;
import рф.пинж.ios.view.forum.TopicView;

import java.util.List;

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
