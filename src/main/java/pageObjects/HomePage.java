package pageObjects;


import constants.Configuration;
import constants.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
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

    @FindBy(className = "//ul[@class='main-nav__list']//a[contains(@class,'news')]")
    private WebElement newsPageButton;

    @FindBy(className = "//ul[@class='main-nav__list']//a[contains(@class,'about')]")
    private WebElement aboutPageButton;

    @FindBy(xpath = "//ul[@class='main-nav__list']//a[contains(@class,'faq')]")
    private WebElement FAQPageButton;

    public SignInPage clickSignInButton() {
        waitForPageLoadComplete(25);
        waitElementToBeClickable(10,signInButton).click();
        LOG.info("'Sign in' button clicked");
        return new SignInPage();
    }

    public HomePage proceedToHomePage() {
        navigateToURL(Configuration.getProperty(Constants.HOME_PAGE_URL.getNameConstInFile()));
        LOG.info(String.format("Proceeded to '%s' URL.",Configuration.getProperty(Constants.HOME_PAGE_URL.getNameConstInFile())));
        return this;
    }

    public boolean isUserNameDisplayed(){
        boolean isDisplayed = topRightCornerUserNameElement.isDisplayed();
        LOG.info(String.format("User is logged in: '%s'",isDisplayed));
        return isDisplayed;
    }
    public String getLoggedInUserName(){
        return topRightCornerUserNameElement.getText();
    }

//    public AboutPage openAboutPage() {
//        getElement(aboutPageButton).click();
//        LOG.info("Open 'About' page.");
//        return new AboutPage();
//    }

//    public FAQPage openFAQPage() {
//        getElement(FAQPageButton).click();
//        LOG.info("Open 'FAQ' page.");
//        return new FAQPage();
//    }

    public void verifyUserIsLoggedIn(){
        Assert.assertTrue(isUserNameDisplayed(),"User is not logged in");
    }

}
