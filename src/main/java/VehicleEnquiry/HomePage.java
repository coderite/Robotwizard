package VehicleEnquiry;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by zenbox on 3/2/2016.
 */
public class HomePage {
    private String link = "https://www.vehicleenquiry.service.gov.uk";
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        try {
            driver.findElement(By.id("MainContent_txtSearchVrm"));
        } catch (NoSuchElementException ex) {
            driver.get(link);
        }
    }

    public void setRegNumber(String number) {
        driver.findElement(By.id("MainContent_txtSearchVrm")).sendKeys(number);
    }

    public void setVehicleMake(String make) {
        driver.findElement(By.id("MainContent_MakeTextBox")).sendKeys(make);
    }

    public ResultPage search() {
        driver.findElement(By.id("MainContent_butSearch")).click();
        return new ResultPage(driver);
    }
}
