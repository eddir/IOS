package рф.пинж.ios.repository.iosFile;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class IosFileRepository extends Repository<IosFile> implements IRepository<IosFile> {
    public IosFileRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(IosFile.class);
    }

    public List<IosFile> getNestedFiles(int idDirectory) {
        return Server.getInstance().getDatabase().createQuery("SELECT * FROM files WHERE idDirectory = " + idDirectory)
                .executeAndFetch(IosFile.class);
    }

    public void saveFile(IosFile file) {
        Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO files (file_name, idDirectory, url) VALUES (" +
                "\'" + file.getFile_name() + "\', " +
                file.getIdDirectory() + ", " +
                "\'" + file.getUrlFile() + "\')");
        query.executeUpdate();
    }
}
