package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataProviderEmailData {

    final static String CSV_FILE = "src/test/java/dataProviders/inappropriate_emails.csv";

    @DataProvider(name = "data_provider_domainname")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"test", "name.surname@mail.co"},
                {"test", "name.surname@mail.com"},
                {"test", "name.surname@mail.commmmmmm"},
                {"test", "name.surname@mail.commmmmmmm"}};
    }

    @DataProvider(name = "data_provider_inappropriate_email")
    public Object[][] dpReadFile() throws IOException { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        List<String> result = Files.readAllLines(Paths.get(CSV_FILE));
        Object[][] objArray = new Object[result.size()][];

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 1; j++) {
                objArray[i] = new Object[1];
                objArray[i][j] = result.get(i);
            }
        }
        return objArray;
    }
}
