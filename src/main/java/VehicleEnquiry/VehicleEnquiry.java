package VehicleEnquiry;

import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 3/2/2016.
 */
public class VehicleEnquiry {
    public static void main(String[] args) {
        // AA05AMA SMART
        List<Record> records = new ArrayList<>();

        HomePage homepage = new HomePage(new FirefoxDriver());
        homepage.setRegNumber("AA05AMA");
        homepage.setVehicleMake("SMART");
        ResultPage resultpage = homepage.search();
        records.add(resultpage.getRecord());
        resultpage.searchAgain();

        records.forEach(record -> System.out.println(record));

    }
}
