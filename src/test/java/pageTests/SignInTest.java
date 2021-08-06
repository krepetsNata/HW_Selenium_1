package pageTests;

import constants.Credentials;
import dataProviders.DataProviderEmailData;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SignInPage;

public class SignInTest extends BaseTest {

    @Test(description = "Verify error message appears when user logging in with inappropriate credentials.")
    public void verifyErrorMessageAppearsForIncorrectUser() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.INCORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.INCORRECT_CRED.getPassword())
                .clickSignInButton();
        new SignInPage()
                .verifyFailedLoginErrorMessageDisplayed();
    }

    @Test(description = "Verify user is successfully logged in with appropriate credentials")
    public void verifyUserSuccessfullyLogIn() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .verifyUserIsLoggedIn();
    }

    @Test(description = "Verify that special characters (%+-_) and digits are allowed")
    public void verifySpecCharsInEmail() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("azAZ%+-_12@gmail.com")
                .verifyContinueButtonEnabled();
    }

    @Test(dataProvider = "data_provider_domainname", dataProviderClass = DataProviderEmailData.class,
            description = "Verify count chars in email domain")
    public void verifyDomainInEmail(String email) {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(email)
                .verifyContinueButtonEnabled();
    }

    @Test(dataProvider = "data_provider_inappropriate_email", dataProviderClass = DataProviderEmailData.class,
            description = "Verify count chars in email domain")
    public void verifyInappropriateEmail(String email) {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(email)
                .verifyContinueButtonDisabled();
    }
}
