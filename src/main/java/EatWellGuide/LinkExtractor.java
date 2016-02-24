package EatWellGuide;

import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * Created by zenbox on 2/16/2016.
 */
public class LinkExtractor {
    ArrayList<String> zipCodes;
    WebDriver driver;

    public static void main(String[] args) {
        LinkExtractor app = new LinkExtractor();
        app.start();
    }

    public void start() {
        zipCodes = new Helpers().loadLinks("EatWellGuide/zipCodes");
        System.out.println("number of zip codes: " + zipCodes.size());
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/EatWellGuide/establishmentLinks.txt");

        String link;
        String zip;
        int count = 0;
        for(int i=0;i<zipCodes.size();i++) {
            zip = zipCodes.get(i);
            link = "http://www.eatwellguide.org/listings?where=" + zip + "&q=*&radius=1";

            System.out.println("todo: " + (zipCodes.size() - (i+1)));
            try {
                Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").get();
                Element totals = doc.select("#content > div > div > div > div.search-pagination.search-pagination--bottom > nav > div > span.search-results__total-number").first();
                int pageResults = Integer.valueOf(totals.text());
                count += pageResults;
                System.out.println("Found a valid zip (" + zip + ") with " + totals.text() + " entries");


                // find the number of pages and loop through them
                int numberOfPages = (pageResults / 24) + 1;
                System.out.println("There are " + numberOfPages + " pages available");
                for (int j = 1; j <= numberOfPages; j++) {
                    if (j != 1) { // pagination
                        link = link + "page=" + j + "&page_size=24";
                        doc = Jsoup.connect(link).get();
                        System.out.println("page " + j + " loaded: " + link);
                    }
                    // get a list of results for this page
                    Elements entries = doc.select("div.search-results__container a");
                    // open each link
                    int entryCount = 1;
                    for (Element entry : entries) {
                        String itemLink = entry.attr("abs:href");
                        if (itemLink.contains("listings") && !itemLink.contains("page")) {
                            // loop through each page
                            System.out.println(entryCount++ + " " + itemLink);
                            helper.printItem(itemLink);


                        }
                    }
                }
            } catch (NullPointerException ex) {

            } catch (Exception ex) {
                ex.printStackTrace();
                Helpers errorWriter = new Helpers();
                errorWriter.setOutputFile("Results/EatWellGuide/missedLinks.txt");
                errorWriter.printItem(link);
            }
        }
        System.out.println("total entries " + count);



    }
}
