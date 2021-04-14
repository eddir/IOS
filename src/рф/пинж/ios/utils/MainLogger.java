package рф.пинж.ios.utils;

public class MainLogger{
    public static MainLogger logger;

    public MainLogger() {
        if (logger != null) {
            throw new RuntimeException("MainLogger уже был создан");
        }
        logger = this;
    }

    public static MainLogger getLogger() {
        return logger;
    }

    public void logException(Exception exception) {
        // Внимание! Не использовать этот метод пока не будет разработана документация к нему.
        // Используйте `throw new Exception()` взамен.
        this.error("Произошла неизвестная ошибка.");
        exception.printStackTrace();
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
        this.send(ConsoleColors.BLACK_BRIGHT + "[INFO] " + message + ConsoleColors.RESET);
    }

    public void debug(String message) {
        this.send(ConsoleColors.BLACK_BRIGHT + "[DEBUG] " + message + ConsoleColors.RESET);
    }

    public void send(String message) {
        System.out.println(message);
    }

}
