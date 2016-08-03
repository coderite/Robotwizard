package Marinas.Force5;

import Carters.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 4/9/2016.
 */
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        DirectoryPage directoryPage = new DirectoryPage();
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Marinas/force5Registrants.txt");

        for(int i=0;i<9;i++) {
            for(int j=0;j<directoryPage.getNumberOfRegistrants();j++) {
                RegistrantPage registrantPage = directoryPage.viewEntry(j);
                Registrant registrant = registrantPage.getRegistrant();
                System.out.println(registrant);
                helper.printItem(registrant.toPrint());
                registrantPage.back();

                try {
                    Thread.sleep(2000);
                } catch(Exception x) {

                }
            }
            if(i < 8)
                directoryPage.gotoNextPage();
        }
        directoryPage.quit();

    }
}
