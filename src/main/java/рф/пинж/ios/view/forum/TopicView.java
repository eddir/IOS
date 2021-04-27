package рф.пинж.ios.view.forum;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.model.prototype.forum.Topic;
import рф.пинж.ios.view.View;

public class TopicView extends View {

    public TopicView(Topic model) {
        super(model);
    }

    public String prepare() {
        return ((Topic)this.model).getTitle();
    }
}
