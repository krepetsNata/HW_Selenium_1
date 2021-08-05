package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TrainingListPage extends BasePage {

    private static final Logger LOG = Logger.getLogger(TrainingListPage.class);
    private SoftAssert softAssert = new SoftAssert();

    @FindBy(name = "training-filter-input")
    private WebElement searchInput;

    @FindBy(xpath = "//span[@ng-show='filteredTrainings.length == 0']")
    private WebElement nullTrainingsSpan;

    @FindBy(xpath = "//span[@ng-click='clearAllLocations(totalLocationList)']")
    private WebElement clearLocationsSpan;

    @FindBy(xpath = "//span[@ng-click='clearAllSkills(selectedSkills)']")
    private WebElement clearSkillsSpan;

    @FindBy(xpath = "//div[@ng-click=\"changeTab('Skill')\"]")
    private WebElement bySkillsButton;

    @FindBy(xpath = "//div[@ng-click=\"changeTab('Location')\"]")
    private WebElement byLocationsButton;

    @FindBy(xpath = "//label[contains(@class,'location')]//self::text()[normalize-space(.)='Java']//preceding-sibling::input")
    private WebElement javaCheckBox;

    @FindBy(xpath = "//label[contains(@class,'location')]//self::text()[normalize-space(.)='Ruby']")
    private WebElement rubyCheckBox;

    @FindBy(xpath = "//ul[contains(@class,'location__cities-list-skills')]//li//label[contains(@class,'location') and normalize-space()]")
    private List<WebElement> listSkills;

    @FindBy(xpath = "//ul[contains(@class,'location__cities-list-cities')]//li//label[contains(@class,'location') and normalize-space()]")
    private List<WebElement> listCities;

    @FindBy(xpath = "//div[contains(@class,'training-list__container')]//div[@training-item='itemTraining']")
    private List<WebElement> listTrainings;

    @FindBy(xpath = "//ul[@class='location__countries-list-countries']//li")
    private List<WebElement> listCountries;

    public List<WebElement> getListSkills() {
        if (listSkills.isEmpty()) {
            LOG.warn("List is empty");
            return null;
        }
        return listSkills;
    }

    public List<WebElement> getListCities() {
        if (listCities.isEmpty()) {
            LOG.warn("List is empty");
            return null;
        }
        return listCities;
    }

    public List<WebElement> getListTrainings() {
        if (listTrainings.isEmpty()) {
            LOG.warn("List is empty");
            return null;
        }
        return listTrainings;
    }

    public List<WebElement> getListCountries() {
        if (listCountries.isEmpty()) {
            LOG.warn("List is empty");
            return null;
        }
        return listCountries;
    }

    public WebElement getCheckBoxFromListByName(List<WebElement> list, String textItem) {
        WebElement checkBox = null;
        for (WebElement e : list) {
            if (e.getText().contains(textItem)) {
                checkBox = e.findElement(By.xpath("//self::text()[normalize-space()='" + textItem + "']//preceding-sibling::input"));
                break;
            }
//            else {
//                LOG.warn(System.out.format("CheckBox with name '%s' is not exist\n", textItem));
//            }
        }
        return checkBox;
    }

    public WebElement getCountryFromListByName(String textItem) {
        WebElement coutryLi = null;
        for (WebElement e : getListCountries()) {
            if (e.getText().contains(textItem)) {
                coutryLi = e;
                break;
            }
//            else {
//                LOG.warn(System.out.format("Country with name '%s' is not exist\n", textItem));
//            }
        }
        return coutryLi;
    }

    public TrainingListPage clickOnSearchInput() {
        waitForPageLoadComplete(10);
        waitElementToBeClickable(10, searchInput).click();
        LOG.info("SearchInput was clicked.");
        return this;
    }

    public TrainingListPage clickOnBySkillsButton() {
        waitElementToBeClickable(10, bySkillsButton).click();
        LOG.info("BySkillsButton was clicked.");
        return this;
    }

    public TrainingListPage clickOnByLocationsButton() {
        waitElementToBeClickable(10, byLocationsButton).click();
        LOG.info("ByLocationsButton was clicked.");
        return this;
    }

    public TrainingListPage clickOnSkillsCheckBox(String textItem) {//skills
        waitForPageLoadComplete(20);
        //накладення WebElements  so JavascriptExecutor
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getCheckBoxFromListByName(getListSkills(), textItem));
        //waitElementToBeClickable(20,getCheckBoxSkillFromList(textItem)).click();
        //waitVisibilityOfElement(20,javaCheckBox);
        //javaCheckBox.click();
        LOG.info(System.out.format("Skill-Checkbox '%s' was clicked.\n", textItem));
        return this;
    }

    public TrainingListPage clickOnCitiesCheckBox(String textItem) {//cities
        waitForPageLoadComplete(20);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getCheckBoxFromListByName(getListCities(), textItem));
        LOG.info(System.out.format("City-Checkbox '%s' was clicked.\n", textItem));
        return this;
    }

    public TrainingListPage clickOnCountry(String textItem) {
        waitElementToBeClickable(10, getCountryFromListByName(textItem)).click();
        LOG.info(System.out.format("Country was selected - %s.\n", textItem));
        return this;
    }

    public TrainingListPage clickOnClearLocationsSpan() {
        waitForPageLoadComplete(20);
        //накладення WebElements, so, JavascriptExecutor
        //waitElementToBeClickable(10,clearLocationsSpan).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", clearLocationsSpan);
        LOG.info("clearLocationsSpan was clicked - filter by locations was cleared.");
        return this;
    }

    public TrainingListPage clickOnClearSkillsSpan() {
        waitForPageLoadComplete(20);
        //накладення WebElements, so, JavascriptExecutor
        //waitElementToBeClickable(10,clearSkillsSpan).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", clearSkillsSpan);
        LOG.info("clearLocationsSpan was clicked - filter by locations was cleared.");
        return this;
    }

    public boolean isCheckboxIsSelected(List<WebElement> list, String textItem) {
        boolean isSelected = getCheckBoxFromListByName(list, textItem).isSelected();
        LOG.info(String.format("Is '%s' checkbox selected': '%s'\n", textItem, isSelected));
        return isSelected;
    }

    public void verifyCheckbosIsChecked(String textItem) { //skills
        Assert.assertTrue(isCheckboxIsSelected(getListSkills(), textItem), String.format("'%s' checkbox is NOT checked", textItem));
    }

    public void verifyCheckbosIsUnhecked(String textItem) { //skills
        Assert.assertFalse(isCheckboxIsSelected(getListSkills(), textItem), String.format("'%s' checkbox IS checked", textItem));
    }

    public void verifyTitleTrainings(String textTitleTraining) {
        if (getListTrainings() != null) {
            boolean flag = false;
            //System.out.println("Training LIST SIZE: " + getListTrainings().size());

            for (WebElement el : getListTrainings()) {
                WebElement elTitle = el.findElement(By.xpath("//div[contains(@class,'training-item__title')]"));
                String elText = elTitle.getAttribute("textContent").trim();// getText() from element is empty

                if (elText.contains(textTitleTraining)) {
                    flag = true;
                } else {
                    flag = false;
                }
                softAssert.assertTrue(flag, String.format("'%s' incorrect key word ", textTitleTraining));
            }
            softAssert.assertAll();
        } else {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
    }

    public void verifyCountryTrainings(String textCountryTraining) {
        if (getListTrainings() != null) {
            boolean flag = false;
            //System.out.println("Training LIST SIZE: " + getListTrainings().size());

            for (WebElement el : getListTrainings()) {
                WebElement elLocation = el.findElement(By.xpath("//*[contains(@class,'training-item__location--text')]"));
                String elText = elLocation.getAttribute("textContent").trim();

                if (elText.equalsIgnoreCase(textCountryTraining)
                        || elText.equalsIgnoreCase("Multi-location")) {
                    flag = true;
                } else {
                    flag = false;
                }
                softAssert.assertTrue(flag, String.format("'%s' incorrect key word ", textCountryTraining));
            }
            softAssert.assertAll();
        } else {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
    }

    public void verifyTrainingsIsEmpty() {
        if (getListTrainings() == null) {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
    }
}
