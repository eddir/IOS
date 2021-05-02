package рф.пинж.ios.controller.forum;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.repository.forum.TopicRepository;
import рф.пинж.ios.view.forum.TopicView;

import java.lang.reflect.InvocationTargetException;

public class TopicController extends Controller {
    public TopicController() {
        super(null, new TopicView(null));
    }

    @URL("first")
    public void showFirstTopic(CommandSender sender, String request) {
        try {
            this.model = (new TopicRepository()).get(2);
            this.view.setModel(this.model);
            sender.sendView(this.view);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
