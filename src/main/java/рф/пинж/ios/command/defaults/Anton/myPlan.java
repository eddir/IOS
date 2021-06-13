package рф.пинж.ios.command.defaults.Anton;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.command.defaults.ShowAllSubInEduPlanCommand;

public class myPlan extends Command
{
    public myPlan(){
        super("getMySub");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            //Server.getInstance().dispatchView(sender,"Anton/EduPlan/allSub");
            //if(sender instanceof Student){

            // }
            String[] args2 = {"10"};
            new ShowAllSubInEduPlanCommand().execute(sender,commandLabel,args2); //
            return true;
        }
        catch(Exception err){
            sender.sendMessage(err.getMessage());
        }
        return false;
    }
}
