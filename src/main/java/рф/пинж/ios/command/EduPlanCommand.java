package рф.пинж.ios.command;

import рф.пинж.ios.Server;
import рф.пинж.ios.controller.action.ViewAction;
import рф.пинж.ios.controller.defaults.Anton.EduPlanController;
import рф.пинж.ios.model.Anton.EduPlan;
import рф.пинж.ios.repository.Anton.EduPlanRepository;
import org.sql2o.Query;
import org.sql2o.Sql2oException;


public class EduPlanCommand extends Command{
    public EduPlanCommand(){
        super("eduPlan");
    }

    @Override
    //название, id профиля
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        switch (args[0]){
            case "create":
                EduPlanRepository.addNewPlan(args[1],args[2]);
                break;
            case "delete":
                Query query = Server.getInstance().getDatabase().createQuery("DELETE FROM subjectsInEduPlan WHERE idEduPlan = " + args[0]);
                query.executeUpdate();
                EduPlanRepository.delPlan(Integer.parseInt(args[1]));
                break;
            case "addSub":
                EduPlanRepository.addSubsInPlan(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
                break;
            case "delSub":
                EduPlanRepository.delSubInPlan(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
                (new EduPlanController.DelSubjectController(Integer.parseInt(args[1]))).execute(sender);
                //new EduPlanController.DelSubjectController.execut(Integer.parseInt(args[1]) , null);
                break;
        }
        return true;
    }
}
