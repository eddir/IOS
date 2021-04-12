package рф.пинж;

public class IOS {

    public static String version = "1.0";

    public static void main(String[] args) {
        try {
            new Server("127.0.0.1", 77);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
