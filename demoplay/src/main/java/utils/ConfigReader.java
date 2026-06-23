package utils;

import java.util.Properties;
import java.io.InputStream;


public class ConfigReader {

    public static Properties prop;

    public ConfigReader() {
        try {
            prop = new Properties();

            InputStream fis = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (fis == null) {
                throw new RuntimeException("config.properties NOT found in src/test/resources");
            }

            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}