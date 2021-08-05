package pageTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

public class HardCodedTest {

    @Test(description = "Verify user is successfully logged in with appropriate credentials")
    public void verifyUserIsSuccessfullyLoggedIn() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#!/Home?lang=en");

        WebElement signInButton = driver.findElement(By.className("header-auth__signin"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("username"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement continueButton = driver.findElement(By.id("kc-login-next"));
        continueButton.click();

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("ivanhorintestPassword");

        WebElement signInButtonOnPasswordWindow = driver.findElement(By.id("kc-login"));
        signInButtonOnPasswordWindow.click();

        WebElement usernameLabel = driver.findElement(By.className("user-info__name"));
        Assert.assertTrue(usernameLabel.isDisplayed(), "Username is NOT displayed!");

        driver.quit();
    }


    @Test(description = "Verify user is got error after trying log in with invalid credentials(pasword)")
    public void verifyUserIsLoggedInIncorrectPass() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#!/Home?lang=en");

        WebElement signInButton = driver.findElement(By.className("header-auth__signin"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("username"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement continueButton = driver.findElement(By.id("kc-login-next"));
        continueButton.click();

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("invalidPassword");

        WebElement signInButtonOnPasswordWindow = driver.findElement(By.id("kc-login"));
        signInButtonOnPasswordWindow.click();

        WebDriverWait wait = new WebDriverWait(driver, 25);
        mailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mailInput.isDisplayed(), "User mail field is NOT displayed!");
        softAssert.assertEquals(mailInput.getText(), "ivanhorintest@gmail.com");

        WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='temp']//span[@class='error-text']")));//className("error-text"));
        Assert.assertTrue(errorText.isDisplayed(), "We can't find user with such credentials.");

        driver.quit();
    }
}
