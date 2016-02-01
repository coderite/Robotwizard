package GridWorks;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zenbox on 1/2/16.
 */
class LocalDriverFactory {
    private static String urlBase = "http://ec2-52-27-159-114.us-west-2.compute.amazonaws.com:4444/wd/hub";

    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            Capabilities capabilities = DesiredCapabilities.firefox();
            try {
                driver = new RemoteWebDriver(new URL(urlBase), capabilities);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                return null;
            }
            return driver;
        }
        if (browserName.toLowerCase().contains("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
            driver = new ChromeDriver();
            return driver;
        }
        return driver;
    }
}
