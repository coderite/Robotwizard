package GridWorks;

/**
 * Created by zenbox on 1/2/16.
 */
import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
