package рф.пинж.ios.controller.defaults.users;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.InputAction;
import рф.пинж.ios.controller.action.InputableAction;
import рф.пинж.ios.model.prototype.users.User;
import рф.пинж.ios.repository.users.UserRepository;
import рф.пинж.ios.view.element.Input;
import рф.пинж.ios.utils.Utils;

public class RestorePasswordController extends Controller {
    public RestorePasswordController() {
        super(null, null);
    }

    @URL("help")
    public void signIn(CommandSender sender, String request) {
        sender.sendMessage("Для входа в ИОС введите команду /auth arg1 arg2, где");
        sender.sendMessage("arg1 - ваш логин для входа в систему, arg2 - пароль.");
        sender.sendMessage("Введите команду /restore-pwd, чтобы восстановить пароль от вашей учётной записи");
    }

    @URL("restore_pwd")
    public void restorePwd(CommandSender sender, String request) {
        new InputAction(new Input(new EmailOrLoginInputAction(), "Введите ваш логин или e-mail для восстановления доступа к системе: ")).execute(sender);
    }

    static class EmailOrLoginInputAction implements InputableAction {
        @Override
        public void execute(CommandSender sender, String input) throws Exception {
            User user = null;
            if(Utils.isValidEmail(input)) {
                if (UserRepository.countEmails(input) == 1) {
                    input = UserRepository.getLoginByEmail(input);
                } else {
                    new InputAction(new Input(new EmailOrLoginInputAction(), "Пользователь с данным email не зарегистрирован в системе.\n\rВведите ваш логин или e-mail для восстановления доступа к системе: ")).execute(sender);
                }
            }
            user = UserRepository.getUserByLogin(input);
            if (user == null) {
                new InputAction(new Input(new EmailOrLoginInputAction(), "Пользователь с данным логином не зарегистрирован в системе.\n\rВведите ваш логин или e-mail для восстановления доступа к системе: ")).execute(sender);
                return;
            }
            String confirmCode;
            Server.putCodes(user.getLogin(), confirmCode = Utils.generateRandomCode());
            Utils.sendEmail(user.getEmail(),
                    "Восстановление пароля от системы ИОС", "Здравствуйте, " + user.getFirstName() + " " + user.getMiddleName() + ". " +
                    "Поступил запрос на восстановление пароля вашей учётной записи от системы ИОС.\n\r" +
                    "Для того чтобы восстановить пароль, введите код подтверждения.\n\r" +
                    "Код подтверждения: " + confirmCode + ".\n\r" +
                    "Пожалуйста, проигнорируйте данное письмо, если оно попало к Вам по ошибке.");
            new InputAction(new Input(new EmailConfirmInputAction(user, 3), "На вашу электронную почту было отправленно письмо с кодом подтверждения\n\rВведите код подтверждения: ")).execute(sender);
        }
    }

    static class EmailConfirmInputAction implements InputableAction {
        private User user;
        private int counter;

        public EmailConfirmInputAction(User user, int counter) {
            this.user = user;
            this.counter = counter;
        }

        @Override
        public void execute(CommandSender sender, String input) throws Exception {
            if (Server.isRightCode(user.getLogin(), input, counter == 1))
                new InputAction(new Input(new RestorePasswordController.NewPasswordInputAction(user), "Введите новый пароль: ")).execute(sender);
            else
                if (counter == 1)
                    Server.getInstance().dispatchView(sender, "welcome/index");
                else
                    new InputAction(new Input(new EmailConfirmInputAction(user, --counter), "Неверный код подтверждения. Осталость " + counter + " попыт" + (counter==2?"ки":"ка") + "\n\rВведите код подтверждения: ")).execute(sender);
        }
    }

    static class NewPasswordInputAction implements InputableAction {
        private User user;

        public NewPasswordInputAction(User user) {
            this.user = user;
        }

        @Override
        public void execute(CommandSender sender, String input) {
            if (Utils.isValidPassword(input)) {
                new InputAction(new Input(new RestorePasswordController.PasswordSubmitInputAction(input), "Подтвердите пароль: ")).execute(sender);
                try {
                    UserRepository.changeUserPassword(user.getLogin(), input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                new InputAction(new Input(new RestorePasswordController.NewPasswordInputAction(user), "Пароль должен содержать большие и маленькие заглавные буквы.\n\r" +
                        "Хотя бы 1 цифру или один из спецсимволы '@', '#', '$', '%', '!'.\\\n\r" +
                        "Длина пароля от 8 до 40 символов\n\rВведите новый пароль: ")).execute(sender);
            }
        }
    }

    static class PasswordSubmitInputAction implements InputableAction {
        private String str;

        public PasswordSubmitInputAction(String str) {
            this.str = str;
        }

        @Override
        public void execute(CommandSender sender, String input) {
            if (input == null || !input.equals(str))
                new InputAction(new Input(new RestorePasswordController.PasswordSubmitInputAction(str), "Пароли не совпадают, Подтвердите пароль: ")).execute(sender);
            Server.getInstance().dispatchView(sender, "welcome/index");
        }
    }
}
