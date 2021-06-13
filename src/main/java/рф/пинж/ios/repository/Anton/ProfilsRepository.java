package рф.пинж.ios.repository.Anton;
import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.Anton.Profils;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ProfilsRepository extends Repository<Profils> implements IRepository<Profils> {
    public ProfilsRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(Profils.class);
    }
    public static void addNewProfil(String...args){
        try{
            Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO edupProfils VALUES (NULL," + "\"" + args[0] + "\"" + ")");

            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
    public static void delProfil(String...args){
        try{
            Query query2 = Server.getInstance().getDatabase().createQuery("DELETE FROM edupProfils WHERE id = " + args[0]);
            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
    public static List<Integer> getAllProfils(){
        Query query = Server.getInstance().getDatabase().createQuery("SELECT id FROM edupProfils");
        List<Integer> outId = query.executeScalarList(Integer.class);
        return outId;
    }
}
