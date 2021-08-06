package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationPropertiesFromFile {

    private static Properties properties = null;
    private static String CONFIG_FILE_PATH = "src/main/resources/configuration.properties";
    private static final Logger LOG = Logger.getLogger(ConfigurationPropertiesFromFile.class);

    static {
        File file = new File(CONFIG_FILE_PATH);
        properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOG.warn("File configuration.properties not found. " + e.getMessage());
        } catch (IOException e) {
            LOG.trace(e.getMessage());
        }
    }

    public static String getProperty(String propKey) {
        String propValue = null;
        if (propKey != null && !propKey.trim().isEmpty()) {
            propValue = properties.getProperty(propKey);
        } else {
            LOG.warn("Property key " + propKey + "does not exist in configuration.properties or is empty.");
        }
        return propValue;
    }
}
