package Marinas.SAMs;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zenbox on 4/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start() {
        System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
        SurveyorsByState surveyorsByState = new SurveyorsByState(new ChromeDriver());
        surveyorsByState.loopThroughState();
    }
}
