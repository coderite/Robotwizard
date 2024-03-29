package KSBLaw;

import Carters.Helpers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class Main {
	private String resourceLinks = "KsbClaw/missingLinks";
	private static List<String> sList;
	private static final int NUMOFTHREADS = 1;

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
  	public void run() {
		String uniqueNumber = UUID.randomUUID().toString();
		Helpers helper = new Helpers();
		helper.setOutputFile("Results/Ksblaw/" + uniqueNumber + ".txt");

        while(Main.sListSize() != 0) {
			String link = Main.shift();
			System.out.println(link);

			try {
				Document doc = Jsoup.connect(link).get();
				String name = doc.getElementById("lblName1").text();
				String docketNumber = doc.getElementById("lblDocket1").text();
				String bookingDate = doc.getElementById("lblArrestDate1").text();
				String cellLocation = doc.getElementById("CellLocation").text().replaceAll(";","");

				/*
				System.out.println("name: " + name);
				System.out.println("docket#: " + docketNumber);
				System.out.println("booking date: " + bookingDate);
				System.out.println("Cell location: " + cellLocation);
				*/

				String outputText = name + ";" + docketNumber + ";" + bookingDate + ";" + cellLocation;
				System.out.println(outputText);
				helper.printItem(outputText);
				System.out.println(Main.sListSize());

			} catch(IOException ex) {
				System.out.println("something went wrong");
				helper.printItem("IO Exception: " + link);
			} catch(NullPointerException ex) {
				System.out.println("removed!");
				helper.printItem("removed;"+ link);
			} catch(Exception ex) {
				System.out.println("ERROR");
				helper.printItem("error;"+ link);
			}
		}

	}
}
