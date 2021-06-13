package рф.пинж.ios.command.defaults;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.utils.MainLogger;
//Связывает уже СУЩЕСТВУЮЩИЕ предметы с учебным планом
public class AddSubjectInEduPlanCommand extends Command {
    public AddSubjectInEduPlanCommand(){
        super("addSubInPlan");
    }

    @Override
    //args[0] - idPlan, args[n > 0] - idSub для добавления в учебный план
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            if(args.length >= 2){
                for(int i = 1;i < args.length;i++){
                    Query query = Server.getInstance().getDatabase().createQuery("INSERT INTO subjectsInEduPlan VALUES (" + args[i] + "," + args[0] + ")");
                    query.executeUpdate();
                }

            }
            return true;
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }
}
