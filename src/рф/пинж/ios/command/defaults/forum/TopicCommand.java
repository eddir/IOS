package рф.пинж.ios.command.defaults.forum;

import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.prototype.forum.Topic;

import java.util.List;

public class TopicCommand extends Command {
    public TopicCommand() {
        super("topic");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage(Topic.getFirstTitle());

        List<Topic> topics = Topic.getAll();
        assert topics != null;
        sender.sendMessage(topics.get(topics.size() - 1).getTitle());
        return true;
    }
}
