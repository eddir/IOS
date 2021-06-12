package рф.пинж.ios.repository.ilya;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.ilya.Institute;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CathedraRepository extends Repository<Institute> implements IRepository<Institute> {

    public CathedraRepository(Class<Institute> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(type);
    }

    public static List<Integer> getAllId(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT id FROM `cathedras` WHERE idInst = " + id);
            return query.executeScalarList(Integer.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static List<String> getAllTitles(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT title FROM `cathedras` WHERE idInst = " + id);
            return query.executeScalarList(String.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

}
