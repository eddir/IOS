package рф.пинж.ios.command;

import org.sql2o.Sql2oException;
import рф.пинж.ios.repository.Anton.SubjectRepository;
import рф.пинж.ios.utils.MainLogger;

public class SubjectCommand extends Command{
    public SubjectCommand(){
        super("subject");
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            switch (args[0]){
                case "create":
                    if(args.length == 3)
                        SubjectRepository.createAndAddToEduPlan(args[1],Integer.parseInt(args[2]));
                    else if (args.length == 2) {
                        SubjectRepository.create(args[1]);
                    }
                    break;
                case "delete":
                    SubjectRepository.deleteById(Integer.parseInt(args[1]));
                    break;
                case "setTeacher":
                    SubjectRepository.setTeacher(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            }
        }
        catch(Sql2oException exception){
            MainLogger.getLogger().error(exception.getMessage());
        }
        return true;
    }
}
