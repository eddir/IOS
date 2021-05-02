package рф.пинж.ios.repository.forum;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.forum.Topic;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;

public class TopicRepository extends Repository<Topic> implements IRepository<Topic> {

    //todo: что-то сделать с этими исключениями
    public TopicRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(Topic.class);
    }

    public static Topic getFirst() {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT * FROM `topics` LIMIT 1");
            return query.executeAndFetchFirst(Topic.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }
}
