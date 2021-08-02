package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class DataProviderEmailData {
    @DataProvider(name = "data_provider_domainname")
    public Object[][] dpMethod(){
        return new Object[][] {
                {"name.surname@mail.co"},
                {"name.surname@mail.com"},
                {"name.surname@mail.commmmmmm"},
                {"name.surname@mail.commmmmmmm"},};
    }


    final static String CSV_FILE = "src/test/java/dataProviders/inappropriate_emails.csv";
    final static String DELIMETER = ",";

    @DataProvider(name = "data_provider_inappropriate_email")
    public Iterator<Object[]> testDP(){
        try {
            Scanner scanner = new Scanner(new File(CSV_FILE)).useDelimiter(DELIMETER);
            return new Iterator<Object[]>() {
                @Override
                public boolean hasNext() {
                    return scanner.hasNext();
                }
                @Override
                public Object[] next() {
                    return new Object[]{scanner.next()};
                }
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
