package Marinas.Nams;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by zenbox on 4/10/2016.
 *
 * THIS APP WILL SCRAPE NAMSGlobal for marine surveyors.
 * www.namsglobal.org
 */
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
        HomePage homepage = new HomePage(new ChromeDriver());
        SearchResultsPage searchResultsPage = homepage.searchListings();
        searchResultsPage.iterateListings();
        searchResultsPage.close();
    }
}
