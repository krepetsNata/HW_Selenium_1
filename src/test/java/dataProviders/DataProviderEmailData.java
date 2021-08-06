package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataProviderEmailData {

    final static String CSV_FILE = "src/test/java/dataProviders/inappropriate_emails.csv";

    @DataProvider(name = "data_provider_domainname")
    public Object[][] dpCheckingDomainNameFromArray() {
        return new Object[][]{
                {"name.surname@mail.co"},
                {"name.surname@mail.com"},
                {"name.surname@mail.commmmmmm"},
                {"name.surname@mail.commmmmmmm"}};
    }

    @DataProvider(name = "data_provider_inappropriate_email")
    public Object[][] dpCheckingWrongEmailsFromFile() throws IOException { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        List<String> emailsList = Files.readAllLines(Paths.get(CSV_FILE));
        Object[][] emailsObj = new Object[emailsList.size()][];

        for (int i = 0; i < emailsList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                emailsObj[i] = new Object[1];
                emailsObj[i][j] = emailsList.get(i);
            }
        }
        return emailsObj;
    }
}
