package Carters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by zenbox on 1/30/16.
 */
public class ThreadedProductPageMin {
    private WebDriver driver;
    private ArrayList<String> results = new ArrayList<String>();
    private String title;
    private String url;
    private String link;
    private Helpers helper;

    public void setHelper(Helpers helper) {
        this.helper = helper;
    }

    public ThreadedProductPageMin(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {

        }
        //closePopups("http://www.carters.com");
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
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.product-name")));
                WebElement titleElement = driver.findElement(By.cssSelector("h1.product-name"));
                title = titleElement.getText();

                // get number of colors
                try {
                    int colorsSize = driver.findElements(By.cssSelector("#product-content .product-variations ul.swatches.color li")).size() - 1;
                    System.out.println("number of colors: " + colorsSize);

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

                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {

                        }


                        // for each color get size
                        int size = driver.findElements(By.cssSelector("#product-content .product-variations ul.swatches.size li")).size();
                        System.out.println("found sizes : " + size);

                        for (int j = 0; j < size; j++) {
                            if (driver.findElements(By.cssSelector("#product-content .product-variations ul.swatches.size li:nth-child(" + (j + 1) + ").emptyswatch.unselectable")).size() != 0) {
                                continue;
                            }

                            String sizeText = "N/A";
                            if(size != 1) {
                                WebElement sizeElement;
                                boolean ok = false;
                                int retryCount = 0;
                                do {
                                    try {
                                        sizeElement = driver.findElement(By.cssSelector("#product-content .product-variations ul.swatches.size li:nth-child(" + (j + 1) + ")"));
                                        sizeText = sizeElement.getText();
                                        sizeElement.click();
                                        ok = true;
                                    } catch (ElementNotVisibleException ex) {
                                        System.out.println("error: sizes not visible");
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
                            }

                            // get MSRP standard price
                            String standardPrice = getMsrpPrice(driver);

                            // get sales price
                            String salesPrice = getSalesPrice(driver);

                            // get upc code
                            String upc = getUpcCode(driver);

                            // get final url
                            String finalUrl = getFinalUrl(driver);

                            // build string
                            StringBuilder sb = new StringBuilder();
                            sb.append(title + ";"); // add title
                            sb.append(sizeText + ";"); // add
                            sb.append(colorText + ";"); // add color
                            sb.append(upc + ";"); // add upc code
                            sb.append(standardPrice + ";"); // add msrp standard price
                            sb.append(salesPrice + ";"); // add sales price
                            sb.append(finalUrl + ";"); // add current url
                            sb.append(link); // add full url for diagnostical purposes

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
               return;
            }
        } while(!bigOk); // end of big okay
    }

    public String getSalesPrice(WebDriver driver) {
        String salesPriceCss = "span.price-sales";
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
        return salesPrice;
    }

    public String getUpcCode(WebDriver driver) {
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
        return upc;
    }

    public String getMsrpPrice(WebDriver driver) {
        String standardPriceCss = "span.price-standard";
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
        return standardPrice;
    }

    public String getFinalUrl(WebDriver driver) {
        String finalUrl = driver.getCurrentUrl().replaceAll("http://www.carters.com/", "").replaceAll("null", "");
        try {
            finalUrl = finalUrl.substring(0, finalUrl.indexOf("/")).replaceAll("carters-", "");
        } catch (Exception ex) {
            System.out.println("FINAL URL: something went wrong, digressing");
        }
        return finalUrl;
    }
}
