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

public class DirectionRepository extends Repository<Institute> implements IRepository<Institute> {

    public DirectionRepository(Class<Institute> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(type);
    }

    public static List<Integer> getAllId(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT id FROM `directions` WHERE idCathedra = " + id);
            return query.executeScalarList(Integer.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static String getTitle(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT title FROM `directions` WHERE idCathedra = " + id);
            return query.executeScalar(String.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static boolean deleteDirection(int id) {
        try {
            Query query = Server.getInstance().getDatabase()
                    .createQuery("DELETE FROM directions WHERE id = " + id);
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }

    public static boolean insertNewDirection(int temp, String title, int idi) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO directions (id, title, idCathedra) " +
                    "VALUES (" + temp + ", \"" + title + "\", " + idi + ")");
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }

    public static List<String> getAllTitles(int idCathedra) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT title FROM `directions` WHERE idCathedra = " + idCathedra);
            return query.executeScalarList(String.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }
}
