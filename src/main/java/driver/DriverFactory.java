package driver;

import constants.Configuration;
import constants.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public abstract class DriverFactory {
    private static WebDriver webDriver;
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);

    protected void initDriver(final String browserName) {

        if (Configuration.getProperty(Constants.CHROME_NAME.getNameConstInFile()).equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty(Constants.CHROME_NAME.getNameConstInFile()), Configuration.getProperty(Constants.CHROME_DRIVER_LOCATION.getNameConstInFile()));
            webDriver=new ChromeDriver();
            LOG.info("Chrome driver was initialized");
        } else if (Configuration.getProperty(Constants.FIREFOX_NAME.getNameConstInFile()).equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty(Constants.FIREFOX_NAME.getNameConstInFile()), Configuration.getProperty(Constants.FIREFOX_DRIVER_LOCATION.getNameConstInFile()));
            webDriver = new FirefoxDriver();
            LOG.info("Firefox driver was initialized");
        } else if (Configuration.getProperty(Constants.EDGE_NAME.getNameConstInFile()).equalsIgnoreCase(browserName)) {
            System.setProperty(Configuration.getProperty(Constants.EDGE_NAME.getNameConstInFile()), Configuration.getProperty(Constants.EDGE_DRIVER_LOCATION.getNameConstInFile()));
            webDriver = new EdgeDriver();
            LOG.info("Edge driver was initialized");
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(Configuration.getProperty(Constants.IMPLICITLY_WAIT_VALUE.getNameConstInFile())), TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    protected void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver=null;
            LOG.info("Driver was closed");
        }
    }
}
