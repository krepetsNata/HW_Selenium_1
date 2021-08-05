package pageObjects;

import constants.NavigationLinks;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BlogPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BlogPage.class);

    @FindBy(xpath = "//div[contains(@class,'tab-nav__list')]//a")
    private List<WebElement> listNavigation;

    public List<WebElement> getListNavigation() {
        if (listNavigation.isEmpty()) {
            LOG.warn("List is empty");
            return null;
        }
        return listNavigation;
    }

    public void verifyListContainsText() {
        boolean itemNav = false;
        NavigationLinks[] vals = NavigationLinks.values();

        for (int i = 0; i < getListNavigation().size(); i++) {
            NavigationLinks nl = vals[i];
            String s = getListNavigation().get(i).getText();

            if (getListNavigation().get(i).getText().equalsIgnoreCase(nl.getTextLinks())) {
                itemNav = true;
            } else {
                itemNav = false;
            }
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(itemNav, "Text is not contained");
        softAssert.assertAll();
    }
}
