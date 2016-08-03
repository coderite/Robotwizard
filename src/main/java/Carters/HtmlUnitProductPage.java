package Carters;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zenbox on 6/11/2016.
 */
public class HtmlUnitProductPage {
    private String url = "http://www.carters.com/carters-kid-girl-dresses/V_271G113.html";

    public static void main(String[] args) throws Exception {
        HtmlUnitProductPage app = new HtmlUnitProductPage();
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);
        app.start();
    }

    public void start() throws Exception {

        System.out.println("HtmlUnit START");
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);

        Document doc = Jsoup.parse(driver.getPageSource());

        System.out.println("Product Page Title: " + driver.getTitle());
        System.out.println("Product Name: " + doc.select(".product-col-2 h1.product-name").text());
        System.out.println("Sales Price: " + doc.select(".product-col-2 .product-price-container .product-price > span.price-sales.clearance").text());
        System.out.println("MSRP Price: " + doc.select(".product-col-2 .product-price-container .product-price > span.price-standard").text());
        //System.out.println(getStandardPrice(driver));

        System.out.println("Test: " + driver.findElement(By.cssSelector(".product-col-2 h1.product-name")).getText());

    }

    public String getTitle(WebDriver driver) {
        WebElement titleElement = driver.findElement(By.cssSelector("h1"));
        return titleElement.getText();
    }

    public String getStandardPrice(WebDriver driver) {
        WebElement salesPrice = driver.findElement(By.cssSelector("span.price-sales.clearance"));
        return salesPrice.getText();
    }

}
