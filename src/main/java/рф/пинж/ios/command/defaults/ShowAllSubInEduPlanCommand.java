package рф.пинж.ios.command.defaults;

import org.sql2o.Query;
import org.sql2o.Sql2oException;
import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.utils.MainLogger;

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
            String buf = "CREATE VIEW test02\n" +
                    "AS\n" +
                    "SELECT s.description\n" +
                    "FROM subject s JOIN subjectsInEduPlan sIE ON s.id = sIE.idSubject\n" +
                    "WHERE sIE.idEduPlan = " + args[0];
            Query query = Server.getInstance().getDatabase().createQuery(buf);
            query.executeUpdate();

            Query query1 = Server.getInstance().getDatabase().createQuery("SELECT * FROM test02");
            List<String> outList = query1.executeScalarList(String.class);


            query = Server.getInstance().getDatabase().createQuery("DROP VIEW test02");
            query.executeUpdate();
            for(String buf1:outList){
                sender.sendMessage(buf1.toString());
            }
            return true;
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
            return false;
        }
    }
}
