package рф.пинж.ios.controller.defaults.users;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.Action;
import рф.пинж.ios.model.IModel;
import рф.пинж.ios.view.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SignInController extends Controller {
    public SignInController() {
        super(null, null);
    }

    @URL("help")
    public void signIn(CommandSender sender, String request) {
        sender.sendMessage("Для входа в ИОС введите команду /auth arg1 arg2, где");
        sender.sendMessage("arg1 - ваш логин для входа в систему, arg2 - пароль.");
        sender.sendMessage("Введите команду /restore-pwd arg1 (где arg1 - ваш email), ");
        sender.sendMessage("чтобы восстановить пароль от вашей учётной записи");
    }

    @URL("restore_pwd")
    public void restorePwd(CommandSender sender, String request) {
        sender.sendMessage("Введите ваш логин или e-mail для восстановления доступа к системе: ");
    }

}
