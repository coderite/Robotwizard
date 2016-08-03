package Burton;


import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by zenbox on 2/6/16.
 */
public class MainSingle {
    private String link = "http://www.burtonandburton.com/-CZZ---2016-CY1.asp?q=1";
    private String baseLink = "http://www.burtonandburton.com/-CZZ---2016-CY";
    private String postLink = ".asp?q=1";
    private WebDriver driver;

    public static void main(String[] args) {
        MainSingle app = new MainSingle();
        app.start();
    }

    public void start() {
        driver = new FirefoxDriver();

        // open the home page and login
        HomePage homepage = new HomePage(driver);
        homepage.login("remusparty", "remusparty");

        //ProductPage productPage = new ProductPage(driver);
        //productPage.setPageLink("http://www.burtonandburton.com/HUGGER-BROWN-BEAR-DZ98494-DYZ---2016-DX5.asp?q=1");
        //productPage.collect();


        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
/*

        driver.get(link);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // String nextPageCss = "input.listPagingImg";
        String nextPageCss = "div.listPaging:nth-child(6) > div:nth-child(1) > input:nth-child(8)";

        // ArrayList<String> list = new Helpers().loadLinks("Burton/missedLinks");
        for(int i=1;i<=592;i++) {
            String page = baseLink + i + postLink;
            //String page = list.get(i);
            System.out.println("Opening page: #" + i);

            Helpers helper = new Helpers();
            helper.setOutputFile("burtonProductLinksReal.txt");
            helper.printItem("Opening page: " + page);


            try {
                driver.get(page);
                if(i==1) { // only do this on the first page
                    String itemsPerPage = "#three";
                    boolean endItemsPerPage;
                    do {
                        try {
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(itemsPerPage))).click();
                            endItemsPerPage = true;
                        } catch (NoSuchElementException ex) {
                            endItemsPerPage = false;
                        }
                    } while(!endItemsPerPage);
                }
                getLinks(driver.getPageSource());

            } catch (Exception ex) {
                Helpers exceptionHelper = new Helpers();
                exceptionHelper.setOutputFile("BurtonSkipped.txt");
                exceptionHelper.printItem(page);
                try {
                    Thread.sleep(5000);
                } catch (Exception exh) {

                }
            }
        }

        System.out.println("done");
        driver.quit();
*/

    }

    public void getLinks(String source) {
        Document doc = Jsoup.parse(source);
        Elements gridBoxLinks = doc.select(".gridBox a");

        int count = 1;
        for(Element link : gridBoxLinks) {
            String url = "http://www.burtonandburton.com" + link.attr("href");
            url = url.replaceAll(".asp-DYZ", "-DYZ").replaceAll("store//", "");
            System.out.println("\t" + count++ + " " + url);

            Helpers helper = new Helpers();
            helper.setOutputFile("burtonProductLinksReal.txt");
            helper.printItem(url);
        }
    }


}
