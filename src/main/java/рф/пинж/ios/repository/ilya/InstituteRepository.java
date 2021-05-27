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

public class InstituteRepository extends Repository<Institute> implements IRepository<Institute> {

    //TODO: что-то сделать с этими исключениями
    public InstituteRepository(Class<Institute> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(Institute.class);
    }

    public static List<String> getAllTitles() {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT title FROM `institutes`");
            return query.executeScalarList(String.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static boolean insertNewInstitute(String title, String abbreviation) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO institutes " +
                    "VALUES (NULL, \"" + title + "\", \"" + abbreviation + "\", NULL)");
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }

    public static boolean deleteInstitute(String abbreviation) {
        try {
            Query query = Server.getInstance().getDatabase()
                                .createQuery("DELETE FROM institutes WHERE abbreviation LIKE \'" + abbreviation+ "\'");
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }
}
