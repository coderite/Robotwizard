package Marinas.SAMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 4/10/2016.
 */
public class SurveyorsByState {
    private WebDriver driver;

    public SurveyorsByState(WebDriver driver) {
        this.driver = driver;
    }

    public void loopThroughState() {
        driver.get("http://www.marinesurvey.org/us-regions.html");
        List<WebElement> states = driver.findElements(By.cssSelector("#page > table > tbody a"));
        List<String> sStates = new ArrayList<>();
        for(WebElement state : states) {
            sStates.add(state.getAttribute("href"));
        }

        for(String state : sStates) {
            System.out.println(state);
            MarineSurvey marineSurvey = new MarineSurvey(driver);
            marineSurvey.setLink(state);
            marineSurvey.start();
        }
        driver.close();
    }
}
