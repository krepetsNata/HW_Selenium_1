package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
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

    void navigateToURL(final String url) {
        DriverFactory.getDriver().get(url);
    }


//    private boolean existsElement(WebElement el, String xpath) {
//        try {
//            el.findElement(By.xpath(xpath));
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//        return true;
//    }
}
