package рф.пинж.prototype.forum;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.Model;
import рф.пинж.Server;
import рф.пинж.utils.MainLogger;

import java.util.List;

public class Topic implements Model {

    private int id;

    private String title;

    private String created_at;

    public static List<Topic> getAll() {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT * FROM `topics`");
            return query.executeAndFetch(Topic.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
        }
        return null;
    }

    public static String getFirstTitle() {
        return Server.getInstance().getDatabase().createQuery(
                "SELECT title FROM topics LIMIT 1"
        ).executeScalar(String.class);
    }

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
