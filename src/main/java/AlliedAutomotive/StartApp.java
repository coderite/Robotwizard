package AlliedAutomotive;

import Carters.Helpers;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zenbox on 2/28/2016.
 */
public class StartApp {
    private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36";
    private static String link = "http://www.alliedautomotive.com.au/";
    private String jLink = "http://www.alliedautomotive.com.au/part-finder/shafts-axles-wheels/";
    private WebDriver driver;
    private Helpers helper;

    public static void main(String[] args) {
     //   try {
          //  Document doc = Jsoup.connect(link).get();
            //System.out.println(doc.body());


        StartApp app = new StartApp();
        app.run();
    }

    public void loopThroughYear(String make, String model) {
        Select selector = new Select(driver.findElement(By.cssSelector("#year_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#year_field")));
        }
        System.out.println("size of selector: " + selector.getOptions().size());

        List<String> list = new ArrayList<>();
        boolean seenAllModels = false;
        boolean containsAll = false;
        for(WebElement el: selector.getOptions()) {
            if(el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if(containsAll) {
                if (seenAllModels)
                    list.add(e.getText());

                if (e.getText().contains("All"))
                    seenAllModels = true;
            } else {
                list.add(e.getText());
            }
        }

        System.out.println("\t\tnumber of year: " + list.size());
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String year = iterator.next();
            System.out.println("\t\tyear " + year);
            boolean ok;
            do {
                try {
                    selector.selectByVisibleText(year);
                    ok = true;
                } catch (StaleElementReferenceException ex) {
                    System.out.println("caugh it ");
                    selector = getSelector("#year_field");
                    ok = false;
                }
            } while(!ok);

            /*
            LOOP THROUGH CHASSIS
             */

            loopThroughSeriesChassis(make, model, year);
        }
        /*
        try {
            Thread.sleep(500);
        } catch (Exception x) {

        }
        */
    }

    public void loopThroughSeriesChassis(String make, String model, String year) {
        Select selector = new Select(driver.findElement(By.cssSelector("#series_chassis_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#series_chassis_field")));
        }
        System.out.println("size of selector: " + selector.getOptions().size());

        List<String> list = new ArrayList<>();
        boolean containsAll = false;
        for(WebElement el: selector.getOptions()) {
            if(el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if(e.getText().contains("./.") || e.getText().trim().length() < 1 || selector.getOptions().size() > 2 && e.getText().trim().equals("All"))
                continue;
            else
                list.add(e.getText());
        }

        if(!containsAll)
            list = Lists.reverse(list);

        System.out.println("\t\t\tnumber of series/chassis: " + list.size());
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String seriesChassis = iterator.next();
            System.out.println("\t\t\tseries/chassis\t" + seriesChassis);
            boolean ok;
            do {
                try {
                    selector.selectByVisibleText(seriesChassis);
                    ok = true;
                } catch (StaleElementReferenceException ex) {
                    System.out.println("caugh it ");
                    selector = getSelector("#series_chassis_field");
                    ok = false;
                }
            } while(!ok);

            /*
            LOOP THROUGH ENGINE
             */
            try {
                Thread.sleep(500);
            } catch (Exception x) {

            }
            loopThroughEngine(make, model, year, seriesChassis);
        }
        /*
        try {
            Thread.sleep(500);
        } catch (Exception x) {

        }
        */
    }

    public void loopThroughEngine(String make, String model, String year, String seriesChassis) {
        Select selector = new Select(driver.findElement(By.cssSelector("#engine_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#engine_field")));
        }
        System.out.println("size of selector: " + selector.getOptions().size());

        List<String> list = new ArrayList<>();
        boolean seenAllModels = false;
        boolean containsAll = false;
        for(WebElement el: selector.getOptions()) {
            if(el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if(containsAll) {
                if (seenAllModels)
                    list.add(e.getText());

                if (e.getText().contains("All"))
                    seenAllModels = true;
            } else {
                if(e.getText().trim().length() > 1)
                    list.add(e.getText());
            }
        }

        if(!containsAll)
            list = Lists.reverse(list);

        System.out.println("\t\t\t\tnumber of engines: " + list.size());
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String engine = iterator.next();
            System.out.println("\t\t\t\tengine\t" + engine);
            boolean ok;
            do {
                try {
                    selector.selectByVisibleText(engine);
                    ok = true;
                } catch (StaleElementReferenceException ex) {
                    selector = new Select(driver.findElement(By.cssSelector("#engine_field")));
                    ok = false;
                }
            } while(!ok);

            /*
            LOOP THROUGH VEHICLE DETAILS
             */
            loopThroughVehicleDetails(make, model, year, seriesChassis, engine);
        }
        /*
        try {
            Thread.sleep(500);
        } catch (Exception x) {

        }
        */
    }

    public void loopThroughVehicleDetails(String make, String model, String year, String seriesChassis, String engine) {
        Select selector = new Select(driver.findElement(By.cssSelector("#details_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#details_field")));
        }
        System.out.println("size of selector: " + selector.getOptions().size());

        List<String> list = new ArrayList<>();
        boolean seenAllModels = false;
        boolean containsAll = false;
        for(WebElement el: selector.getOptions()) {
            if(el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if(e.getText().trim().length() > 1)
                list.add(e.getText());

        }

        if(!containsAll)
            list = Lists.reverse(list);

        System.out.println("\t\t\t\tnumber of vehicle details: " + list.size());
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String vehicleDetails = iterator.next();
            System.out.println("\t\t\t\tvehicle details\t" + vehicleDetails);
            // safeSelect(selector, model);
            boolean ok;
            do {
                try {
                    selector.selectByVisibleText(vehicleDetails);
                    ok = true;
                } catch (StaleElementReferenceException ex) {
                    System.out.println("caugh it ");
                    selector = getSelector("#details_field");
                    ok = false;
                }
            } while(!ok);

            //WebElement checkout = driver.findElement(By.id("sb-overlay"));
            String originalHandle = driver.getWindowHandle();
            driver.switchTo().activeElement();
            System.out.println("on active element");
            WebElement close = driver.findElement(By.id("sb-nav-close"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", close);

            driver.switchTo().window(originalHandle);

            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }


            driver.findElement(By.cssSelector("div.part-category-wrapper:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(2)")).click();
            driver.findElement(By.cssSelector("div.sub-category-title:nth-child(2) > a:nth-child(1)")).click();

            List<WebElement> listOfParts = driver.findElements(By.cssSelector(".part-list .part-product"));
            System.out.println("PARTS SEEN: " + listOfParts.size());
            getParts(listOfParts, make, model, year, seriesChassis, engine, vehicleDetails);


            //
        }
        /*
        try {
            Thread.sleep(500);
        } catch (Exception x) {

        }
        */
    }
    public void getParts(List<WebElement> elements, String make, String model, String year, String serriesChassis, String engine, String vehicleDetails) {
        String sku = "N/A";
        String price = "N/A";
        String description = "N/A";
        String supplier = "N/A";

        System.out.println("PARTS: lines" + elements.size());
        if(elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {

                WebElement lineElement = elements.get(i);
                for(int j=0;j<3;j++) {
                    if (j == 0) {
                        WebElement firstLine = lineElement.findElement(By.cssSelector("h2"));
                        String thisLine = firstLine.getText().replaceAll("\\p{Cc}", "");
                        String[] tokens = thisLine.split("-");
                        sku = tokens[0];
                        System.out.println("SKU: " + sku);

                        String[] priceTokens = thisLine.split("\\$");
                        price = priceTokens[1];
                        System.out.println("price: " + price);
                    } else if (j == 1) {
                        WebElement detLine = lineElement.findElement(By.cssSelector("h3"));
                        String[] descTokens = detLine.getText().split("\n");
                        description = descTokens[0].replaceAll("\\p{Cc}", "").trim();
                        System.out.println("DESCRIPTION: " + description);
                    } else if (j == 2) {
                        WebElement supLine = lineElement.findElement(By.cssSelector("h3"));
                        String[] supTokens = supLine.getText().split("\n");
                        if (supTokens.length > 1)
                            supplier = supTokens[1].replaceAll("\\p{Cc}", "").trim();
                        System.out.println("SUPPLIER: " + supplier);
                    }
                }
                Part part = new Part();
                part.setSKU(sku);
                part.setPrice(price);
                part.setPartDescription(description);
                part.setSupplier(supplier);

                part.setMake(make);
                part.setModel(model);
                part.setYear(year);
                part.setSeriesChassis(serriesChassis);
                part.setEngine(engine);
                part.setVehicleDetails(vehicleDetails);

                helper.printItem(part.getPrintableString());



            }
        } else {
            Part part = new Part();
            part.setSKU(sku);
            part.setPrice(price);
            part.setPartDescription(description);
            part.setSupplier(supplier);

            part.setMake(make);
            part.setModel(model);
            part.setYear(year);
            part.setSeriesChassis(serriesChassis);
            part.setEngine(engine);
            part.setVehicleDetails(vehicleDetails);

            helper.printItem(part.getPrintableString());
        }
    }


    public Select getSelector(String css) {

        Select selector = new Select(driver.findElement(By.cssSelector(css)));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector(css)));
        }
        return selector;
    }

    public void safeSelect(Select selector, String model) {

        selector.selectByVisibleText(model);
    }

    public void clickByLocator( final String locator ) {
        final long startTime = System.currentTimeMillis();
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
        Wait<WebDriver> wait = new FluentWait<WebDriver>( driver )
                .withTimeout(90000, TimeUnit.MILLISECONDS)
                .pollingEvery(5500, TimeUnit.MILLISECONDS);
        //.ignoring( StaleElementReferenceException.class );
        wait.until( new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply( WebDriver webDriver ) {
                try {
                    webDriver.findElement(By.cssSelector(locator)).click();
                    return true;
                } catch ( StaleElementReferenceException e ) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        } );
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS );
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Finished click after waiting for " + totalTime + " milliseconds.");
    }

    public void run() {
        helper = new Helpers();
        helper.setOutputFile("Results/AlliedAutomotive/out.txt");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/37.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");

        String proxi = "120.198.231.24:85";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxi).setFtpProxy(proxi).setSslProxy(proxi).setSocksProxy(proxi);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        driver = new FirefoxDriver(cap);
        //driver.manage().deleteAllCookies();
        driver.get(link);

        driver.findElement(By.cssSelector("li.part-finder-section:nth-child(1) > a:nth-child(1)")).click();

        /*
        LOOP THROUGH ALL THE CAR MAKES
         */
        Select makeSelector = new Select(driver.findElement(By.cssSelector("#make_field")));
        List<String> makeList = new ArrayList<>();
        boolean seenAllMakes = false;
        for(WebElement e: makeSelector.getOptions()) {
            //if (seenAllMakes) // uncomment this to return the combo functionality
            //    makeList.add(e.getText());

            if(!e.getText().equals("TOYOTA"))
                continue;
            else
                makeList.add(e.getText());

            if (e.getText().contains("All Makes"))
                seenAllMakes = true;
        }

        Iterator<String> makeIterator = makeList.iterator();
        while(makeIterator.hasNext()) {
            String make = makeIterator.next();
            System.out.print(make+ "\t");
            makeSelector.selectByVisibleText(make);

/*
            try {
                Thread.sleep(500);
            } catch (Exception x) {

            }
            */
        /*
        LOOP THROUGH THE MODELS
         */
            Select modelSelector = new Select(driver.findElement(By.cssSelector("#model_field")));
            while(modelSelector.getOptions().size() < 1) {
                try {
                    Thread.sleep(500);
                } catch (Exception ex) {

                }
                modelSelector = new Select(driver.findElement(By.cssSelector("#model_field")));
            }
            System.out.println("size of selector: " + modelSelector.getOptions().size());

            List<String> modelList = new ArrayList<>();
            boolean seenAllModels = false;
            boolean containsAll = false;
            for(WebElement el: modelSelector.getOptions()) {
                if(el.getText().contains("All Models")) {
                    containsAll = true;
                    System.out.println("selection contains ALL");
                    break;
                }
            }
            for (WebElement e : modelSelector.getOptions()) {
                if(containsAll) {
                    if (seenAllModels)
                        modelList.add(e.getText());

                    if (e.getText().contains("All Models"))
                        seenAllModels = true;
                } else {
                    modelList.add(e.getText());
                }
            }

            // if no --ALL- is seen we should reverse the list to avoid non selection of the first items
            if(!containsAll)
               modelList = Lists.reverse(modelList);

            Helpers help = new Helpers();
            List<String> l = help.loadLinks("Allied/seenModels");
            System.out.println("number of models: " + modelList.size());
            Iterator<String> modelIterator = modelList.iterator();
            while(modelIterator.hasNext()) {
                String model = modelIterator.next();
                if(listContains(model, l))
                    continue;

                System.out.println("\tmodel\t" + model);
                boolean ok;
                do {
                    try {
                        modelSelector.selectByVisibleText(model);
                        ok = true;
                    } catch (StaleElementReferenceException ex) {
                        System.out.println("caugh it ");
                        modelSelector = getSelector("#model_field");
                        ok = false;
                    }
                } while(!ok);

                /*
                LOOP THROUGH YEAR
                 */
                loopThroughYear(make, model);
            }
            /*
            try {
                Thread.sleep(500);
            } catch (Exception x) {

            }
            */
        }
    }

    public boolean listContains(String entry, List<String> list){
        for(int i=0;i<list.size();i++) {
            if(list.get(i).equals(entry))
                return true;
        }
        return false;
    }

}
