package рф.пинж.ios.controller.defaults.ilya;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.ViewAction;
import рф.пинж.ios.repository.ilya.CathedraRepository;
import рф.пинж.ios.repository.ilya.InstituteRepository;
import рф.пинж.ios.view.defaults.ilya.InstituteView;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;

public class InstituteController extends Controller {

    public InstituteController() {
        super(null, new InstituteView());
    }
    public static int VAL = 1;

    @URL("institutes")
    public void institutes(CommandSender sender, String request) {
        sender.sendView(view);
        sender.sendView(new Menu(new LinkedHashMap<>() {
            {
                for (String str : InstituteRepository.getAllTitles()) {
                    put(str, new CommandAction("cathedras " + (VAL++)));
                }
                put("Выход", new ViewAction("welcome/index"));
            }
        }));
    }
}
