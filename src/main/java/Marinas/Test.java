package Marinas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zenbox on 1/17/16.
 */
public class Test {
    String outputFile = "marinasFL.txt";
    ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        Test app = new Test();
        app.start();

    }

    public void start() {

        Page page = new Page();
        page.setBase("http://marinas.com/browse/marina/US/");
        page.setState("FL");

        Document doc = null;
        try {
            doc = Jsoup.connect(page.toString()).get();
        } catch(IOException ex) {

        }

        while(!isFinished(doc)) {
            Elements es = doc.select("a[href]");

            for(Element e: es) {
                String link = e.attr("href");

                if(link.contains("http://marinas.com/view/marina")) {
                    System.out.println(link);
                    printIt(link);
                }

            }


            System.out.println(page);
            page.nextPage();
            try {
                Thread.sleep(2000);
                doc = Jsoup.connect(page.toString()).get();
            } catch(IOException ex) {

            } catch(InterruptedException ex) {

            }
        }




    }

    public void printIt(String text) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(outputFile, true));
            writer.println(text);
        } catch(FileNotFoundException ex) {

        } finally {
            if(writer != null)
                writer.close();
        }
    }

    public boolean isFinished(Document doc) {
        Elements es = doc.select(".graybox tbody td[align=center]");
        String text = es.text();

        if(text.length() < 1)
            return false;
        return true;
    }
}
