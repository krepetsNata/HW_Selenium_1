package pageTests;

import constants.Configuration;
import constants.Constants;
import driver.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest extends DriverFactory {
    private Logger LOG = Logger.getLogger(BaseTest.class);
    private Test test;

    //public static final String CHROME = Configuration.getProperty(Constants.CHROME_NAME.getNameConstInFile());

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    //public void beforeMethod(@Optional(value = Configuration.getProperty(Constants.CHROME_NAME.getNameConstInFile())) final String browserName, Method method) {
    public void beforeMethod(@Optional(value = "webdriver.chrome.driver") final String browserName, Method method) {
        initDriver(browserName);
        test = method.getAnnotation(Test.class);
        LOG.info(String.format("Test '%s' started.",method.getName()));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final Method method){
        LOG.info(String.format("Test '%s' completed.",method.getName()));
        quitDriver();
    }
}
