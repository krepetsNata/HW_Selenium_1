package pageTests;

import constants.SkillsItems;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TrainingListPage;

public class TrainingListTest extends BaseTest {

    @Test
    public void searchInSkills() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("ivanhorintest@gmail.com")
                .clickContinueButton()
                .enterPassword("ivanhorintestPassword")
                .clickSignInButton()
                .clickTrainingListButton();

        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnBySkillsButton()
                .clickOnSkillsCheckBox(SkillsItems.JAVA.getTextSkills())
                .verifyCheckbosIsChecked(SkillsItems.JAVA.getTextSkills());

        new TrainingListPage()
                .verifyTitleTrainings(SkillsItems.JAVA.getTextSkills());//????!!! getText from element is empty

        new TrainingListPage()
                .clickOnSkillsCheckBox(SkillsItems.JAVA.getTextSkills())
                .verifyCheckbosIsUnhecked(SkillsItems.JAVA.getTextSkills());

        new TrainingListPage()
                .clickOnSkillsCheckBox(SkillsItems.RUBY.getTextSkills())
                .verifyCheckbosIsChecked(SkillsItems.RUBY.getTextSkills());

        new TrainingListPage()
                .verifyTrainingsIsEmpty();
    }

    @Test
    public void searchInLocations() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail("ivanhorintest@gmail.com")
                .clickContinueButton()
                .enterPassword("ivanhorintestPassword")
                .clickSignInButton()
                .clickTrainingListButton();
        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnByLocationsButton()
                .clickOnCountry("Ukraine")
                .clickOnCitiesCheckBox("Lviv")
                .verifyCountryTrainings("Ukraine");//????!!! getText from element is empty
    }
}
