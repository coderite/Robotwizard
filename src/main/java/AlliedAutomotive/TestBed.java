package AlliedAutomotive;

import Carters.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by zenbox on 3/1/2016.
 */
public class TestBed {
    public static void main(String[] args) {
        TestBed app = new TestBed();
        app.start();
    }

    private void start() {
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/AlliedAutomotive/alliedCount.txt");

        SeleniumSetup setup = new SeleniumSetup();
        setup.setProxy("186.93.149.117:80");
        WebDriver driver = setup.getDriver();

        MainPage mainPage = new MainPage(driver);
        PartFinderPage partFinderPage = mainPage.clickPartFinder();

        Make carCount = new Make();
        carCount.setMakes(partFinderPage.getMakes());
        System.out.println("# makes: " + carCount.getMakes().size());

        int count = 31186;
        for(int i=12;i<carCount.getMakes().size();i++) {
            Make make = new Make();
            make.setMakes(carCount.getMakes());
            make.setMake(carCount.getMakes().get(i));
            partFinderPage.setValue(make.getMake(), PartFinderPage.Types.MAKE);

            make.setModels(partFinderPage.getModels());
            partFinderPage.setValue(make.getModels().get(0), PartFinderPage.Types.MODEL);

            make.setYears(partFinderPage.getYears());
            partFinderPage.setValue(make.getYears().get(0), PartFinderPage.Types.YEAR);

            make.setSeriesChassis(partFinderPage.getSeriesChassis());
            partFinderPage.setValue(make.getSeriesChassis().get(0), PartFinderPage.Types.SERIESCHASSIS);

            make.setEngines(partFinderPage.getEngines());
            partFinderPage.setValue(make.getEngines().get(0), PartFinderPage.Types.ENGINE);

            make.setVehicleDetails(partFinderPage.getVehicleDetails());
            partFinderPage.setValue(make.getVehicleDetails().get(0), PartFinderPage.Types.VEHICLEDETAILS);

            for (String model : make.getModels()) {
                for (String year : make.getYears()) {
                    for (String seriesChassis : make.getSeriesChassis()) {
                        for (String engine : make.getEngines()) {
                            for (String vehicleDetail : make.getVehicleDetails()) {
                                ++count;
                                System.out.println(count + "\t" + make.getMake() + ":" + model + ":" + year + ":" + seriesChassis + ":" + engine + ":" + vehicleDetail);
                                helper.printItem(count + "\t" + make.getMake() + "\t" + model + "\t" + year + "\t" + seriesChassis + "\t" + engine + "\t" + vehicleDetail);
                            }
                        }
                    }
                }
            }

            // deal with popup
            driver = partFinderPage.closePopup(driver);
        }
        driver.close();
    }
}
