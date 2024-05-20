package util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author jussaragranja
 * Access to config.properties
 */

public class Property {

    private static final String PROP_FILE_CONFIG = "config.properties";
    public static String API_URL;

    public static Properties getConfig(){
        Properties properties = new Properties();
        InputStream fileIn = ClassLoader.getSystemResourceAsStream(PROP_FILE_CONFIG);
        try {
            properties.load(fileIn);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    static{
        Properties properties   	= getConfig();
        API_URL					    = properties.getProperty("api-url");
    }

}
