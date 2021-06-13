package рф.пинж.ios.repository.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;

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
}
