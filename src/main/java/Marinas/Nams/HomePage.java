package Marinas.Nams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by zenbox on 4/10/2016.
 */
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        driver.get("http://www.namsglobal.org/");
        driver.manage().window().maximize();
    }

    public void setDiscipline() {

    }

    public void setCountries() {

    }

    public void setSurveyorType() {

    }

    public SearchResultsPage searchListings() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.update_results"))).click();
            try {
                Thread.sleep(10000);
            } catch (Exception ex) {

            }
            return new SearchResultsPage(driver);
        } catch (Exception ex) {
            System.out.println("could not click the search listings button");
        }
        return null;
    }
}
