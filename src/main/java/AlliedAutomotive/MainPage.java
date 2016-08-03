package AlliedAutomotive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by zenbox on 3/1/2016.
 */
public class MainPage {
    private WebDriver driver;

    public MainPage (WebDriver driver) {
        this.driver = driver;
        driver.get("http://www.alliedautomotive.com.au/");
    }

    public PartFinderPage clickPartFinder() {
        driver.findElement(By.cssSelector("li.part-finder-section:nth-child(1) > a:nth-child(1)")).click();
        return new PartFinderPage(driver);
    }
}
