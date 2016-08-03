package Marinas.Nams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by zenbox on 4/10/2016.
 * Registrant page class which corresponds to page object notation
 */
public class RegistrantPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrantPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public Registrant getRegistrant() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.job_listing-title")));

        Registrant registrant = new Registrant();

        List<WebElement> tableRows = driver.findElements(By.cssSelector("table > tbody tr"));
        for(int i=0;i<tableRows.size();i++) {
            List<WebElement> rowColumns = tableRows.get(i).findElements(By.cssSelector("td"));
            if(rowColumns.get(0).getText().contains("Member status:"))
                registrant.setMemberStatus(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Date Elected:"))
                registrant.setDateElected(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Certified in:"))
                registrant.setCertifiedIn(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Accepts Assignments"))
                registrant.setAcceptsAssignmentsIn(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Company name"))
                registrant.setCompanyName(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Email"))
                registrant.setEmail(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Website"))
                registrant.setWebsite(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Work Phone"))
                registrant.setWorkPhone(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Toll Free"))
                registrant.setTollFreePhone(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Cell Phone"))
                registrant.setCellPhone(rowColumns.get(1).getText());

            if(rowColumns.get(0).getText().contains("Fax"))
                registrant.setFaxNo(rowColumns.get(1).getText());

            registrant.setName(getName());
        }

        return registrant;
    }

    private String getWorkPhone() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.job_listing-title"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    private String getName() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.job_listing-title"))).getText();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public void back() {
        driver.navigate().back();
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {

        }
    }




}
