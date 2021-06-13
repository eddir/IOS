package рф.пинж.ios.repository.Anton;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.Anton.EduPlan;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;

public class EduPlanRepository extends Repository<EduPlan> implements IRepository<EduPlan> {
    public EduPlanRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EduPlan.class);
    }
    public static void addNewPlan(String...args){
        try{
            //description , idProfil
            Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO eduPlan values(0,\"" + args[0] + "\"," + args[1] + ")");
            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
    public static void delPlan(int id){
        try{
            Query query2 = Server.getInstance().getDatabase().createQuery("DELETE FROM eduPlan WHERE id = " + id);
            query2.executeUpdate();
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
    }
    public static void addSubsInPlan(int idPlan,int idSub){
        //Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO subjectsInEduPlan (idEduPlan,idSubject) VALUES (" + idPlan + "," + idSub + ")");
        Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO subjectsInEduPlan (idEduPlan,idSubject) VALUES ("+ idPlan + "," + idSub +")");
        query.executeUpdate();

    }
    public static void delSubInPlan(int idPlan,int idSub){
        Query query = Server.getInstance().getDatabase().createQuery("DELETE FROM subjectsInEduPlan WHERE (idSubject = " + idSub + " AND " + "idEduPlan = " + idPlan + ")");
        query.executeUpdate();
    }
    //public static void showAllSubIn
}
