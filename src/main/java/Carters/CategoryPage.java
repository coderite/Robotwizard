package Carters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zenbox on 1/30/16.
 */
public class CategoryPage {
    private String postfix = "&startRow=0&sz=all";
    private String link = "http://www.carters.com/carters-baby-girl-sets&startRow=0&sz=all";
    private String category;
    private Sourcer sourcer;
    private String outputFile;
    private Set<String> links = new HashSet<>();

    public CategoryPage(Sourcer sourcer) {
        this.sourcer = sourcer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLink(String link) {
        String[] tokens = link.split("\\?");
        this.link = tokens[0] + "?cgid=" + category + postfix;
    }

    public void collect() {
        try {
            System.out.println("page: " + link);
            // Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get();
            sourcer.setLink(link);
            Document doc = Jsoup.parse(sourcer.getSource());

            Elements products = doc.select(".product-name a");
            // Elements products = doc.select("div.search-result-content a");

            int count = 1;
            String previous = "";
            for(Element product : products) {
                String pageLink = product.attr("href");
                pageLink = makePageLinkClean(pageLink);
                System.out.println(count++ + " " + pageLink);

                previous = pageLink;

                if(!pageLink.contains("recs.richrelevance")) {
                    links.add(pageLink);
                }
            }

            Helpers helper = new Helpers();
            LocalDateTime timePoint = LocalDateTime.now();
            String time = timePoint.getMonth() + "" + timePoint.getDayOfMonth();
            helper.setOutputFile("Results/Carter/carterFullProductLinks_" + time + ".txt");

            // Java 8 mag
            links.forEach(link -> helper.printItem(link));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String makePageLinkClean(String link) {
        return link.split("\\?")[0];
    }


    public List<String> getList() {
        return null;
    }
}
