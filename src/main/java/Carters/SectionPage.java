package Carters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zenbox on 2/1/2016.
 * USE THIS CLASS AS ENTRY POINT TO GET ALL LINKS FOR ALL SECTIONS AND CATEGORIES.
 * RUN THIS AND USE OUTPUT AS INPUTLINKS TO CARTERS.JAVA
 */
public class SectionPage {
    public static final String OUTPUTFILE = "carterKidGirlProductLinks.txt";
    private Sourcer sourcer = new Sourcer();
   static String[] links = { "http://www.carters.com/carters-kid-girl",};

    // static String[] links = {
            //"http://www.carters.com/carters-baby-girl",
            //"http://www.carters.com/carters-baby-boy",
            //"http://www.carters.com/carters-baby-neutral",
            //"http://www.carters.com/carters-toddler-girl",
            //"http://www.carters.com/carters-toddler-boy",
            //"http://www.carters.com/carters-kid-girl",
            //"http://www.carters.com/carters-kid-boy"};

    public static void main(String[] args) {
        SectionPage app = new SectionPage();

        for(String link : links) {
            app.start(link);
        }

    }

    public void start(String link) {
        try {
            Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get();
            Elements links = doc.select(".categorylisting ul.category-group:nth-of-type(3) li");
            for(Element l : links) {
                String categoryLink = l.select("a").attr("href");
                String category = l.attr("id");

                System.out.println("category: " + category);
                System.out.println("category link: " + categoryLink);

                CategoryPage page = new CategoryPage(sourcer);
                page.setCategory(category);
                page.setLink(categoryLink);
                page.collect();
            }
        } catch (IOException ex) {

        }
    }
}
