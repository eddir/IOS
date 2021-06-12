package рф.пинж.ios.repository.Anton;
import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.Anton.Profils;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;

public class ProfilsPerository extends Repository<Profils> {
    public ProfilsPerository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(Profils.class);
    }
    public static void addNewProfil(String...args){
        try{
            Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO edupProfils\n values(0"+ "," + args[0] + ")");
            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
    public static void delProfil(int id){
        try{
            Query query2 = Server.getInstance().getDatabase().createQuery("DELETE FROM edupProfils WHERE id = " + id);
            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
}
