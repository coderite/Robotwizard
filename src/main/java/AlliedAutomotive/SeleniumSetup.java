package AlliedAutomotive;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by zenbox on 3/1/2016.
 */
public class SeleniumSetup {
    private WebDriver driver;
    private String proxy;

    public WebDriver getDriver() {

        if(driver != null)
            return driver;
        else {
            System.out.println("WebDriver not initialized");
            System.exit(1);
        }
        return null;
    }

    public void setProxy(String proxyString) {
        String proxi = proxyString;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxi).setFtpProxy(proxi).setSslProxy(proxi).setSocksProxy(proxi);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        driver = new FirefoxDriver(cap);
    }
}
