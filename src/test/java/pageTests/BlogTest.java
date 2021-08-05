package pageTests;

import org.testng.annotations.Test;
import pageObjects.BlogPage;
import pageObjects.HomePage;

public class BlogTest extends BaseTest {

    @Test
    public void verifyigLinks() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("ivanhorintest@gmail.com")
                .clickContinueButton()
                .enterPassword("ivanhorintestPassword")
                .clickSignInButton()
                .clickBlogButton();
        new BlogPage()
                .verifyListContainsText();
    }
}
