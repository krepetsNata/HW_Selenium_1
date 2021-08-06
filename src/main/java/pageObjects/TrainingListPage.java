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
            //return null;
        }
        return listSkills;
    }

    public List<WebElement> getListCities() {
        if (listCities.isEmpty()) {
            LOG.warn("List is empty");
            //return null;
        }
        return listCities;
    }

    public List<WebElement> getListTrainings() {
        if (listTrainings.isEmpty()) {
            LOG.warn("List is empty");
            //return null;
        }
        return listTrainings;
    }

    public List<WebElement> getListCountries() {
        if (listCountries.isEmpty()) {
            LOG.warn("List is empty");
            //return null;
        }
        return listCountries;
    }

    public WebElement getCheckBoxFromListByName(List<WebElement> listWebElemets, String checkBoxText) {
        WebElement checkBoxInput = null;
        for (WebElement e : listWebElemets) {
            if (e.getText().contains(checkBoxText)) {
                checkBoxInput = e.findElement(By.xpath("//self::text()[normalize-space()='" + checkBoxText + "']//preceding-sibling::input"));
                break;
            }
        }
        return checkBoxInput;
    }

    public WebElement getCountryFromListByName(String countryText) {
        WebElement coutryLi = null;
        for (WebElement e : getListCountries()) {
            if (e.getText().contains(countryText)) {
                coutryLi = e;
                break;
            }
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
        waitElementToBeClickable(15, bySkillsButton).click();
        LOG.info("BySkillsButton was clicked.");
        return this;
    }

    public TrainingListPage clickOnByLocationsButton() {
        waitElementToBeClickable(10, byLocationsButton).click();
        LOG.info("ByLocationsButton was clicked.");
        return this;
    }

    public TrainingListPage clickOnSkillsCheckBox(String skillsCheckBoxText) {//skills
        waitForPageLoadComplete(20);
        WebElement skillCheckBoxElem = getCheckBoxFromListByName(getListCities(), skillsCheckBoxText);

        if (skillCheckBoxElem != null) {
            //накладення WebElements  so JavascriptExecutor
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", skillCheckBoxElem);
            LOG.info(System.out.format("Skill-Checkbox '%s' was clicked.\n", skillsCheckBoxText));
        } else {
            Assert.assertNotNull(skillCheckBoxElem, String.valueOf(System.out.format("Skill-CheckBox with name '%s' is not exist\n", skillsCheckBoxText)));
        }
        return this;
    }

    public TrainingListPage clickOnCitiesCheckBox(String citiesCheckBoxText) {//cities
        waitForPageLoadComplete(20);
        WebElement cityCheckBoxElem = getCheckBoxFromListByName(getListCities(), citiesCheckBoxText);

        if (cityCheckBoxElem != null) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", cityCheckBoxElem);
            LOG.info(System.out.format("City-Checkbox '%s' was clicked.\n", citiesCheckBoxText));
        } else {
            Assert.assertNotNull(cityCheckBoxElem, String.valueOf(System.out.format("City-CheckBox with name '%s' is not exist\n", citiesCheckBoxText)));
        }
        return this;
    }

    public TrainingListPage clickOnCountry(String countryLiText) {
        WebElement countryElem = getCountryFromListByName(countryLiText);

        if (countryElem != null) {
            waitElementToBeClickable(10, countryElem).click();
            LOG.info(System.out.format("Country was selected - %s.\n", countryLiText));
        } else {
            Assert.assertNotNull(countryElem, String.valueOf(System.out.format("Country with name '%s' is not exist\n", countryLiText)));
        }
        return this;
    }

    public TrainingListPage clickOnClearLocationsSpan() {
        waitForPageLoadComplete(30);
        waitVisibilityOfElement(30, clearLocationsSpan);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", clearLocationsSpan);
        LOG.info("clearLocationsSpan was clicked - filter by locations was cleared.");
        return this;
    }

    public TrainingListPage clickOnClearSkillsSpan() {
        waitForPageLoadComplete(20);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", clearSkillsSpan);
        LOG.info("clearLocationsSpan was clicked - filter by locations was cleared.");
        return this;
    }

    public TrainingListPage verifyCheckbosIsChecked(String skillsCheckBoxText) { //skills
        Assert.assertTrue(getCheckBoxFromListByName(getListSkills(), skillsCheckBoxText).isSelected(), String.format("'%s' checkbox is NOT checked", skillsCheckBoxText));
        return this;
    }

    public TrainingListPage verifyCheckbosIsUnhecked(String skillsCheckBoxText) { //skills
        Assert.assertFalse(getCheckBoxFromListByName(getListSkills(), skillsCheckBoxText).isSelected(), String.format("'%s' checkbox IS checked", skillsCheckBoxText));
        return this;
    }

    public TrainingListPage verifyTitleTrainings(String titleTrainingText) {
        if (getListTrainings().size() != 0) {

            for (WebElement training : getListTrainings()) {
                WebElement elTitle = training.findElement(By.xpath("//div[contains(@class,'training-item__title')]"));
                String elText = elTitle.getAttribute("textContent").trim();// getText() from element is empty

                softAssert.assertTrue(elText.contains(titleTrainingText), String.format("'%s' incorrect key word ", titleTrainingText));
            }
            softAssert.assertAll();
        } else {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
        return this;
    }

    public TrainingListPage verifyCountryTrainings(String countryTrainingText) {
        if (getListTrainings().size() != 0) {
            boolean flag = false;

            for (WebElement training : getListTrainings()) {
                WebElement elLocation = training.findElement(By.xpath("//*[contains(@class,'training-item__location--text')]"));
                String elText = elLocation.getAttribute("textContent").trim();

                flag = (elText.equalsIgnoreCase(countryTrainingText) || elText.equalsIgnoreCase("Multi-location")) ? true : false;

                softAssert.assertTrue(flag, String.format("'%s' incorrect key word ", countryTrainingText));
            }
            softAssert.assertAll();
        } else {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
        return this;
    }

    public TrainingListPage verifyTrainingsIsEmpty() {
        if (getListTrainings().size() == 0) {
            Assert.assertEquals(nullTrainingsSpan.getText(), "No training are available.", "List is empty - trainings are not exist");
        }
        return this;
    }
}
