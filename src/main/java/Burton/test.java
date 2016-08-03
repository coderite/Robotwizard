package Burton;

import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zenbox on 2/9/2016.
 */
public class test {
    public static void main(String[] args) {
        test app = new test();
        app.start();
    }

    public void start() {

        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebDriver driver2 = new ChromeDriver();
        driver2.get("http://www.yahoo.com");


    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }
}
