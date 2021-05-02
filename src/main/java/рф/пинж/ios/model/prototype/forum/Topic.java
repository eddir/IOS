package рф.пинж.ios.model.prototype.forum;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.model.Model;

import java.util.List;

public class Topic extends Model implements IModel {

    {
        super.table = "topics";
        super.columns = List.of("id", "title", "created_at");
    }

    private String title;

    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
