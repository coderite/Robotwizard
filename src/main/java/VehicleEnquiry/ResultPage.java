package VehicleEnquiry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by zenbox on 3/2/2016.
 */
public class ResultPage {
    private WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage searchAgain() {
        driver.findElement(By.id("MainContent_butSearchAgain")).click();

        return new HomePage(driver);
    }

    public Record getRecord() {
        Record record = new Record();

        Document doc = Jsoup.parse(driver.getPageSource());

        Element taxElement = doc.select(".isValidTax p").first();
        String tax = taxElement.text().split(":")[1].trim();

        Element motElement = doc.select(".isValidMot p").first();
        String mot = motElement.text().split(":")[1].trim();

        Elements list = doc.select(".ul-data li strong");
        String vehicleMake = list.get(0).text().trim();
        String dateOfFirstRegistration = list.get(1).text().trim();
        String yearOfManufacture = list.get(2).text().trim();
        String cylinderCapacity = list.get(3).text().trim();
        String co2Emissions = list.get(4).text().trim();
        String fuelType = list.get(5).text().trim();
        String vehicleStatus = list.get(6).text().trim();
        String vehicleColour = list.get(7).text().trim();
        String vehicleTypeApproval = list.get(8).text().trim();
        String wheelplan = list.get(9).text().trim();
        String revenueWeight = list.get(10).text().trim();

        record.setTaxDueOn(tax);
        record.setMotEpxiresOn(mot);
        record.setVehicleMake(vehicleMake);
        record.setDateOfFirstRegistration(dateOfFirstRegistration);
        record.setYearOfManufacture(yearOfManufacture);
        record.setCylinderCapacity(cylinderCapacity);
        record.setCo2Emissions(co2Emissions);
        record.setFuelType(fuelType);
        record.setVehicleStatus(vehicleStatus);
        record.setVehicleColour(vehicleColour);
        record.setVehicleTypeApproval(vehicleTypeApproval);
        record.setWheelplan(wheelplan);
        record.setRevenueWeight(revenueWeight);

        return record;
    }
}
