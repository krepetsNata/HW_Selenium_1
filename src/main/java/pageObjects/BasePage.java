package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    private static WebDriverWait wait =  new WebDriverWait(DriverFactory.getDriver(), 10);

    BasePage() {
    }

    void  navigateToURL(final String url) {
        DriverFactory.getDriver().get(url);
    }

}
