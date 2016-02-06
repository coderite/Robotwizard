package Carters;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 1/30/16.
 */
public class ProductPage {
    private WebDriver driver;
    private ArrayList<String> results = new ArrayList<String>();
    private String title;
    private String url;
    private String link;
    private String duplicatePreventionString = "na";
    private String duplicatePreventionString2 = "na";

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductPage() { }

    public ArrayList<String> getResults() {
        return results;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void multiStartInitiate(String link) {
        driver.get(link);

        // close the popup
        WebDriverWait popupClose = new WebDriverWait(driver, 10);
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".padiClose"))).click();
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".vnothanks"))).click();
        driver.manage().window().maximize();
    }

    public void multiStartRun(String link) {
        driver.get(link);

        // for each link do
        collect();
    }

    public void collect() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#pdpMain > div.product-detail-cols.clearfix > div.product-col-2.product-detail > h1")));
        WebElement titleElement = driver.findElement(By.cssSelector("#pdpMain > div.product-detail-cols.clearfix > div.product-col-2.product-detail > h1"));
        title = titleElement.getText();

        this.url = driver.getCurrentUrl();

        // get number of colors
        try {
            int colorsSize = driver.findElements(By.cssSelector("ul.swatches.color li")).size() - 1;

            for (int i = 0; i < colorsSize; i++) {
                boolean colorOk = false;
                String colorText = "N/A";
                WebElement colorElement;
                do {
                    try {
                        colorElement = driver.findElement(By.cssSelector("ul.swatches.color li:nth-child("+ (i+1) +")"));
                        colorText = colorElement.getText();
                        colorElement.click();
                        colorOk = true;
                    } catch (StaleElementReferenceException ex) {
                        System.out.println("Error " + ex.getMessage());
                    }
                } while(!colorOk);


                // for each color get size
                int size = driver.findElements(By.cssSelector("ul.swatches.size li")).size();

                for (int j = 0; j<size; j++) {
                    if(driver.findElements(By.cssSelector("ul.swatches.size li:nth-child("+ (j+1) +").emptyswatch.unselectable")).size() != 0) {
                        continue;
                    }

                    String sizeText = "";
                    WebElement sizeElement;
                    boolean ok = false;
                    do {
                        try {
                            sizeElement = driver.findElement(By.cssSelector("ul.swatches.size li:nth-child("+ (j+1) +")"));
                            sizeText = sizeElement.getText();
                            sizeElement.click();
                            ok = true;
                        }  catch (NoSuchElementException ex) {
                            System.out.println("error: no such element - size swatch");
                        } catch (StaleElementReferenceException ex) {
                            System.out.println("error: stale element reference - size swatch");
                        }
                    } while (!ok);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {

                    }

                    // get MSRP standard price
                    String standardPriceCss = "#product-content > div.product-price > span.price-standard";
                    String standardPrice = "N/A";
                    boolean isOk = false;
                    try {
                        do {
                            try {
                                WebElement msrpElement = driver.findElement(By.cssSelector(standardPriceCss));
                                standardPrice = msrpElement.getText();
                                standardPrice = standardPrice.replaceAll("MSRP\\*:", "").trim();
                                isOk = true;
                            } catch (StaleElementReferenceException ex) {
                                System.out.println("error: stale element reference - standard price");
                            }
                        } while (!isOk);
                    } catch (NoSuchElementException ex) {
                        System.out.println("error: no such element - standard price");
                    }

                    // get sales price
                    String salesPriceCss = "#product-content > div.product-price > span.price-sales";
                    String salesPrice = "N/A";
                    boolean salesPriceOk = false;
                    try {
                        do {
                            try {
                                WebElement salesPriceElement = driver.findElement(By.cssSelector(salesPriceCss));
                                salesPrice = salesPriceElement.getText();
                                salesPriceOk = true;
                            } catch (StaleElementReferenceException ex) {
                                System.out.println("error: stale element reference - sales price");
                            }
                        } while (!salesPriceOk);
                    } catch (NoSuchElementException ex) {
                        System.out.println("error: no such element - sales price");
                    }
                    // get upc code
                    String upcCss = ".product-number";
                    String upc = "N/A";
                    boolean upcOk = false;
                    try {
                        do {
                            try {
                                WebElement upcElement = driver.findElement(By.cssSelector(upcCss));
                                upc = upcElement.getText();
                                upc = upc.replaceAll("Style #", "").trim();
                                upcOk = true;
                            } catch (StaleElementReferenceException ex) {
                                System.out.println("error: stale element reference - product number");
                            }
                        } while (!upcOk);
                    } catch (NoSuchElementException ex) {
                        System.out.println("error: no such element - product number");
                    }

                    // build string
                    StringBuilder sb = new StringBuilder();
                    sb.append(title + ";"); // add title
                    sb.append(sizeText + ";"); // add
                    sb.append(colorText + ";"); // add color
                    sb.append(upc + ";"); // add upc code
                    sb.append(standardPrice + ";"); // add msrp standard price
                    sb.append(salesPrice); // add sales price

                    System.out.println(sb.toString());
                    Helpers helper = new Helpers();
                    helper.setOutputFile("resultsCarterFeb2.txt");
                    helper.printItem(sb.toString());
                    results.add(sb.toString());
                }
            }
        } catch (TimeoutException ex) {
            System.out.println(title + "probably out of stock");
        }
    }
}
