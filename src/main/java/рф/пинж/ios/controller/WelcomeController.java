package рф.пинж.ios.controller;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.model.prototype.forum.Topic;
import рф.пинж.ios.repository.forum.TopicRepository;
import рф.пинж.ios.view.WelcomeView;

public class WelcomeController extends Controller {
    public WelcomeController() {
        super(null, new WelcomeView());
    }

    @URL("index")
    public void index(CommandSender sender, String request) {
        sender.sendView(view);
    }

    @URL("about")
    public void about(CommandSender sender, String request) {
        Topic topic = TopicRepository.getFirst();
        this.view.setModel(topic);
        sender.sendView(view);
    }
}
