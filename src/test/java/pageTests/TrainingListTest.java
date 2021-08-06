package pageTests;

import constants.Credentials;
import constants.SkillsItems;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TrainingListPage;

public class TrainingListTest extends BaseTest {

    @Test(priority = 0, description = "Verify checked courses in titles of trainings array(Java and Ruby).")
    public void searchInSkills() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .clickTrainingListButton();

        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnBySkillsButton()
                .clickOnSkillsCheckBox(SkillsItems.JAVA.getTextSkill())
                .verifyCheckbosIsChecked(SkillsItems.JAVA.getTextSkill())
                .verifyTitleTrainings(SkillsItems.JAVA.getTextSkill())

                .clickOnSkillsCheckBox(SkillsItems.JAVA.getTextSkill())
                .verifyCheckbosIsUnhecked(SkillsItems.JAVA.getTextSkill())
                .clickOnSkillsCheckBox(SkillsItems.RUBY.getTextSkill())
                .verifyCheckbosIsChecked(SkillsItems.RUBY.getTextSkill())
                .verifyTrainingsIsEmpty();

        //for running whole class
        new HomePage()
                .clickProfileArrowButton()
                .clickLogOutButton();
    }

    @Test(priority = 1, description = "Verify selected locations in cards of trainings array.")
    public void searchInLocations() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .clickTrainingListButton();
        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnByLocationsButton()
                .clickOnCountry("Ukraine")
                .clickOnCitiesCheckBox("Lviv")
                .verifyCountryTrainings("Ukraine");

        //for running whole class
        new HomePage()
                .clickProfileArrowButton()
                .clickLogOutButton();
    }




    @Test(priority = 2, description = "Verify checked courses in titles of trainings array(Java).")
    public void searchInJavaSkills() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .clickTrainingListButton();

        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnBySkillsButton()
                .clickOnSkillsCheckBox(SkillsItems.JAVA.getTextSkill())
                .verifyCheckbosIsChecked(SkillsItems.JAVA.getTextSkill())
                .verifyTitleTrainings(SkillsItems.JAVA.getTextSkill());

        //for running whole class
        new HomePage()
                .clickProfileArrowButton()
                .clickLogOutButton();
    }

    @Test(priority = 3, description = "Verify checked courses in titles of trainings array(Ruby).")
    public void searchInRubySkills() {
        new HomePage()
                .proceedToHomePage()
                .clickSignInButton()
                .enterEmail(Credentials.CORRECT_CRED.getEmail())
                .clickContinueButton()
                .enterPassword(Credentials.CORRECT_CRED.getPassword())
                .clickSignInButton()
                .clickTrainingListButton();

        new TrainingListPage()
                .clickOnClearLocationsSpan()
                .clickOnSearchInput()
                .clickOnBySkillsButton()
                .clickOnSkillsCheckBox(SkillsItems.RUBY.getTextSkill())
                .verifyCheckbosIsChecked(SkillsItems.RUBY.getTextSkill())
                .verifyTrainingsIsEmpty();

        //for running whole class
        new HomePage()
                .clickProfileArrowButton()
                .clickLogOutButton();
    }
}
