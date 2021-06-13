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
    //description , idProfil
    //Создание Subject только в таблице subject
    public static void create(String description){
        int idDir = 1; //МЕТОД СОЗДАНИЯ РЕПОЗ
        int idTopic = 1;//МЕТОД СОЗДАНИЯ ФОРУМА

        Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO subject (description,PathToDirect,idTopic) VALUES(" + "\"" + description + "\"" + "," + idDir + "," + idTopic + ")");
        query2.executeUpdate();
    }
    //Создание Subject в таблице subject и связать предмет с учебным планом
    public static void createAndAddToEduPlan(String description, int idPlan){
        //Проверка на существование такого плана
        int idDir = 1; //МЕТОД СОЗДАНИЯ РЕПОЗ
        int idTopic = 1;//МЕТОД СОЗДАНИЯ ФОРУМА
        Query query2 = Server.getInstance().getDatabase().createQuery("INSERT INTO subject (description,PathToDirect,idTopic) VALUES (" + "\"" + description + "\"" + "," + idDir + "," + idTopic + ")");
        Long keyIdSub = query2.executeUpdate().getKey(Long.class);

        Query query3 = Server.getInstance().getDatabase().createQuery("INSERT INTO subjectsInEduPlan VALUES(" + keyIdSub + "," + idPlan + ")");
        query3.executeUpdate();
    }
    public static void deleteById(int idSub){
        Query query2 = Server.getInstance().getDatabase().createQuery("DELETE FROM subject WHERE id = " + idSub);
        //Query query2 = Server.getInstance().getDatabase().createQuery("DELETE FROM subjectsInEduPlan WHERE id = " + )
        query2.executeUpdate();
    }
    public static void setTeacher(int idSubject,int idTeacher){
        Query query = Server.getInstance().getDatabase().createQuery("UPDATE subject SET idTeacher = " + idTeacher + " WHERE id = " + idSubject);
        query.executeUpdate();
    }
}
