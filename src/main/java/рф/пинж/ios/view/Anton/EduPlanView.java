package рф.пинж.ios.view.Anton;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.model.Anton.Subject;
import рф.пинж.ios.view.View;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;
import java.util.List;

public class EduPlanView extends View{
    //int idProfil = 10;
    public EduPlanView(){
        super(null);
    }
    public EduPlanView(int idGroup){
        this();
        //if sender.getClass != Student -> throw new Exception
        //int idPlan  = "SELECT idPlan FROM groups WHERE id = sender.idGroup"
        //int idPlan = 10; // поднимай глаза

    }
    /*
    @Override
    public String prepare() {
        StringBuilder menu = new StringBuilder();
        Query query = Server.getInstance().getDatabase().createQuery("SELECT * FROM subject WHERE id IN (SELECT id FROM subjectsInEduPlan WHERE idEduPlan = " + idProfil + ")");
        List<Subject> subj = query.executeScalarList(Subject.class);
        //LinkedHashMap<String, Action> menuElements= new LinkedHashMap<>();
        StringBuilder buf2 = new StringBuilder();
        for(Subject buf:subj){
           buf2.append(buf.getDescription());
           buf2.append("\r\n");
        }
        //Menu main = new Menu("Список учебный предметов",menuElements);
        return buf2.toString();
    }
     */
}
