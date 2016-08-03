package AlliedAutomotive;

import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zenbox on 3/1/2016.
 */
public class PartFinderPage {
    private WebDriver driver;

    public PartFinderPage(WebDriver driver) {
        this.driver = driver;
    }

    public enum Types {
        MAKE, MODEL, YEAR, SERIESCHASSIS, ENGINE, VEHICLEDETAILS;
    }

    public void setValue(String value, Types type) {
        String css = "";
        switch(type) {
            case MAKE: {
                css = "#make_field";
                break;
            }
            case MODEL: {
                css = "#model_field";
                break;
            }
            case YEAR: {
                css = "#year_field";
                break;
            }
            case SERIESCHASSIS: {
                css = "#series_chassis_field";
                break;
            }
            case ENGINE: {
                css = "#engine_field";
                break;
            }
            case VEHICLEDETAILS: {
                css = "#details_field";
                break;
            }
        }

        boolean selectorSeen;
        do {
            try {
                Select selector = new Select(driver.findElement(By.cssSelector(css)));
                selector.selectByVisibleText(value);
                selectorSeen = true;
            } catch (StaleElementReferenceException ex) {
                selectorSeen = false;
            }
        } while(!selectorSeen);
    }

    public List<String> getModels () {
        Select modelSelector = new Select(driver.findElement(By.cssSelector("#model_field")));
        while(modelSelector.getOptions().size() < 1) {
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }
            modelSelector = new Select(driver.findElement(By.cssSelector("#model_field")));
        }

        List<String> modelList = new ArrayList<>();
        boolean seenAllModels = false;
        boolean containsAll = false;
        for(WebElement el: modelSelector.getOptions()) {
            if(el.getText().contains("All Models")) {
                containsAll = true;
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

        if (!containsAll)
            modelList = Lists.reverse(modelList);

        return modelList;
    }

    public List<String> getYears() {
        Select selector = new Select(driver.findElement(By.cssSelector("#year_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#year_field")));
        }

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
        return list;
    }

    public List<String> getSeriesChassis() {
        Select selector = new Select(driver.findElement(By.cssSelector("#series_chassis_field")));
        while(selector.getOptions().size() < 1) {
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#series_chassis_field")));
        }
        System.out.println("size of series: " + selector.getOptions().size());

        List<String> list = new ArrayList<>();
        for (WebElement e : selector.getOptions()) {
            if(e.getText().contains("./.") || e.getText().trim().length() < 1 || selector.getOptions().size() > 3 && e.getText().trim().equals("All"))
                continue;
            else {
                if(e.getText().trim().length() > 1)
                    list.add(e.getText());
            }
        }
        return list;
    }

    public List<String> getEngines() {
        Select selector = new Select(driver.findElement(By.cssSelector("#engine_field")));
        while (selector.getOptions().size() < 1) {
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#engine_field")));
        }

        List<String> list = new ArrayList<>();
        boolean seenAllModels = false;
        boolean containsAll = false;
        for (WebElement el : selector.getOptions()) {
            if (el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if (containsAll) {
                if (seenAllModels)
                    list.add(e.getText());

                if (e.getText().contains("All"))
                    seenAllModels = true;
            } else {
                if (e.getText().trim().length() > 1)
                    list.add(e.getText());
            }
        }

        if (!containsAll)
            list = Lists.reverse(list);

        return list;
    }

    public List<String> getVehicleDetails() {
        Select selector = new Select(driver.findElement(By.cssSelector("#details_field")));
        while (selector.getOptions().size() < 1) {
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }
            selector = new Select(driver.findElement(By.cssSelector("#details_field")));
        }

        List<String> list = new ArrayList<>();
        boolean containsAll = false;
        for (WebElement el : selector.getOptions()) {
            if (el.getText().contains("All")) {
                containsAll = true;
                break;
            }
        }
        for (WebElement e : selector.getOptions()) {
            if (e.getText().trim().length() > 1)
                list.add(e.getText());

        }

        if (!containsAll)
            list = Lists.reverse(list);
        return list;
    }

    public List<String> getMakes() {
        Select makeSelector = new Select(driver.findElement(By.cssSelector("#make_field")));
        List<String> makeList = new ArrayList<>();
        boolean seenAllMakes = false;
        for(WebElement e: makeSelector.getOptions()) {
            if (seenAllMakes) // uncomment this to return the combo functionality
                makeList.add(e.getText());

            if (e.getText().contains("All Makes"))
                seenAllMakes = true;
        }
        return makeList;
    }

    public WebDriver closePopup(WebDriver driver) {
        String originalHandle = driver.getWindowHandle();
        driver.switchTo().activeElement();
        WebElement close = driver.findElement(By.id("sb-nav-close"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", close);
        driver.switchTo().window(originalHandle);

        try {
            Thread.sleep(500);
        } catch (Exception ex) {

        }
        return driver;
    }
}
