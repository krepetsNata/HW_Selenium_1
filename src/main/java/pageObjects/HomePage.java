package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import utils.ConfigurationPropertiesFromFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(className = "header-auth__signin")
    private WebElement signInButton;

    @FindBy(className = "user-info__name")
    private WebElement topRightCornerUserNameElement;

    @FindBy(xpath = "//ul[@class='main-nav__list']//a[contains(@class,'training')]")
    private WebElement trainingListPageButton;

    @FindBy(xpath = "//ul[@class='main-nav__list']//a[contains(@class,'news')]")
    private WebElement blogButton;

    @FindBy(xpath = "//div[@class='dropdown user-info']//a[@class='user-info dropdown-toggle']")
    private WebElement profileArrow;

    @FindBy(xpath = "//a[@href='/Auth/LogoutExt']")
    private WebElement logoutLink;

    public HomePage proceedToHomePage() {
        navigateToURL(ConfigurationPropertiesFromFile.getProperty("business.home_page_url"));
        LOG.info(String.format("Proceeded to '%s' URL.", ConfigurationPropertiesFromFile.getProperty("business.home_page_url")));
        return this;
    }

    public SignInPage clickSignInButton() {
        waitForPageLoadComplete(30);
        waitElementToBeClickable(10, signInButton).click();
        LOG.info("'Sign in' button was clicked");
        return new SignInPage();
    }

    public HomePage clickProfileArrowButton() {
        //profileArrow.click(); //ElementNotInteractableException: element not interactable
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", profileArrow);
        LOG.info("'Profile arrow' button was clicked");
        return new HomePage();
    }

    public HomePage clickLogOutButton() {
        //logoutLink.click();//ElementNotInteractableException: element not interactable
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", logoutLink);
        LOG.info("'Log Out' button was clicked");
        return new HomePage();
    }

    public TrainingListPage clickTrainingListButton() {
        trainingListPageButton.click();
        LOG.info("'Training List' button clicked");
        return new TrainingListPage();
    }

    public BlogPage clickBlogButton() {
        blogButton.click();
        LOG.info("'Blog' button clicked");
        return new BlogPage();
    }

    public boolean isUserNameDisplayed() {
        boolean isDisplayed = topRightCornerUserNameElement.isDisplayed();
        LOG.info(String.format("User is logged in: '%s'", isDisplayed));
        return isDisplayed;
    }

    public HomePage verifyUserIsLoggedIn() {
        Assert.assertTrue(isUserNameDisplayed(), "User is not logged in");
        return this;
    }

}
