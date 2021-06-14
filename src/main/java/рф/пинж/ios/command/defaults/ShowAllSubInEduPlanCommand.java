package рф.пинж.ios.command.defaults;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.ViewAction;
import рф.пинж.ios.controller.defaults.Anton.EduPlanController;
import рф.пинж.ios.model.Anton.Subject;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

public class ShowAllSubInEduPlanCommand extends Command {
    public ShowAllSubInEduPlanCommand(){
        super("getAllSub");
    }
    //args[0] - id учебного плана
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            //Получить все учебные предметы по id учебного плана
            //Получаем учебный план по idГруппы
            if(args.length == 0){
                //Server.getInstance().dispatchView(sender,"Anton/EduPlan/allSub");
            }
            else{

                Menu buf = new Menu(new LinkedHashMap<>(){
                    {
                        int idPlan = Integer.parseInt(args[0]); //функция получения idPlan у стуента
                        Query query = Server.getInstance().getDatabase().createQuery("SELECT description FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + args[0] + ")");
                        Query query2 = Server.getInstance().getDatabase().createQuery("SELECT id FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + args[0] + ")");
                        Query query3 = Server.getInstance().getDatabase().createQuery("SELECT PathToDirect FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + args[0] + ")");
                        Query query4 = Server.getInstance().getDatabase().createQuery("SELECT idTopic FROM subject WHERE id IN (SELECT idSubject FROM subjectsInEduPlan WHERE idEduPlan = " + idPlan + ")");

                        List<String> subj = query.executeScalarList(String.class);
                        List<String> idSubj = query2.executeScalarList(String.class);
                        List<String> idPath = query3.executeScalarList(String.class);
                        List<String> idTopics = query4.executeScalarList(String.class);

                        //Id предмета, описание придмета, путьКдирректорииПредмета, args[0] - idПлана
                        ArrayList<Subject> subList = new ArrayList<>();
                        //sender.sendMessage(Integer.toString(subj.size()));
                        for(int i = 0;i < subj.size();i++){

                            subList.add(new Subject(subj.get(i),Integer.parseInt(idSubj.get(i)),Integer.parseInt(idPath.get(i)),Integer.parseInt(idTopics.get(i))));

                        }
                        //sender.sendMessage("!!!ТУТ!!!");
                        for(Subject buf1 : subList){
                            // Переход в дисцилину
                            put((buf1.getDescription()),new EduPlanController.doChoice(Integer.toString(buf1.getId()),args[0])); //"Anton/EduPlan/SubView"
                        }
                        //Если есть права, то...
                        if(true){
                            put("Удалить дисциплину",new EduPlanController.DelSubjectController(idPlan));
                        }
                        put("Выход",new ViewAction("welcome/index"));
                        //мы ввели idPlan
                        //put("Выход",new EduPlanController.doChoice(args[0]);

                        //put("Добавить дисциплину в учебный план",new CommandAction("eduPlan addSub " + 10 + " " + "idSub"));
                    }
                });
                sender.sendView(buf);
            }
            return true;
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }
}
