package Carters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by zenbox on 2/2/2016.
 */
public class Sourcer {
    private String link;
    private WebDriver driver;

    public Sourcer() {
        this.driver = new FirefoxDriver();
        driver.get("http://www.carters.com/");

        // close the popup
        WebDriverWait popupClose = new WebDriverWait(driver, 10);
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".padiClose"))).click();
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".vnothanks"))).click();
        driver.manage().window().maximize();
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        driver.get(link);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        String results = driver.getPageSource();
        return results;
    }

    public void close() {
        driver.close();
    }
}
