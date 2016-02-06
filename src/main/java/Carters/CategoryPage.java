package Carters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zenbox on 1/30/16.
 */
public class CategoryPage {
    private String postfix = "&startRow=0&sz=all";
    private String link = "http://www.carters.com/carters-baby-girl-sets&startRow=0&sz=all";
    private String category;
    private Sourcer sourcer;
    private String outputFile;
    // String link2 = "http://www.carters.com/carters-baby-boy-pajamas?cgid=carters-baby-boy-pajamas&startRow=0&sz=all";
    // String link = "http://www.carters.com/carters-baby-boy-bottoms?&startRow=0&sz=all";

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
                System.out.println(count++ + " " + pageLink);

                previous = pageLink;

                Helpers helper = new Helpers();
                helper.setOutputFile("carterFullProductLinks.txt");
                if(!pageLink.contains("recs.richrelevance")) {
                    helper.printItem(pageLink);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
