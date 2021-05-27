package рф.пинж.ios.repository.Anton;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.Anton.Subject;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;


import java.lang.reflect.InvocationTargetException;

public class SubjectRepository extends Repository<Subject> implements IRepository<Subject> {
    public SubjectRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(Subject.class);
    }
    public static void create(int id,String description){
        Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO subject VALUES(" + id + ",\"" + description + "\"");
        query2.executeBatch();
    }
}
