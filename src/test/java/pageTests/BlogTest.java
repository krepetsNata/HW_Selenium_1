package pageTests;

import constants.Credentials;
import org.testng.annotations.Test;
import pageObjects.BlogPage;
import pageObjects.HomePage;

public class BlogTest extends BaseTest {

    @Test(description = "Verify text links.")
    public void verifyingLinks() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .clickBlogButton();
        new BlogPage()
                .verifyListContainsText();
    }
}
