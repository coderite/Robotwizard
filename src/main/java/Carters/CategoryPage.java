package Carters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zenbox on 1/30/16.
 */
public class CategoryPage {
    String link2 = "http://www.carters.com/carters-baby-boy-pajamas?cgid=carters-baby-boy-pajamas&startRow=0&sz=all";
    String link = "http://www.carters.com/carters-baby-boy-bottoms?&startRow=0&sz=all";

    public CategoryPage() {

    }

    public static void main(String[] args) {
        CategoryPage app = new CategoryPage();
        app.collect();
    }

    public void collect() {
        try {
            Document doc = Jsoup.connect(link).get();
            Elements products = doc.select(".product-name a");

            int count = 1;
            for(Element product : products) {
                System.out.println(product.attr("href"));
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
