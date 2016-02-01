package Carters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 1/29/16.
 */
public class Carters {
    private String link = "http://www.carters.com/carters-tops-baby-boy-shirts/888767323361.html";
    private String outputFile = "resultsCarters2.txt";
    private ArrayList<String> urlList = new ArrayList<String>();

    public static void main(String[] args) {
        String link = "http://www.carters.com";

        Carters app = new Carters();
        app.start();
    }

    public void start() {
        urlList = loadLinks();
        WebDriver driver = new FirefoxDriver();
        driver.get(link);

        // close the popup
        WebDriverWait popupClose = new WebDriverWait(driver, 10);
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".padiClose"))).click();
        popupClose.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".vnothanks"))).click();
        driver.manage().window().maximize();

        // load a link from the resource links
        for(int i=0;i<urlList.size();i++) {
            driver.get(urlList.get(i));

            // for each link do
            ProductPage page = new ProductPage(driver);
            page.collect();

            //ArrayList<String> list = page.getResults();
            //for (int k= 0;k<list.size();k++) {
            //    System.out.println(list.get(k));
            //    printItem(list.get(k));
            //}
        }
        driver.close();
    }

    public void printItem(String text) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(outputFile, true));
            writer.println(text);
        } catch (IOException ex) {
            System.out.println("error : printing text");
        } finally {
            writer.close();
        }
    }

    private ArrayList<String> loadLinks() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("Carters/categoryLinks");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ArrayList<String> list = new ArrayList<String>();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("#")) {
                    String[] tokens = line.split("html");
                    line = tokens[0] + "html";

                    if(!list.contains(line))
                        list.add(line.trim());
                }
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}