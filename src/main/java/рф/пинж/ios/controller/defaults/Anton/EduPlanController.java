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
                Query query4 = Server.getInstance().getDatabase().createQuery("SELECT idTopic FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");


                List<String> subj = query.executeScalarList(String.class);
                List<String> idSubj = query2.executeScalarList(String.class);
                List<String> idPath = query3.executeScalarList(String.class);
                List<String> idTopics = query4.executeScalarList(String.class);

                ArrayList<Subject> subList = new ArrayList<>();
                for(int i = 0;i < subj.size();i++){
                    //subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i))));

                }
                for(Subject buf1 : subList){
                    put((buf1.getDescription()),new CommandAction("openDirectory " + buf1.getPathToDirect())); //"Anton/EduPlan/SubView"
                }
                //Если есть права, то...
                if(true){
                    put("Удалить дисциплину",new DelSubjectController(idPlan));
                }
                put("Выход",new ViewAction("welcome/index"));
                //put("Добавить дисциплину в учебный план",new CommandAction("eduPlan addSub " + 10 + " " + "idSub"));
            }
        });
        sender.sendView(buf); //в аргументе должен быть указан idProfil , по умолчанию он равен 10
    }

    public void subView(CommandSender sender){

    }
    public record DelSubjectController(int idPlan) implements Action {
        @Override
        @URL("subDel")
        public void execute(CommandSender sender){
            Menu choiceMenu = new Menu("Удаление дисциплин:",new LinkedHashMap<>(){
                {
                    Query query = Server.getInstance().getDatabase().createQuery("SELECT description FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                    Query query2 = Server.getInstance().getDatabase().createQuery("SELECT id FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                    Query query3 = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                    Query query4 = Server.getInstance().getDatabase().createQuery("SELECT idTopic FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");

                    List<String> subj = query.executeScalarList(String.class);
                    List<String> idSubj = query2.executeScalarList(String.class);
                    List<String> idPath = query3.executeScalarList(String.class);
                    List<String> idTopics = query4.executeScalarList(String.class);
                    ArrayList<Subject> subList = new ArrayList<>();
                    for(int i = 0;i < subj.size();i++){
                        subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i)),Integer.parseInt(idTopics.get(i))));
                    }
                    for(Subject buf : subList){
                        put(buf.getDescription(),new CommandAction("eduPlan delSub " + idPlan + " " + buf.getId()));
                    }
                    //put("Выход",new ViewAction("Anton/EduPlan/allSub"));
                    put("Назад", new CommandAction("getAllSub " + idPlan));
                    put("Выход",new ViewAction("welcome/index"));
                }
            });
            sender.sendView(choiceMenu);
        }

        }
        public record toPlan (String idPlan) implements Action{
            @Override
            @URL("planMenu")
            public void execute(CommandSender sender) {
                Menu buf = new Menu(new LinkedHashMap<>(){
                    {
                        Query query = Server.getInstance().getDatabase().createQuery("SELECT description FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                        Query query2 = Server.getInstance().getDatabase().createQuery("SELECT id FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                        Query query3 = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");
                        Query query4 = Server.getInstance().getDatabase().createQuery("SELECT idTopic FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");


                        List<String> subj = query.executeScalarList(String.class);
                        List<String> idSubj = query2.executeScalarList(String.class);
                        List<String> idPath = query3.executeScalarList(String.class);
                        List<String> idTopics = query4.executeScalarList(String.class);

                        ArrayList<Subject> subList = new ArrayList<>();
                        for(int i = 0;i < subj.size();i++){
                            subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i)),Integer.parseInt(idTopics.get(i))));

                        }
                        for(Subject buf1 : subList){
                            put((buf1.getDescription()),new doChoice(Integer.toString(buf1.getId()),idPlan)); //"Anton/EduPlan/SubView"
                        }
                        //Если есть права, то...
                        if(true){
                            put("Удалить дисциплину",new DelSubjectController(Integer.parseInt(idPlan)));
                        }
                        put("Выход",new ViewAction("welcome/index"));
                        //put("Добавить дисциплину в учебный план",new CommandAction("eduPlan addSub " + 10 + " " + "idSub"));
                    }
                });
                sender.sendView(buf); //в аргументе должен быть указан idProfil , по умолчанию он равен 10

            }
        }
    public record doChoice (String idSubject,String idPlan) implements Action{
        @Override
        @URL("choiceMenu")
        public void execute(CommandSender sender) {
            Query query = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id = " + idSubject);
            String idDirect = query.executeScalar(String.class);
            Query query2 = Server.getInstance().getDatabase().createQuery("SELECT idTopic FROM subject WHERE id = " + idSubject);
            String idForum = query2.executeScalar(String.class);
            Menu menu = new Menu("Выберете действие",new LinkedHashMap<>(){
                {
                    //if(idDirect != null && !idDirect.isEmpty() && !idDirect.equals("NULL"))
                    put("Перейти в каталог",new CommandAction("openDirectory " + idDirect));
                    //if(idDirect != null && !idDirect.isEmpty() && !idDirect.equals("NULL"))
                    put("Открыть форум", new CommandAction("openTopic " + idForum));
                    put("Назад",new toPlan(idPlan));//toPlan(idPlan)
                    //put("Выход",new CommandAction("getAllSub " + idPlan));
                    put("Выход",new ViewAction("welcome/index"));
                }
            });
            sender.sendView(menu);
        }
    }

}
