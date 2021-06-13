package рф.пинж.ios.controller.defaults.Anton;

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

import java.util.ArrayList;
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
                int idPlan = 10; //функция получения idPlan у стуента
                Query query = Server.getInstance().getDatabase().createQuery("SELECT description FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                Query query2 = Server.getInstance().getDatabase().createQuery("SELECT id FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                Query query3 = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");

                List<String> subj = query.executeScalarList(String.class);
                List<String> idSubj = query2.executeScalarList(String.class);
                List<String> idPath = query3.executeScalarList(String.class);

                ArrayList<Subject> subList = new ArrayList<>();
                for(int i = 0;i < subj.size();i++){
                    subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i))));

                }
                for(Subject buf1 : subList){
                    put((buf1.getDescription()),new CommandAction("openDirectory " + buf1.getId())); //"Anton/EduPlan/SubView"
                }
                //Если есть права, то...
                if(true){
                    put("Удалить дисциплину",new DelSubjectController(idPlan,subList));
                }
                put("Выход",new ViewAction("welcome/index"));
                //put("Добавить дисциплину в учебный план",new CommandAction("eduPlan addSub " + 10 + " " + "idSub"));
            }
        });
        sender.sendView(buf); //в аргументе должен быть указан idProfil , по умолчанию он равен 10
    }

    public void subView(CommandSender sender){

    }
    public record DelSubjectController(int idPlan, List<Subject> subList) implements Action {
        public DelSubjectController(int idPlan){
            this(idPlan,new ArrayList<>());
        }
        @Override
        @URL("subDel")
        public void execute(CommandSender sender){
            if(this.subList.size() == 0){
                Query query = Server.getInstance().getDatabase().createQuery("SELECT description FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                Query query2 = Server.getInstance().getDatabase().createQuery("SELECT id FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                Query query3 = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");

                List<String> subj = query.executeScalarList(String.class);
                List<String> idSubj = query2.executeScalarList(String.class);
                List<String> idPath = query3.executeScalarList(String.class);

                for(int i = 0;i < subj.size();i++){
                    subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i))));
                }

            }
            sender.sendMessage("...\r\n");
            Menu choiceMenu = new Menu("Удаление дисциплин:",new LinkedHashMap<>(){
                {
                    for(Subject buf : subList){
                        put(buf.getDescription(),new CommandAction("eduPlan delSub " + idPlan + " " + buf.getId()));
                    }
                    put("Выход",new ViewAction("Anton/EduPlan/allSub"));
                }
            });
            sender.sendView(choiceMenu);
        }
    }

}
