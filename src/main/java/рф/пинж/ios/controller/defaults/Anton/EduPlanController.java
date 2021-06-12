package рф.пинж.ios.controller.Anton;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.ViewAction;
import рф.пинж.ios.model.Anton.Subject;
import рф.пинж.ios.view.Anton.EduPlanView;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;
import java.util.List;

public class EduPlanController extends Controller  {
    public EduPlanController(){
        super(null,new EduPlanView());
    }
    @URL("allSub")
    public void allSub(CommandSender sender,String request)
    {
        //Если это не студент - ошибка
        Menu buf = new Menu(new LinkedHashMap<>(){
            {
                Query query = Server.getInstance().getDatabase().createQuery("SELECT * FROM subject WHERE id IN (SELECT id FROM subjectsInEduPlan WHERE idEduPlan = " + 10 + ")");
                List<Subject> subj = query.executeScalarList(Subject.class);
                for(Subject buf1 : subj){
                    put(buf1.getDescription(),new ViewAction("anton/eduplan/subView"));
                }
            }
        });
        sender.sendView(buf); //в аргументе должен быть указан idProfil , по умолчанию он равен 10
    }
    @URL("subView")
    public void subView(CommandSender sender){
        sender.sendMessage("Тест_1");
    }
    /*
    record SubjectAction(String message) implements Action {
        @Override
        public void execute(CommandSender sender) {
            try {
                Thread.sleep(2000);
                Server.getInstance().dispatchView(sender, "welcome/index");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
     */
}
