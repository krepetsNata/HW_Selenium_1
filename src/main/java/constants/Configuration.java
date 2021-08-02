package constants;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Configuration {

    private static Properties properties = null;
    private static String CONFIG_FILE_PATH = "configuration.properties";
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

//    /** Private constructor */
//    private Configuration (){
//        this.properties = new Properties();
//        try{
//            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.PATH_CONFFILE));
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    /** Creates the instance is synchronized to avoid multithreads problems */
//    private synchronized static void createInstance () {
//        if (instance == null) {
//            instance = new Configurations ();
//        }
//    }
//
//    /** Get the properties instance. Uses singleton pattern */
//    public static Configurations getInstance(){
//        // Uses singleton pattern to guarantee the creation of only one instance
//        if(instance == null) {
//            createInstance();
//        }
//        return instance;
//    }

    /** Get a property of the property file */
    public static String getProperty(String key){
        String propValue = null;
        if(key !=null && !key.trim().isEmpty()){
            propValue = properties.getProperty(key);
        } else{
            LOG.warn("Property key " + key + "does not exist in configuration.properties or is empty.");
        }
        return propValue;
    }

//    /** Override the clone method to ensure the "unique instance" requeriment of this class */
//    public Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
}
