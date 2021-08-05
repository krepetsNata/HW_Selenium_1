package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    public BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoadComplete(long timeToWait) {
        DriverFactory.getDriver().manage().timeouts().pageLoadTimeout(timeToWait, TimeUnit.SECONDS);
    }

    public void waitVisibilityOfElement(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeToWait);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementToBeClickable(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeToWait);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    void navigateToURL(final String url) {
        DriverFactory.getDriver().get(url);
    }
}
