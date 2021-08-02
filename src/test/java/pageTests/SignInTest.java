package pageTests;

import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SignInPage;

public class SignInTest extends BaseTest {

    @Test(description = "Verify error message appears when user logging in with inappropriate credentials.")
    public void verifyErrorMessageAppearsForIncorrectUser() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("incorrectmail@gmail.com")
                .clickContinueButton()
                .enterPassword("incorrectPassword")
                .clickSignInButton();
        new SignInPage()
                .verifyFailedLoginErrorMessageDisplayed();

    }

    @Test(description = "Verify that special characters (%+-_) and digits are allowed")
    public void verifySpecCharsInEmail(){
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("azAZ%+-_12@gmail.com")
                .verifyContinueButtonEnabled();
    }
}
