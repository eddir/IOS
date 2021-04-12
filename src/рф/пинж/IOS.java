package рф.пинж;

import рф.пинж.utils.MainLogger;

public class IOS {

    public static String version = "1.0";

    public static void main(String[] args) {
        try {
            MainLogger logger = new MainLogger();
            new Server(logger, "127.0.0.1", 77);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
