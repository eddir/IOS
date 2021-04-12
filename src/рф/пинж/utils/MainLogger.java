package рф.пинж.utils;

public class MainLogger{
    public static MainLogger logger;

    public MainLogger() {
        if (logger != null) {
            throw new RuntimeException("MainLogger уже был создан");
        }
        logger = this;
    }

    public void emergency(String message) {
        this.send(ConsoleColors.YELLOW + "[EMERGENCY] " + message + ConsoleColors.RESET);
    }

    public void alert(String message) {
        this.send(ConsoleColors.RED + "[ALERT] " + message + ConsoleColors.RESET);
    }

    public void critical(String message) {
        this.send(ConsoleColors.RED + "[CRITICAL] " + message + ConsoleColors.RESET);
    }

    public void error(String message) {
        this.send(ConsoleColors.RED + "[ERROR] " + message + ConsoleColors.RESET);
    }

    public void warning(String message) {
        this.send(ConsoleColors.YELLOW + "[WARNING] " + message + ConsoleColors.RESET);
    }

    public void notice(String message) {
        this.send(ConsoleColors.BLUE_BRIGHT + "[NOTICE] " + message + ConsoleColors.RESET);
    }

    public void info(String message) {
        this.send(ConsoleColors.BLACK + "[INFO] " + message + ConsoleColors.RESET);
    }

    public void debug(String message) {
        this.send(ConsoleColors.BLACK + "[DEBUG] " + message + ConsoleColors.RESET);
    }

    public void send(String message) {
        System.out.println(message);
    }

}
