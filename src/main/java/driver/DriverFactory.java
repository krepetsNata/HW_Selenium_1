package driver;

import utils.ConfigurationPropertiesFromFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    // try to implement a singleton pattern for the driver (3 steps):
    // 1) declaring instanceof this class
    private static DriverFactory instanceOfDriverFactory = null;

    private static WebDriver webDriver;
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);

    //2) private constructor to restrict creating obj outside of class
    private DriverFactory(final String browserName) {
        if (ConfigurationPropertiesFromFile.getProperty("driver.chrome_name").equalsIgnoreCase(browserName)) {
            System.setProperty(ConfigurationPropertiesFromFile.getProperty("driver.chrome_name"), ConfigurationPropertiesFromFile.getProperty("driver.chrome_driver_location"));
            webDriver = new ChromeDriver();
            LOG.info("Chrome driver was initialized");
        } else if (ConfigurationPropertiesFromFile.getProperty("driver.firefox_name").equalsIgnoreCase(browserName)) {
            System.setProperty(ConfigurationPropertiesFromFile.getProperty("driver.firefox_name"), ConfigurationPropertiesFromFile.getProperty("driver.firefox_driver_location"));
            webDriver = new FirefoxDriver();
            LOG.info("Firefox driver was initialized");
        } else if (ConfigurationPropertiesFromFile.getProperty("driver.edge_name").equalsIgnoreCase(browserName)) {
            System.setProperty(ConfigurationPropertiesFromFile.getProperty("driver.edge_name"), ConfigurationPropertiesFromFile.getProperty("driver.edge_driver_location"));
            webDriver = new EdgeDriver();
            LOG.info("Edge driver was initialized");
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigurationPropertiesFromFile.getProperty("driver.implicity_wait_value")), TimeUnit.SECONDS);

        LOG.info("Object created");
    }

    //3) method which create instance of this class
    public static DriverFactory getInstanceOfDriverFactory(final String browserName) {
        if (instanceOfDriverFactory == null)
            instanceOfDriverFactory = new DriverFactory(browserName);
        return instanceOfDriverFactory;
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
            LOG.info("Driver was closed");
        }
    }
}
