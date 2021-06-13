package рф.пинж.ios.command.defaults.forum;

import org.sql2o.Query;
import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;

import java.util.List;

public class MyEduPlanCommand extends Command {
    public MyEduPlanCommand(){
        super("myEduPlan");
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            //Проверка если на sender.getClass == "Student"
            //Получаем по id_группы принадлежность студента к учебному плану
            //
            //showAllSubInEduPlanCommand(sender,commandLabel,args)
            Query query1 = Server.getInstance().getDatabase().createQuery("SELECT * FROM subject WHERE id IN (SELECT DISTINCT id FROM subjectsInEduPlan WHERE idEduPlan = " + args[0] + ")");
            List<String> outList = query1.executeScalarList(String.class);
            for(String buf1:outList){
                sender.sendMessage(buf1.toString());
            }
            return true;
        }
        catch(Exception err){
            sender.sendMessage(err.getMessage()); //поправить
            return false;
        }
    }
}
