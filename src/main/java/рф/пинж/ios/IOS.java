package рф.пинж.ios;

import рф.пинж.ios.utils.MainLogger;

import java.io.File;

public class IOS {

    public static String version = "1.1.0";

    // Путь до дирректории сервера
    public final static String PATH = System.getProperty("user.dir") + File.separator;

    public static void main(String[] args) {
        try {
            MainLogger logger = new MainLogger();
            new Server(logger, "127.0.0.1", 77);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
