package ThreadedBaseTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bittstream on 12/19/2015.
 *
 * BASE FRAME FOR MULTITHREADED SELENIUM PROJECTS
 * USE THIS TEMPLATE TO START NEW PROJECTS THAT
 * REQUIRE ITERATING OVER LARGE LISTS WITH WEBDRIVER
 */
public class Main {
	private String resourceLinks = "Travellers/agentLinks";
	private static List<String> sList;
	private static final int NUMOFTHREADS = 10;

	public static void main(String[] args) {
		Main app = new Main();
		app.start();
	}

	public void start() {
		ArrayList<String> list = loadLinks();
		sList = Collections.synchronizedList(list);

		long startTime = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(NUMOFTHREADS);
		for(int i=0;i<NUMOFTHREADS;i++){
			executor.execute(new Blast());
		}
		executor.shutdown();

		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " ms");
	}

	private ArrayList<String> loadLinks() {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceLinks);
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

	public static synchronized String shift() {
		if(sList.size() > 0) {
			String item = sList.get(0);
			sList.remove(0);
			return item;
		}
		return null;
	}

	public static synchronized int sListSize() {
		return sList.size();
	}
}


/*
ADD NEW CODE TO THE RUNNABLE CLASS BELOW
 */
class Blast implements Runnable {
    private static String urlBase = "http://ec2-52-27-159-114.us-west-2.compute.amazonaws.com:4444/wd/hub";

	public void run() {
        WebDriver driver = null;
        Capabilities capabilities = DesiredCapabilities.firefox();

        try {
            driver = new RemoteWebDriver(new URL(urlBase), capabilities);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        while(Main.sListSize() != 0) {
			String link = Main.shift();
			System.out.println(link);
			driver.get(link);
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("search_button")));
		}
		driver.close();
	}
}
