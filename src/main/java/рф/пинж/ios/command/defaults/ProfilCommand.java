package рф.пинж.ios.command.defaults;
import рф.пинж.ios.command.Command;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.repository.Anton.ProfilsRepository;

public class ProfilCommand extends Command{
    public ProfilCommand(){
        super("profil");
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            switch (args[0]){
                case "create":
                    ProfilsRepository.addNewProfil(args[1]);
                    break;
                case "del":
                    ProfilsRepository.delProfil(args[1]);
                    break;
            }
            return true;
        }
        catch(Exception err){
            sender.sendMessage(err.getMessage());//Нужно изменить
            return false;
        }

    }
}
