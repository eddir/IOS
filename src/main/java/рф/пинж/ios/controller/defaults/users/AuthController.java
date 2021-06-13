package рф.пинж.ios.controller.defaults.users;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.model.IModel;
import рф.пинж.ios.view.View;

public class AuthController extends Controller {

    public AuthController() {
        super(null, null);
    }

    @URL("signIn")
    public void signIn(CommandSender sender, String request) {
        sender.sendMessage("Для входа в ИОС введите команду /auth arg1 arg2, где");
        sender.sendMessage("arg1 - ваш логин для входа в систему, arg2 - пароль.");
        sender.sendMessage("");
        sender.sendMessage("Введите команду /restore-pwd arg1 (где arg1 - ваш email), ");
        sender.sendMessage("чтобы восстановить пароль от вашей учётной записи");
    }
}
