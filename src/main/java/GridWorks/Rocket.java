package GridWorks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

/**
 * Created by zenbox on 1/3/16.
 */
public class Rocket implements Runnable {
    private WebDriver driver;
    private String link;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void run() {
        driver.get(link);
        try {
            WebElement waitForElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#footer-nav > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a")));
            waitForElement.click();
            WebElement waitForHeader = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#archive-head > span")));
        } catch (NoSuchElementException ex) {
            System.out.println("ELEMENT NOT FOUND " + ex.getMessage());
        } catch (TimeoutException ex) {
            System.out.println("ELEMENT TIME OUT " + ex.getMessage());
            throw new SkipException("TIMEOUT SKIPPING TEST");
        }
    }
}
