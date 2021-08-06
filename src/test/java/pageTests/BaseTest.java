package pageTests;

import driver.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
    private Logger LOG = Logger.getLogger(BaseTest.class);
    private Test test;

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(@Optional(value = "webdriver.chrome.driver") final String browserName, Method method) {
        DriverFactory.getInstanceOfDriverFactory(browserName);
        test = method.getAnnotation(Test.class);
        LOG.info(String.format("Test '%s' started.", method.getName()));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final Method method) {
        LOG.info(String.format("Test '%s' completed.", method.getName()));
        DriverFactory.quitDriver();
    }
}
