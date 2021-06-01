package рф.пинж.ios.utils;

import рф.пинж.ios.IOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {

    private final HashMap<String, String> properties = new HashMap<>();

    private static String CONFIG_PATH;

    public void load() throws FileNotFoundException {
        CONFIG_PATH = IOS.PATH + "config.properties";
        File file = new File(CONFIG_PATH);

        if (file.exists()) {
            String content = "";
            try {
                content = Utils.readFile(file);
            } catch (IOException e) {
                MainLogger.getLogger().logException(e);
            }
            parseProperties(content);
        } else {
            throw new FileNotFoundException("Не найден файл " + CONFIG_PATH);
        }
    }

    public void parseProperties(String content) {
        for (String line : content.split("\n")) {
            if (Pattern.compile("[a-zA-Z0-9\\-_.]*+=+[^\\r\\n]*").matcher(line).matches()) {
                String[] b = line.split("=", -1);
                this.properties.put(b[0], Arrays.stream(b).skip(1).collect(Collectors.joining("=")).trim());
            }
        }
    }

    public String getProperty(String key) {
        return this.properties.get(key);
    }
}
