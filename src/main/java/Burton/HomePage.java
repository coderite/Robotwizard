package Burton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by zenbox on 2/9/2016.
 */
public class HomePage {
    private final String HOMELINK = "http://www.burtonandburton.com";
    private String cssLoginUserField = "#tbRegCustLogin";
    private String idLoginPasswordField = "tbRegCustPwd";
    private String idLoginSubmitButton = "LOGIN";
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(HOMELINK);
    }

    public void login(String user, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLoginUserField))).sendKeys(user);

        WebElement passwordField = driver.findElements(By.id(idLoginPasswordField)).get(1);
        passwordField.click();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idLoginSubmitButton))).click();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
