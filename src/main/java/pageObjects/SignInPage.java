package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SignInPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(SignInPage.class);
    private SoftAssert softAssert = new SoftAssert();

    @FindBy(id = "username")
    private WebElement mailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "kc-login")
    private WebElement signInButton;

    @FindBy(id = "kc-login-next")
    private WebElement continueButton;

    @FindBy(xpath = "//span[text()=\"We can't find user with such credentials.\"]")
    private WebElement loginFailedErrorMessage;

    public SignInPage enterEmail(String email) {
        mailInput.sendKeys(email);
        LOG.info("Mail was entered.");
        return this;
    }

    public SignInPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        LOG.info("Password was entered.");
        return this;
    }

    public SignInPage clickContinueButton() {
        continueButton.click();
        LOG.info("Continue button clicked.");
        return this;
    }

    public HomePage clickSignInButton() {
        waitElementToBeClickable(10, signInButton).click();
        LOG.info("Sign in button clicked.");
        return new HomePage();
    }

    public boolean isContinueButtonEnabled() {
        waitVisibilityOfElement(10, continueButton);
        boolean isEnabled = continueButton.isEnabled();
        LOG.info(String.format("Is 'Continue' button enabled': '%s'", isEnabled));
        return isEnabled;
    }

    public boolean isLoginFailedErrorMessageDisplayed() {
        waitVisibilityOfElement(10,loginFailedErrorMessage);
        boolean isDisplayed = loginFailedErrorMessage.isDisplayed();
        LOG.info(String.format("Is 'Login Failed' error message displayed': '%s'", isDisplayed));
        return isDisplayed;
    }

    public void verifyFailedLoginErrorMessageDisplayed() {
        Assert.assertTrue(isLoginFailedErrorMessageDisplayed(),"'Login failed' error message is not displayed");
    }

    public void verifyContinueButtonEnabled() {
        softAssert.assertTrue(isContinueButtonEnabled(), "Spec Chars is allowed");
        softAssert.assertAll();
    }

    public void verifyContinueButtonDisabled() {
        softAssert.assertFalse(isContinueButtonEnabled(), "Email is inappropriate");
        softAssert.assertAll();
    }
}
