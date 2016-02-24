package EatWellGuide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 2/16/2016.
 */
public class EstablishmentPage{
    private Document doc;
    private Record record;

    public EstablishmentPage() {

    }

    public void scrape() {
        getFields(doc);
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    private void getFields(Document doc) {
        this.record = new Record();

        String address;
        Element addressElement;
        try {
            addressElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--address").first();
            address = addressElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setAddress(address);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: address " + ex.getMessage());
            record.setAddress("N/A");
        }

        String phone;
        Element phoneElement;
        try {
            phoneElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--phone").first();
            phone = phoneElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setPhone(phone);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: phone " + ex.getMessage());
            record.setPhone("N/A");
        }

        String website;
        Element websiteElement;
        try {
            websiteElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--website").first();
            website = websiteElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setWebsite(website);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: website link " + ex.getMessage());
            record.setWebsite("N/A");
        }


        String facebook;
        Element facebookElement;
        try {
            facebookElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--facebook").first();
            facebook = facebookElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setFacebook(facebook);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: facebook " + ex.getMessage());
            record.setFacebook("N/A");
        }

        String twitter;
        Element twitterElement;
        try {
            twitterElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--twitter").first();
            twitter = twitterElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setTwitter(twitter);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: twitter " + ex.getMessage());
            record.setTwitter("N/A");
        }

        String email;
        Element emailElement;
        try {
            emailElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__sidebar > div > div > ul > li.sidebar__contact-item.sidebar__contact-item--email").first();
            email = emailElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setEmail(email);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: email " + ex.getMessage());
            record.setEmail("N/A");
        }

        String name;
        Element nameElement;
        try {
            nameElement = doc.select("h1.single-listing__name").first();
            name = nameElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setName(name);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: email " + ex.getMessage());
            record.setName("N/A");
        }



        String subtitle;
        Element subtitleElement;
        try {
            subtitleElement = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__main > div > header > h2").first();
            subtitle = subtitleElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setSubtitle(subtitle);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: email " + ex.getMessage());
            record.setSubtitle("N/A");
        }

        List<String> category = new ArrayList<>();
        Elements categoryElements;
        try {
            categoryElements = doc.select("div.single-listing__categories a");
            for(Element e : categoryElements) {
                String cat = e.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
                category.add(cat);
            }
            record.setCategory(category);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: categories " + ex.getMessage());
            record.setCategory(null);
        }

        String description;
        Element descriptionElement;
        try {
            descriptionElement = doc.select("div.single-listing__description").first();
            description = descriptionElement.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
            record.setDescription(description);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: description " + ex.getMessage());
            record.setDescription("N/A");
        }

        List<String> hours = new ArrayList<>();
        Elements hoursElements;
        try {
            hoursElements = doc.select("div.single-listing__hours-container.slideToggle div.single-listing__hours-wrapper");
            int count=1;
            for(Element e : hoursElements) {
                String hour = e.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
                System.out.println(hour);

                if(count++ < hoursElements.size())
                    hour = hour + ", ";

                if(hour.trim().length() > 0)
                    hours.add(hour);
            }

            if(hours.size() < 1) {
                hoursElements = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__main > div > div.single-listing__details-and-hours > div > div");
                String h = hoursElements.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
                hours.add(h);
            }
            record.setHours(hours);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: hours " + ex.getMessage());
            record.setHours(null);
        }

        List<String> relationships = new ArrayList<>();
        Elements relationshipElements;
        try {
            relationshipElements = doc.select("#content > div > div.single-listing__main > div > div.single-listing__relationships.slideToggle > div > div");
            int count=1;
            for(Element e : relationshipElements) {
                String re = e.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");
                System.out.println(count + re);

                if(count++ < relationshipElements.size())
                    re = re + ", ";

                relationships.add(re);
            }
            record.setRelationship(relationships);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: relationships " + ex.getMessage());
            record.setRelationship(null);
        }

        List<String> details = new ArrayList<>();
        Elements detailsElements;
        try {
            detailsElements = doc.select("#content > div.single-listing__faux-column-bg > div.single-listing__main > div > div.single-listing__details-and-hours > div.single-listing__details-container.slideToggle > div > div");
            int count=1;
            for(Element e : detailsElements) {
                String de = e.text().trim().replaceAll("\\p{Cc}", "").replaceAll("\\s+", " ");

                if(count++ < detailsElements.size())
                    de = de + ", ";

                System.out.println(de);
                details.add(de);
            }
            record.setDetails(details);
        } catch (Exception ex) {
            System.out.println("CAUGHT ERROR: details " + ex.getMessage());
            record.setDetails(null);
        }
    }

    public Record getRecord() {
        return record;
    }
}
