package Marinas.Force5;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by zenbox on 4/9/2016.
 */
public class DirectoryPage {
    private WebDriver driver;

    public DirectoryPage() {
        driver = new FirefoxDriver();
        driver.get("http://www.force5.org/surveyors");
    }

    public void gotoNextPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#block-system-main > div > div.item-list > ul > li.pager-next.odd > a"))).click();
        } catch (NoSuchElementException ex) {

        }
    }

    public void quit() {
        driver.close();
    }

    public int getNumberOfRegistrants() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector(".view-content > table tbody tr"));
        return tableRows.size();
    }

    public RegistrantPage viewEntry(int number) {
        List<WebElement> tableRows = driver.findElements(By.cssSelector(".view-content > table tbody tr"));
        tableRows.get(number).findElement(By.cssSelector("a")).click();
        return new RegistrantPage(driver);
    }
}
