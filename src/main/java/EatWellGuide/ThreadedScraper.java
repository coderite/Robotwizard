package EatWellGuide;

import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import sun.awt.windows.ThemeReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
public class ThreadedScraper {
	private String resourceLinks = "EatWellGuide/establishmentLinks";
	private static List<String> sList;
	private static final int NUMOFTHREADS = 5;

	public static void main(String[] args) {
		ThreadedScraper app = new ThreadedScraper();
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

	public void run() {
		String un = UUID.randomUUID().toString();
		WebDriver driver = new FirefoxDriver();

		Helpers helper = new Helpers();
		helper.setOutputFile("Results/EatWellGuide/" + un + ".txt");
		Helpers helperMissing = new Helpers();
		helperMissing.setOutputFile("Results/EatWellGuide/missing" + un + ".txt");

        while(ThreadedScraper.sListSize() != 0) {
			String link = ThreadedScraper.shift();
			System.out.println(ThreadedScraper.sListSize() + link + " ");

			EstablishmentPage page = new EstablishmentPage();
			driver.get(link);
			try {
				Document doc = Jsoup.parse(driver.getPageSource());
				page.setDoc(doc);
				page.scrape();
				helper.printItem(page.getRecord().printable());
				System.out.println(page.getRecord());
			} catch (Exception ex) {
				ex.printStackTrace();
				helperMissing.printItem(link);
				System.out.println(" NOT OK");
			}
		}
		driver.close();
	}
}
