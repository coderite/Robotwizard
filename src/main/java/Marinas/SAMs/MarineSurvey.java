package Marinas.SAMs;

import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by zenbox on 4/9/2016.
 */
public class MarineSurvey {
    private WebDriver driver;
    private String link;

    public MarineSurvey(WebDriver driver) {
        this.driver = driver;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void start(){
        driver.get(link);
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {

        }
        Document doc = Jsoup.parse(correctHtml(driver.getPageSource()));
        Elements entries = doc.select("#page table tbody tr p");
        System.out.println("there are " + entries.size() + " entries");
        Helpers helper = new Helpers();
        String state = link.replaceAll("\\.html", "");
        state = state.replaceAll(".*?/", "");
        helper.setOutputFile("Results/Marinas/SAMS-" + state + ".txt");

        int count = 1;
        for(Element entry : entries) {
            String text = entry.text().replaceAll("This e-mail address is being protected from spambots. You need JavaScript enabled to view it", "");
            String name = text.split("-")[0];
            String specialty = getSpecialty(text);
            String website = getWebsite(entry);
            String company = getCompany(entry);
            String address = getAddress(entry);
            String surveyorType = getSurveyorType(entry);
            String email = getEmail(text);
            System.out.println(count + " " + name + " - " + surveyorType + " - " + email + " - " + specialty + " - " + company + " - " + website + " - " + address);
            helper.printItem(state + "\t" + name + "\t" + surveyorType + "\t" + email + "\t" + specialty + "\t" + company + "\t" + website + "\t" + address);


            count++;
        }
    }

    public String getWebsite(Element element) {
        Elements hrefs = element.select("a");

        for (Element href : hrefs) {
            if (href.attr("href").contains("http:")) {
                try {
                    return href.attr("href");
                } catch (Exception ex) {
                    return "N/A";
                }
            }
        }
        return "N/A";
    }

    public String getSpecialty(String text) {
        try {
            return text.split("Specialty:")[1];
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public String correctHtml(String html) {
        html = html.replaceAll("<p> </p>", "<p>");
        html = html.replaceAll("<p>\n<p>", "<p>");
        //html = html.replaceAll("<p><a href.+</a>", "<p>");
        System.out.println(html);
        return html;
    }

    public String getCompany(Element element) {
        String text = element.html();
        String[] tokens = text.split("<br />");
        String result = "N/A";
        if(tokens.length > 1)
            result = tokens[1];

        if(result.contains(">"))
             result = result.substring(result.indexOf(">")+1, result.length()).replaceAll("<.*?>", "").replaceAll("amp;", "");
        return result;
    }

    public String getAddress(Element element) {
        String text = element.html().replaceAll("\\p{Cc}", "");
        text = text.replaceFirst(".*?<br />", "<br />");
        text = text.replaceFirst("<script.*", "");
        text = text.replaceAll("<a.*?>", "");
        text = text.replaceAll("<br />", "");
        text = text.replaceAll("<.*?>", "");
        System.out.println(text);
        return text;
    }

    public String getEmail(String entry) {
        String[] tokens = entry.split(" ");
        String email = "N/A";
        for(String token : tokens) {
            if(token.contains("@"))
                email = token;
        }
        return email;
    }

    public String getSurveyorType(Element entry) {
        Element link = entry.select("a").first();
        String type = "N/A";
        try {
            type = link.attr("href");
        } catch (NullPointerException ex) {

        }

        if(type.contains("ams.html"))
            return "Accredited Marine Surveyor";
        else if(type.contains("sa.html"))
            return "Surveyor Associate";
        else
            return "N/A";
    }


}
