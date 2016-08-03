package Carters;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zenbox on 2/3/2016.
 */
public class Teest {
    public static void main(String[] args) throws Exception {

        Teest app = new Teest();
        app.start();

    }

    public void start() throws Exception {
        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.carters.com/carters-baby-girl-sets/V_121G770.html");

        Thread.sleep(10000);

        if(driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("hideBrRpQv();");
        }


    }
}
