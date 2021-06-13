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

    private static Integer counterRecords() {
        try {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT COUNT(id) FROM institutes");
            return query.executeScalar(Integer.class);
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static boolean insertNewInstitute(String title, String abbreviation) {

        Integer temp = counterRecords();

        try {
            Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO institutes " +
                    "VALUES (" + (temp + 1) + ", \"" + title + "\", \"" + abbreviation + "\", NULL)");
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }

    public static boolean deleteInstitute(int id) {
        try {
            Query query = Server.getInstance().getDatabase()
                                .createQuery("DELETE FROM institutes WHERE id = " + id);
            query.executeUpdate();
            return true;
        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }

    public static String getPart(int id,int key) {

        String temp = "*";

        try {

            switch (key) {
                case 1:
                    temp = "title";
                    break;
                case 2:
                    temp = "abbreviation";
                    break;
            }

            Query query = Server.getInstance().getDatabase()
                    .createQuery("SELECT " + temp + " FROM institutes WHERE id = " + id);

            return query.executeScalar(String.class);

        } catch (Sql2oException exception) {
            MainLogger.getLogger().error(exception.getMessage());
            return null;
        }
    }

    public static boolean update(String key, int id, String newValue) {
        switch (key) {
            case "title":
                try {
                    Query query = Server.getInstance().getDatabase().createQuery("UPDATE institutes SET title = \"" + newValue + "\" WHERE id = " + id);
                    query.executeUpdate();
                } catch (Sql2oException exception) {
                    MainLogger.getLogger().error(exception.getMessage());
                    return false;
                }
                break;
            case "abb":
                try {
                    Query query = Server.getInstance().getDatabase().createQuery("UPDATE institutes SET abbreviation = \"" + newValue + "\" WHERE id = " + id);
                    query.executeUpdate();
                } catch (Sql2oException exception) {
                    MainLogger.getLogger().error(exception.getMessage());
                    return false;
                }
                break;
        }

        return true;
    }
}
