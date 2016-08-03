package Marinas.Force5;

import org.omg.IOP.ExceptionDetailMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by zenbox on 4/9/2016.
 */
public class RegistrantPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrantPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void back() {
        driver.navigate().back();
    }

    public Registrant getRegistrant() {
        Registrant registrant = new Registrant();
        registrant.setFirstName(getFirstName());
        registrant.setLastName(getLastName());
        registrant.setCompany(getCompany());
        registrant.setAddress(getAddress());
        registrant.setCity(getCity());
        registrant.setState(getState());
        registrant.setZip(getZip());
        registrant.setPhone(getPhone());
        registrant.setEmail(getEmail());

        return registrant;
    }

    public String getFirstName() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-first-name.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getLastName() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-last-name.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getCompany() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-company-name.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getAddress() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-address.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getCity() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-city.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getState() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-state.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getZip() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-zip-code.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getPhone() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-phone.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String getEmail() {
        try {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.field.field-name-field-email-address.field-type-text.field-label-above.view-mode-full > div > div"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

}
