package GridWorks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo {

    @Test
    public void testMethod1() {
        WebDriver driver = LocalDriverManager.getDriver();
        Rocket rocket = new Rocket();
        rocket.setDriver(driver);

        List<String> list = new ArrayList<String>();
        list.add("http://www.arstechnica.com");
        list.add("http://www.apple.com");
        list.add("http://www.arstechnica.com");

        for(String entry: list) {
            rocket.setLink(entry);
            System.out.println("drvr " + driver.hashCode() + " " + entry);
            rocket.run();
        }
    }

    @Test(enabled=false)
    public void testMethod2() {
        invokeBrowser("http://www.techcrunch.com");
    }

    @Test
    public void testMethod3() {
        invokeBrowser("http://www.facebook.com");

    }

    @Test
    public void testMethod4() {
        invokeBrowser("http://www.facebook.com");

    }

    private void invokeBrowser(String url) {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        LocalDriverManager.getDriver().get(url);

    }
}
