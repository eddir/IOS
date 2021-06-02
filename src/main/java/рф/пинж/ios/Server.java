package рф.пинж.ios;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import рф.пинж.ios.command.CommandMap;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.network.Network;
import рф.пинж.ios.permission.Permissible;
import рф.пинж.ios.permission.Permission;
import рф.пинж.ios.utils.Config;
import рф.пинж.ios.utils.MainLogger;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Server {

    // Показывает запущен ли сервер в данный момент
    private boolean isRunning = true;

    private final Network network;

    private final String ip;
    private final int port;

    private final Set<Client> clients;

    private final CommandMap commandMap;

    private final MainLogger logger;

    private static Server instance;

    private final Config config;

    private Connection database;

    public Server(MainLogger logger, String ip, int port) {
        instance = this;
        this.logger = logger;

        this.ip = ip;
        this.port = port;

        this.clients = new HashSet<>();

        this.commandMap = new CommandMap(this);

        this.config = new Config();

        try {
            this.config.load();
        } catch (FileNotFoundException e) {
            this.crash(e.getMessage());
        }

        logger.info("Подключение к бд MySQL...");

        Sql2o sql2o = new Sql2o(
                this.config.getProperty("db-address"),
                this.config.getProperty("db-login"),
                this.config.getProperty("db-password"));

        try {
            this.database = sql2o.open();
        } catch (Sql2oException e) {
            this.crash(e.getMessage());
        }

        logger.info("Подключение к бд готово");

        this.network = new Network(this);
        this.network.run();
    }

    public ArrayList<String> getDefaultPermissions(Permissible user) {
        ArrayList<String> permissions = new ArrayList<>();
        if (user instanceof Client) {
            //todo: ввод из файла permissions.yml
            permissions.add("command.version");
            permissions.add("command.help");
            permissions.add("welcome.index");
        }
//        if (user instanceof Student) {
//            permissions.add("forum.view");
//        }
        return permissions;
    }

    public boolean testPermission(Permissible target, Method method) {
        if (method.isAnnotationPresent(Permission.class)) {
            return this.testPermission(target, method.getAnnotation(Permission.class).value());
        }
        return true;
    }

    /**
     * Проверяет владеет ли пользователь заданным правом
     *
     * @param target     пользователь
     * @param permission права в виде строки, разделённые знаком ;
     * @return true, если да, или false, если нет прав
     */
    public boolean testPermission(Permissible target, String permission) {
        if (permission == null || permission.equals("")) {
            return true;
        }

        String[] permissions = permission.split(";");
        for (String perm : permissions) {
            if (target.hasPermission(perm)) {
                return true;
            }
        }

        return false;
    }

    public boolean dispatchCommand(CommandSender sender, String command) {
        if (this.commandMap.dispatch(sender, command)) {
            return true;
        }

        sender.sendMessage("Неизвестная команда. Вы ввели " + command);
        return false;
    }

    public boolean dispatchView(CommandSender sender, String url) {
        try {
            // Парсинг url
            // Пример: forum/topic/first

            // Две части адреса - путь и параметры
            String[] parts;
            // Путь до метода у контроллера
            List<String> path;
            // Путь до класса контроллера
            String controllerPath;
            // Метод у контроллера
            String action;
            // Аргументы передаются методу контроллера
            Map<String, String> args = new HashMap<>();

            parts = url.split("\\?");
            path = Arrays.asList(parts[0].split("/"));

            List<String> controllerNamespace = path.subList(0, path.size() - 1);
            String cc = controllerNamespace.get(controllerNamespace.size() - 1);
            cc = cc.substring(0, 1).toUpperCase() + cc.substring(1) + "Controller";

            controllerPath = String.join(".", controllerNamespace.subList(0, controllerNamespace.size() - 1));
            if (controllerPath.length() > 0) {
                controllerPath += '.';
            }
            controllerPath += cc;

            action = path.get(path.size() - 1);

            if (parts.length == 2) {
                for (String arg : parts[1].split("&")) {
                    String[] combination = arg.split("=");
                    args.put(combination[0], combination[1]);
                }
            }

            Class<?> c = Class.forName("рф.пинж.ios.controller.defaults." + controllerPath);
            Controller controller = (Controller) c.getDeclaredConstructor().newInstance();

            for (Method method : controller.getClass().getMethods()) {
                if (method.isAnnotationPresent(URL.class) && method.getAnnotation(URL.class).value().equals(action)) {
                    if (this.testPermission(sender, method)) {
                        sender.setAction(url);
                        method.invoke(controller, sender, args.get("do"));
                        return true;
                    } else {
                        sender.sendMessage("У Вас недостаточно прав для совершения этого действия.");
                        return false;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IndexOutOfBoundsException e) {
            MainLogger.getLogger().error("Не удалось определить ссылку " + url);
            e.printStackTrace();
        }
        return false;
    }

    public void addClient(Client client) {
        this.clients.add(client);
        this.dispatchView(client, "welcome/index");
    }

    public void crash(String message) {
        logger.alert(message);
        this.stop();
    }

    public void stop() {
        this.isRunning = false;
        logger.alert("Завершение.");
        System.exit(0);
    }

    public Connection getDatabase() {
        return database;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public static MainLogger getLogger() {
        return instance.logger;
    }

    public static Server getInstance() {
        return instance;
    }
}
