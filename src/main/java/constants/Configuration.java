package constants;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties properties = null;
    private static String CONFIG_FILE_PATH = "src/main/resources/configuration.properties";
    private static final Logger LOG = Logger.getLogger(Configuration.class);

    static {
        File file = new File(CONFIG_FILE_PATH);
        properties = new Properties();

        try (FileInputStream fileInput = new FileInputStream(file)) {
            properties.load(fileInput);
        } catch (FileNotFoundException e) {
            LOG.warn("File configuration.properties not found. " + e.getMessage());
        } catch (IOException e) {
            LOG.trace(e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String propValue = null;
        if (key != null && !key.trim().isEmpty()) {
            propValue = properties.getProperty(key);
        } else {
            LOG.warn("Property key " + key + "does not exist in configuration.properties or is empty.");
        }
        return propValue;
    }
}
