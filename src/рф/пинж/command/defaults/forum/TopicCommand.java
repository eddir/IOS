package рф.пинж.command.defaults.forum;

import рф.пинж.command.Command;
import рф.пинж.command.CommandSender;
import рф.пинж.prototype.forum.Topic;

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
