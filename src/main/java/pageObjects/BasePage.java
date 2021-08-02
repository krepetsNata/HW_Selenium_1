package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    //private static WebDriverWait waiter =  new WebDriverWait(DriverFactory.getDriver(), 10);

        WebDriver driver;

    public BasePage() {
            driver = DriverFactory.getDriver();
            PageFactory.initElements(driver, this);
    }

    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(DriverFactory.getDriver(), timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
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

    void  navigateToURL(final String url) {
        DriverFactory.getDriver().get(url);
    }

}
