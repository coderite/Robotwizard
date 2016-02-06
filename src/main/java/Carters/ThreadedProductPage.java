package Carters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by zenbox on 1/30/16.
 */
public class ThreadedProductPage {
    private WebDriver driver;
    private ArrayList<String> results = new ArrayList<String>();
    private String title;
    private String url;
    private String link;
    private Helpers helper;

    public void setHelper(Helpers helper) {
        this.helper = helper;
    }

    public ThreadedProductPage(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {

        }
        closePopups("http://www.carters.com");
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {

        }
    }

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

    public void closePopups(String link) {
        driver.get(link);

        // close the popup
        WebDriverWait popupClose = new WebDriverWait(driver, 10);
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".padiClose"))).click();
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".vnothanks"))).click();
        driver.manage().window().maximize();
    }

    public void collect(String link) {
        driver.get(link); // high level get the link

        int bigOkCount = 1;
        boolean bigOk = true;
        do {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#pdpMain > div.product-detail-cols.clearfix > div.product-col-2.product-detail > h1")));
                WebElement titleElement = driver.findElement(By.cssSelector("#pdpMain > div.product-detail-cols.clearfix > div.product-col-2.product-detail > h1"));
                title = titleElement.getText();

                // get number of colors
                try {
                    int colorsSize = driver.findElements(By.cssSelector("ul.swatches.color li")).size() - 1;

                    colorLoop:
                    for (int i = 0; i < colorsSize; i++) {
                        boolean colorOk = false;
                        String colorText = "N/A";
                        WebElement colorElement;
                        do {
                            try {
                                colorElement = driver.findElement(By.cssSelector("ul.swatches.color li:nth-child(" + (i + 1) + ")"));
                                colorText = colorElement.getText();
                                colorElement.click();
                                colorOk = true;
                            } catch (StaleElementReferenceException ex) {
                                System.out.println("Error " + ex.getMessage());
                            } catch (ElementNotVisibleException ex) {
                                System.out.println("Error " + ex.getMessage());
                                colorOk = true;
                            }
                        } while (!colorOk);


                        // for each color get size
                        int size = driver.findElements(By.cssSelector("ul.swatches.size li")).size();

                        for (int j = 0; j < size; j++) {
                            if (driver.findElements(By.cssSelector("ul.swatches.size li:nth-child(" + (j + 1) + ").emptyswatch.unselectable")).size() != 0) {
                                continue;
                            }

                            String sizeText = "";
                            WebElement sizeElement;
                            boolean ok = false;
                            int retryCount = 0;
                            do {
                                try {
                                    sizeElement = driver.findElement(By.cssSelector("ul.swatches.size li:nth-child(" + (j + 1) + ")"));
                                    sizeText = sizeElement.getText();
                                    sizeElement.click();
                                    ok = true;
                                } catch (ElementNotVisibleException ex) {
                                    System.out.println("errror: sizes not visible");
                                    ok = true;
                                } catch (NoSuchElementException ex) {
                                    System.out.println("error: no such element - size swatch " + link);
                                    retryCount++;
                                    if (retryCount > 2) {
                                            System.out.println(" breaking on " + link);
                                        throw new RefreshException(); // go to outer loop to reload page and try again
                                    }
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

                            String finalUrl = driver.getCurrentUrl().replaceAll("http://www.carters.com/", "");
                            try {
                                finalUrl = finalUrl.substring(0, finalUrl.indexOf("/")).replaceAll("carters-", "");
                            } catch (Exception ex) {
                                System.out.println("FINAL URL: something went wrong, digressing");
                            }

                            // build string
                            StringBuilder sb = new StringBuilder();
                            sb.append(title + ";"); // add title
                            sb.append(sizeText + ";"); // add
                            sb.append(colorText + ";"); // add color
                            sb.append(upc + ";"); // add upc code
                            sb.append(standardPrice + ";"); // add msrp standard price
                            sb.append(salesPrice + ";"); // add sales price
                            sb.append(finalUrl); // add current url
                            sb.append(url); // add full url for diagnostical purposes

                            System.out.println(sb.toString());
                            helper.printItem(sb.toString());
                            results.add(sb.toString());
                            bigOk = true;
                        }
                    }
                } catch (TimeoutException ex) {
                    System.out.println(title + "probably out of stock");
                }
            } catch (RefreshException ex) {
                if(bigOkCount < 4) {
                    ex.printStackTrace();
                    System.out.println("Error: refreshing page #" + bigOkCount);
                    driver.navigate().refresh();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    bigOk = false;
                } else {
                    Helpers helper = new Helpers();
                    helper.setOutputFile("skipped.txt");
                    helper.printItem(link);
                    bigOk = true;
                }
                bigOkCount++;
            }
        } while(!bigOk); // end of big okay
    }
}
