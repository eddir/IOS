package рф.пинж.ios.controller.forum;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.repository.forum.TopicRepository;
import рф.пинж.ios.view.forum.TopicView;

public class TopicController extends Controller {
    public TopicController() {
        super(null, new TopicView(null));
    }

    @URL("first")
    public void showFirstTopic(CommandSender sender, String request) {
        this.model = TopicRepository.getFirst();
        this.view.setModel(this.model);
        sender.sendView(this.view);
    }

}
