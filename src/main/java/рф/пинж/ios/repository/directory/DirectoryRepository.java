package рф.пинж.ios.repository.directory;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.directory.Directory;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class DirectoryRepository extends Repository<Directory> implements IRepository<Directory> {
    public DirectoryRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(Directory.class);
    }

    public List<Directory> getChildren(int id) {
        return Server.getInstance().getDatabase().createQuery("SELECT * FROM directories WHERE parent_dir = " + id)
                .executeAndFetch(Directory.class);
    }

    public void saveDirectory(Directory directory) {
        Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO directories (title, created_at, parent_dir) VALUES (" +
                "\'" + directory.getTitle() + "\', \'" +
                directory.getCreated_at() + "\', " +
                directory.getParent_dir() + ")");
        query.executeUpdate();
    }
}
