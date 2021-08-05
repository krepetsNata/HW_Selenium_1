package pageObjects;

import constants.Configuration;
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

    public String getLoggedInUserName() {
        return topRightCornerUserNameElement.getText();
    }

    public HomePage proceedToHomePage() {
        navigateToURL(Configuration.getProperty("business.home_page_url"));
        LOG.info(String.format("Proceeded to '%s' URL.", Configuration.getProperty("business.home_page_url")));
        return this;
    }

    public SignInPage clickSignInButton() {
        waitForPageLoadComplete(25);
        waitElementToBeClickable(10, signInButton).click();
        LOG.info("'Sign in' button clicked");
        return new SignInPage();
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

    public void verifyUserIsLoggedIn() {
        Assert.assertTrue(isUserNameDisplayed(), "User is not logged in");
    }

}
