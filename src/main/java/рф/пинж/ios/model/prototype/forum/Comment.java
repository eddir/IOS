package рф.пинж.ios.model.prototype.forum;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.model.Model;

import java.util.List;

public class Comment extends Model implements IModel {

    {
        super.table = "comments";
        super.columns = List.of("id", "author", "body");
    }

    private int id;

    private String author;

    private String body;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
