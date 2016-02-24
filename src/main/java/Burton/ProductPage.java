package Burton;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Created by zenbox on 2/9/2016.
 */
public class ProductPage {
    private WebDriver driver;
    private String pageLink;

    private String cssTitle = "div.product-title";
    private String cssProductImage = "#productDetailForm > div.product-area > div.product-preview > a > img";
    private String cssDescription = "#productDetailForm > div.product-area > div.product-details > p";
    private String cssProductCode = "#productDetailForm > div.product-area > div.product-details > div.table.product-order-table > div:nth-child(1) > div.column.taRight > div";
    private String cssUpc = "#productDetailForm > div.product-area > div.product-details > div.table.product-order-table > div:nth-child(2) > div.column.taRight > div";
    private String cssTableQty = "#productDetailForm > div.product-area > div.product-details > div.table.product-detail > div.table-head > div:nth-child(1)";
    private String cssTablePrice = "#productDetailForm > div.product-area > div.product-details > div.table.product-detail > div.table-head > div:nth-child(2)";
    private String cssCategory = "#productDetailForm > div.product-area > div.prodCategory";

    private Product product = new Product();
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public void collect() {
        driver.get(pageLink);

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssTitle)));
            WebElement elementTitle = driver.findElement(By.cssSelector(cssTitle));
            WebElement elementDescription = driver.findElement(By.cssSelector(cssDescription));
            WebElement elementProductImage = driver.findElement(By.cssSelector(cssProductImage));

            String productCode = "N/A";
            WebElement elementProductCode;
            try {
                elementProductCode = driver.findElement(By.cssSelector(cssProductCode));
                productCode = elementProductCode.getText().trim();
            } catch (NoSuchElementException ex) {
                try {
                    elementProductCode = driver.findElement(By.cssSelector("div.table.product-order-table"));
                    String text = elementProductCode.getText();
                    text = text.trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
                    String[] tokens = text.split(":");
                    productCode = tokens[1].replaceAll("UPC", "").trim();
                } catch (ElementNotFoundException exs) {

                }
            }

            String upc = "N/A";
            WebElement elementUpc;
            try {
                elementUpc = driver.findElement(By.cssSelector(cssUpc));
                upc = elementUpc.getText().trim();
            } catch (NoSuchElementException ex) {
                try {
                    elementUpc = driver.findElement(By.cssSelector("div.table.product-order-table"));
                    String text = elementUpc.getText();
                    text = text.trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
                    String[] tokens = text.split(":");
                    upc = tokens[2].replaceAll("Quantity", "").trim().split(" ")[0].trim();
                } catch (ElementNotFoundException exs) {

                }
            }

            WebElement elementCategory = driver.findElement(By.cssSelector(cssCategory));

            String qty = "Qty";
            WebElement elementQty;
            try {
                elementQty = driver.findElement(By.cssSelector(cssTableQty));
                qty = elementQty.getText().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
            } catch (NoSuchElementException ex) {

            }

            String pricer = "N/A";
            WebElement elementPrice;
            try {
                elementPrice = driver.findElement(By.cssSelector(cssTablePrice));
                pricer = elementPrice.getText().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
            } catch (NoSuchElementException ex) {
                try {
                    elementQty = driver.findElement(By.cssSelector("div.table.product-detail > div.table-head"));
                    String text = elementQty.getText();
                    text = text.trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
                    String[] tokens = text.split("Qty");
                    pricer = tokens[1];
                } catch (NoSuchElementException exs) {

                }
            }

            map.put(qty, pricer);
            String cssTableRows = "div.table.product-detail > div.tableRow";
            List<WebElement> tableRows = driver.findElements(By.cssSelector(cssTableRows));
            int rowCount = 0;
            for (WebElement row : tableRows) {
                if (row.getText().trim().length() > 0 && rowCount != 0) {
                    List<WebElement> columns = row.findElements(By.cssSelector("div.column"));
                    if (columns.size() > 1) {
                        String quantity = columns.get(0).getText().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
                        String price = columns.get(1).getText().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
                        map.put(quantity, price);
                    } else {
                        map.put("", row.getText().trim().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " "));
                    }
                }
                ++rowCount;
            }

            String title;
            if(elementTitle != null)
                title = elementTitle.getText().trim();
            else
                title = "N/A";

            String description;
            if(elementDescription != null)
                description = elementDescription.getText().trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
            else
                description = "N/A";

            String category;
            if(elementCategory != null)
                category = elementCategory.getText().replaceAll("»", "").trim().replaceAll("\\p{Cc}", " ").replaceAll("\\s+", " ");
            else
                category = "N/A";

            String productImage;
            if(elementProductImage != null)
                productImage = elementProductImage.getAttribute("src").trim();
            else
                productImage = "N/A";


            product.setMap(map);
            product.setName(title);
            product.setDescription(description);
            product.setProductCode(productCode);
            product.setCategory(category);
            product.setProductImage(productImage);
            product.setUpc(upc);

            System.out.println("title: " + title);
            System.out.println("description: " + description);
            System.out.println("product# " + productCode);
            System.out.println("upc: " + upc);
            System.out.println("category: " + category);
            System.out.println("product image: " + productImage);

            for(Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " || " + entry.getValue());
            }

            System.out.println(product);
            System.out.println("--- ");
        } catch (ElementNotFoundException ex) {
            ex.printStackTrace();

        } catch (ElementNotVisibleException ex) {
            ex.printStackTrace();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public String getItem() {
        return product.getPrintItem();
    }
}

