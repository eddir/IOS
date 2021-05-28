package рф.пинж.ios.command;

import рф.пинж.ios.repository.Anton.EduPlanRepository;

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
            case "deleteById":
                EduPlanRepository.delPlan(Integer.parseInt(args[1]));
                break;

        }
        return true;
    }
}
