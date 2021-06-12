package рф.пинж.ios.command;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.defaults.HelpCommand;
import рф.пинж.ios.command.defaults.VersionCommand;
import рф.пинж.ios.command.defaults.forum.TopicCommand;
import рф.пинж.ios.command.defaults.ilya.CathedrasCommand;
import рф.пинж.ios.command.defaults.ilya.InstituteCommand;
import рф.пинж.ios.command.defaults.ilya.InstitutesCommand;

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
        this.register(new InstitutesCommand());
        this.register(new InstituteCommand());
        this.register(new CathedrasCommand());
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
