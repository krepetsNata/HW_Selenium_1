package driver;

import constants.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverFactory {
    private static WebDriver webDriver;
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);

    public static void initDriver(final String browserName) {

        if (Configuration.getProperty("driver.chrome_name").equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty("driver.chrome_name"), Configuration.getProperty("driver.chrome_driver_location"));
            webDriver = new ChromeDriver();
            LOG.info("Chrome driver was initialized");
        } else if (Configuration.getProperty("driver.firefox_name").equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty("driver.firefox_name"), Configuration.getProperty("driver.firefox_driver_location"));
            webDriver = new FirefoxDriver();
            LOG.info("Firefox driver was initialized");
        } else if (Configuration.getProperty("driver.edge_name").equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty("driver.edge_name"), Configuration.getProperty("driver.edge_driver_location"));
            webDriver = new EdgeDriver();
            LOG.info("Edge driver was initialized");
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(Configuration.getProperty("driver.implicity_wait_value")), TimeUnit.SECONDS);
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
