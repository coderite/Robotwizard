package Marinas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zenbox on 1/17/16.
 */
public class GetPages {
    String outputFile = "marinasFloridaEntries.txt";
    ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        GetPages app = new GetPages();
        app.start();

    }

    private ArrayList<String> loadLinks() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("Marinas/marinas");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ArrayList<String> list = new ArrayList<String>();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("#"))
                    list.add(line.trim());
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void start() {
        list = loadLinks();
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            Document doc = null;
            try {
                doc = Jsoup.connect(list.get(i)).get();
                Elements bodies = doc.select("#mainform > table > tbody > tr > td:nth-child(2)");
                Elements links = doc.select("a[href]");

                String web = null;
                for(Element e: links) {
                    String url = e.attr("abs:href");

                    if(url.contains("marina_out.php")) {
                        try {
                            Document redirectUrlDoc = Jsoup.connect(url).get();
                            web = redirectUrlDoc.baseUri();
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex);
                        }
                    }
                }


                String text = bodies.text().replaceAll("\\n", "").replaceAll("\\t", " ");
                text = text.replaceAll("Reservation Make Reservation Request", "").replaceAll("E - Mail Click to E-Mail", "").replaceAll("Format DMS DD ", "").replaceAll(" +", " ");
                if(text.startsWith("Click to Visit"))
                    text = text.replaceFirst("Click to Visit Mobile Site", "");
                if(text.contains("No reviews yet"))
                    text = text.replaceFirst("No reviews yet\\. Be the first", "");

                String title = text.split("Contact Information")[0];
                try {
                    if (title.trim().length() < 1)
                        title = "N/A";
                    if (title.contains("in the heart")) {
                        int cutoff = title.toLowerCase().indexOf("located");
                        title = text.substring(0, cutoff).trim();
                    }
                    if (title.endsWith(","))
                        title = title.trim().substring(0, title.length() - 1);
                    title = title.trim();
                } catch (StringIndexOutOfBoundsException ex) {

                }

                int phoneStart = text.indexOf("Phone");
                int faxStart = text.indexOf("Fax");
                int webStart = text.indexOf("Web");
                int latitudeStart = text.toLowerCase().indexOf("latitude");
                int longitudeStart = text.toLowerCase().indexOf("longitude");
                int streetAddressStart = text.toLowerCase().indexOf("street address");
                int mailingAddressStart = text.toLowerCase().indexOf("mailing address");
                int addressStart = text.toLowerCase().indexOf("address");

                String phone = "N/A";
                try {
                    if (phoneStart != -1) {
                        if (faxStart != -1)
                            phone = text.substring(phoneStart, faxStart).trim();
                        else if (webStart != -1)
                            phone = text.substring(phoneStart, webStart).trim();
                        else if (latitudeStart != -1)
                            phone = text.substring(phoneStart, latitudeStart).trim();
                        else if (longitudeStart != -1)
                            phone = text.substring(phoneStart, longitudeStart).trim();
                        else if (streetAddressStart != -1)
                            phone = text.substring(phoneStart, streetAddressStart).trim();
                        else if (mailingAddressStart != -1)
                            phone = text.substring(phoneStart, mailingAddressStart).trim();
                        else
                            phone = text.substring(phoneStart).trim();

                        if (phone.toLowerCase().startsWith("phone"))
                            phone = phone.substring(6);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                String fax = "N/A";
                try {
                    if (faxStart != -1) {
                        if (webStart != -1)
                            fax = text.substring(faxStart, webStart).trim();
                        else if (latitudeStart != -1)
                            fax = text.substring(faxStart, latitudeStart).trim();
                        else if (longitudeStart != -1)
                            fax = text.substring(faxStart, longitudeStart).trim();
                        else if (streetAddressStart != -1)
                            fax = text.substring(faxStart, streetAddressStart).trim();
                        else if (mailingAddressStart != -1)
                            fax = text.substring(faxStart, mailingAddressStart).trim();
                        else
                            fax = text.substring(faxStart).trim();

                        if (fax.toLowerCase().startsWith("fax"))
                            fax = fax.substring(4);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                /*
                String web = "N/A";
                try {
                    if (webStart != -1) {
                        if (latitudeStart != -1)
                            web = text.substring(webStart, latitudeStart).trim();
                        else if (longitudeStart != -1)
                            web = text.substring(webStart, longitudeStart).trim();
                        else if (streetAddressStart != -1)
                            web = text.substring(webStart, streetAddressStart).trim();
                        else if (mailingAddressStart != -1)
                            web = text.substring(webStart, mailingAddressStart).trim();
                        else
                            web = text.substring(webStart).trim();

                        if (web.toLowerCase().startsWith("web"))
                            web = web.substring(4);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }
                */


                String latitude = "N/A";
                try {
                    if (latitudeStart != -1) {
                        if (longitudeStart != -1)
                            latitude = text.substring(latitudeStart, longitudeStart).trim();
                        else if (streetAddressStart != -1)
                            latitude = text.substring(latitudeStart, streetAddressStart).trim();
                        else if (mailingAddressStart != -1)
                            latitude = text.substring(latitudeStart, mailingAddressStart).trim();
                        else if (addressStart != 1)
                            latitude = text.substring(latitudeStart, addressStart).trim();
                        else
                            latitude = text.substring(latitudeStart).trim();

                        if (latitude.toLowerCase().startsWith("latitude"))
                            latitude = latitude.substring(9);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                String longitude = "N/A";
                try {
                    if (longitudeStart != -1) {
                        if (streetAddressStart != -1)
                            longitude = text.substring(longitudeStart, streetAddressStart).trim();
                        else if (mailingAddressStart != -1)
                            longitude = text.substring(longitudeStart, mailingAddressStart).trim();
                        else if (addressStart != 1)
                            longitude = text.substring(longitudeStart, addressStart).trim();
                        else
                            longitude = text.substring(longitudeStart).trim();

                        if (longitude.toLowerCase().startsWith("longitude"))
                            longitude = longitude.substring(9);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                String streetAddress = "N/A";
                try {
                    if (streetAddressStart != -1) {
                        if (mailingAddressStart != -1)
                            streetAddress = text.substring(streetAddressStart, mailingAddressStart).trim();
                        else if (addressStart != -1)
                            streetAddress = text.substring(streetAddressStart, addressStart).trim();
                        else
                            streetAddress = text.substring(streetAddressStart).trim();

                        if (streetAddress.toLowerCase().startsWith("street address"))
                            streetAddress = streetAddress.substring(15);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                String mailingAddress = "N/A";
                try {
                    if (mailingAddressStart != -1) {
                        mailingAddress = text.substring(mailingAddressStart).trim();

                        if (mailingAddress.toLowerCase().startsWith("mailing address"))
                            mailingAddress = mailingAddress.substring(16);
                    }
                } catch (StringIndexOutOfBoundsException ex) {

                }

                String address = "N/A";
                if(addressStart != -1 && streetAddress.equals("N/A") && mailingAddress.equals("N/A")) {
                    address = text.substring(addressStart).trim();

                    if(address.toLowerCase().startsWith("address"))
                        address = address.substring(7);
                }

                if(!address.equals("N/A"))
                    streetAddress = address;


                System.out.println("Marina: " + title);
                System.out.println("Phone: " + phone);
                System.out.println("Fax: " + fax);
                System.out.println("Web: " + web);
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
                System.out.println("Street Address: " + streetAddress);
                System.out.println("Mailing Address: " + mailingAddress);

                String line = title + "\t" + phone + "\t" + fax + "\t" + web + "\t" + latitude + "\t" + longitude + "\t" + streetAddress + "\t" + mailingAddress + "\t" + list.get(i);
                printIt(line);

                        Thread.sleep(1000);
            } catch (IOException ex) {

            } catch (InterruptedException ex) {

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
}
