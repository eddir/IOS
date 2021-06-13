package рф.пинж.ios.command;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.defaults.*;
import рф.пинж.ios.command.defaults.Anton.myPlan;
import рф.пинж.ios.command.defaults.forum.TopicCommand;
import рф.пинж.ios.command.defaults.users.*;
import рф.пинж.ios.command.defaults.users.AuthComand;
import рф.пинж.ios.command.defaults.users.ChangePasswordCommand;
import рф.пинж.ios.command.defaults.users.QuitCommand;
import рф.пинж.ios.command.defaults.users.RestorePasswordCommand;
import рф.пинж.ios.command.defaults.ilya.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {

    protected final Map<String, Command> commands = new HashMap<>();

    final Server server;

    public CommandMap(Server server) {
        this.server = server;
        this.setDefaultCommands();
    }

    public void setDefaultCommands() {
        this.register(new VersionCommand());
        this.register(new TopicCommand());
        this.register(new HelpCommand());

        this.register(new AuthComand());
        this.register(new QuitCommand());
        this.register(new RestorePasswordCommand());
        this.register(new ChangePasswordCommand());
        this.register(new DeleteUserCommand());
        this.register(new ChangeUserDataCommand());
        this.register(new RegisterCommand());

        this.register(new EduPlanCommand());
        this.register(new SubjectCommand());
        this.register(new AddSubjectInEduPlanCommand());
        this.register(new ShowAllSubInEduPlanCommand());
        this.register(new ProfilCommand());
        this.register(new myPlan());
        this.register(new InstitutesCommand());
        this.register(new InstituteCommand());
        this.register(new CathedrasCommand());
        this.register(new CathedraCommand());
        this.register(new DirectionsCommand());

        this.register(new ActiveMVC());
    }

    public void register(Command command) {
        this.register(command, null);
    }

    public void register(Command command, String label) {
        if (label == null) {
            label = command.getName();
        }
        label = label.trim().toLowerCase();

        this.commands.put(label, command);

    }

    public boolean dispatch(CommandSender sender, String commandLine) {
        ArrayList<String> parsed = parseArguments(commandLine);

        if (parsed.size() == 0) {
            return false;
        }

        String sentCommandLabel = parsed.remove(0).toLowerCase();
        String[] args = parsed.toArray(new String[0]);
        Command target = this.getCommand(sentCommandLabel);

        if (target == null) {
            return false;
        }

        // Проверка прав
        for (Method method : target.getClass().getMethods()) {
            if ("execute".equals(method.getName())) {
                if (!sender.getServer().testPermission(sender, method)) {
                    sender.sendMessage("Недостаточно прав для выполнения этой команды.");
                    return true;
                }
                break;
            }
        }


        try {
            target.execute(sender, sentCommandLabel, args);
        } catch (Exception e) {
            sender.sendMessage("Произошла непредвиденная ошибка при выполнении команды.");
            Server.getLogger().error("Произошла непредвиденная ошибка при выполнении команды.");
        }

        return true;
    }

    private ArrayList<String> parseArguments(String cmdLine) {
        StringBuilder sb = new StringBuilder(cmdLine);
        ArrayList<String> args = new ArrayList<>();
        boolean notQuoted = true;
        int start = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '\\') {
                sb.deleteCharAt(i);
                continue;
            }

            if (sb.charAt(i) == ' ' && notQuoted) {
                String arg = sb.substring(start, i);
                if (!arg.isEmpty()) {
                    args.add(arg);
                }
                start = i + 1;
            } else if (sb.charAt(i) == '"') {
                sb.deleteCharAt(i);
                --i;
                notQuoted = !notQuoted;
            }
        }

        String arg = sb.substring(start);
        if (!arg.isEmpty()) {
            args.add(arg);
        }
        return args;
    }

    public Command getCommand(String name) {
        if (this.commands.containsKey(name)) {
            return this.commands.get(name);
        }
        return null;
    }
}
