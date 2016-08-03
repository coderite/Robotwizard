package Marinas.Nams;

import Carters.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by zenbox on 4/10/2016.
 */
public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void iterateListings() {
        int numberOfListings = getNumberOfListings();
        System.out.println(numberOfListings);
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Marinas/Nams.txt");

        List<WebElement> entries;
        for(int i=0;i<numberOfListings;i++) {
            entries = getEntries();
            RegistrantPage registrantPage = clickRegistrant(entries, i);
            Registrant registrant = registrantPage.getRegistrant();
            System.out.println(registrant);
            helper.printItem(registrant.toPrint());
            registrantPage.back();
        }
    }

    public RegistrantPage clickRegistrant(List<WebElement> registrants, int index) {
        WebElement selectedRegistrant = registrants.get(index);
        Actions actions = new Actions(driver);
        actions.moveToElement(selectedRegistrant);
        actions.perform();
        selectedRegistrant.click();
        return new RegistrantPage(driver);
    }

    public List<WebElement> getEntries() {
        return driver.findElements(By.cssSelector("ul.job_listings li"));
    }

    public int getNumberOfListings() {
        return driver.findElements(By.cssSelector("ul.job_listings li")).size();

    }

    public void close() {
        driver.close();
    }



}
