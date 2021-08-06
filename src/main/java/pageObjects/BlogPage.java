package pageObjects;

import constants.NavigationLinks;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BlogPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BlogPage.class);
    private SoftAssert softAssert = new SoftAssert();

    @FindBy(xpath = "//div[contains(@class,'tab-nav__list')]//a")
    private List<WebElement> listNavigation;

    public List<WebElement> getListNavigation() {
        if (listNavigation.isEmpty()) {
            LOG.warn("List is empty");
            //return null;
        }
        return listNavigation;
    }

    public BlogPage verifyListContainsText() {
        NavigationLinks[] navigationLinksValues = NavigationLinks.values();

        for (int i = 0; i < getListNavigation().size(); i++) {
            NavigationLinks navigationLinks = navigationLinksValues[i];
            String navigationLinksText = getListNavigation().get(i).getText();
            softAssert.assertTrue(navigationLinksText.equalsIgnoreCase(navigationLinks.getTextLink()), "Text is not contained");
        }
         softAssert.assertAll();
        return this;
    }
}
