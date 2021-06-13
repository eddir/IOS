package рф.пинж.ios.controller.defaults;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.*;
import рф.пинж.ios.model.prototype.forum.Topic;
import рф.пинж.ios.permission.Permission;
import рф.пинж.ios.repository.forum.TopicRepository;
import рф.пинж.ios.view.defaults.forum.WelcomeView;
import рф.пинж.ios.view.element.Input;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;

public class WelcomeController extends Controller {
    public WelcomeController() {
        super(null, new WelcomeView());
    }

    @URL("index")
    @Permission("welcome.index")
    public void index(CommandSender sender, String request) {
        sender.sendView(view);
//        sender.sendView(new Menu(new LinkedHashMap<>() {
//            {
//                put("Авторизация", new WelcomeAction("авторизация"));
//                put("Помощь", new CommandAction("help"));
//                put("Топик", new ViewAction("forum/topic/first"));
//                put("Ввести данные", new InputAction(new Input(new WelcomeInputAction(), "Введите имя:")));
//                put("Выход", new WelcomeAction("выход"));
//            }
//        }));
    }

    static class WelcomeInputAction implements InputableAction {

        @Override
        public void execute(CommandSender sender, String input) {
            sender.sendMessage("Вы написали " + input);
        }
    }

    record WelcomeAction(String message) implements Action {
        @Override
        public void execute(CommandSender sender) {
            sender.sendMessage("Вы выбрали " + message);
            sender.sendMessage("К сожалению, это действие временно недоступно. Вы будете возвращены в меню.");
            try {
                Thread.sleep(2000);
                Server.getInstance().dispatchView(sender, "welcome/index");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @URL("about")
    public void about(CommandSender sender, String request) {
        Topic topic = TopicRepository.getFirst();
        this.view.setModel(topic);
        sender.sendView(view);
    }

}
