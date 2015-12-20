package Travellers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bittstream on 12/9/2015.
 * FIRST CUSTOMER!
 * frank@frankjdavis.com
 */
public class Travelers {
	//private static String page = "https://www.travelers.com/FindAgent/All/AL/EUFAULA/FLOWERS-EUFAULA-LLC-1/";
	private static String page = "https://www.travelers.com/FindAgent/All/AL/ABBEVILLE/";
	List<String> sList;
	//private static String page = "https://www.travelers.com/FindAgent/All/";

	private String outputFile = "travellers2.txt";
	private String outputFileLinks = "links.txt";
	private String outputFileAgentPages = "agentPagesReal.txt";

	public static void main(String[] args) {
	// clearing cookies might solve timeout issue
		Travelers app = new Travelers();
		//app.countryPage(driver, page);
		app.getAgentLinks();
		//app.getAgentPages();


	}

	public void getAgentPages() {
		ArrayList<String> list = new ArrayList<String>();
		InputStream stream = getClass().getClassLoader().getResourceAsStream("Travellers/agentPages");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line.trim());
			}
		} catch (Exception ex) {
			System.out.println("Error reading file");
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(Exception ex) {
					System.out.println("Error closing reader");
				}
			}
		}

		/*
		START OF MAIN LOOP
		 */

		System.out.println("Searching " + list.size() + " agents");
		boolean three = false;

		Proxy proxy1 = new Proxy();
		proxy1.setHttpProxy("104.28.7.255:80");
		DesiredCapabilities capability1 = new DesiredCapabilities();
		capability1.setCapability(CapabilityType.PROXY, proxy1);
		WebDriver driver1 = new FirefoxDriver(capability1);

		DesiredCapabilities capability2 = new DesiredCapabilities();
		Proxy proxy2 = new Proxy();
		proxy2.setHttpProxy("104.28.11.14:80");
		capability2.setCapability(CapabilityType.PROXY, proxy2);
		WebDriver driver2 = new FirefoxDriver(capability2);

		for(int i=0;i<list.size();i++) {
			String link = list.get(i);
			System.out.println(i + " " + link);
			/*
			SETUP NEW PROXY EVERY 3 ITERATIONS
			 */
			if((i+1) % 3 == 0) {
				if(three)
					three = false;
				else
					three = true;
			}

			if(three)
				agentPage(driver1, link); // for each link call the find agent method
			else
				agentPage(driver2, link);

		}
		driver1.close();
		driver2.close();
	}

	private ArrayList<String> loadLinks() {
		ArrayList<String> list = new ArrayList<String>();
		sList = Collections.synchronizedList(loadLinks()); // needs some work

		InputStream stream = getClass().getClassLoader().getResourceAsStream("Travellers/agentLinks");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line.trim());
			}
		} catch (Exception ex) {
			System.out.println("Error reading file");
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(Exception ex) {
					System.out.println("Error closing reader");
				}
			}
		}
		return list;
	}

	public void getAgentLinks() {
		ArrayList<String> list = loadLinks();
		/*
		START OF MAIN LOOP
		 */
		System.out.println("Search " + list.size() + " locations");

		DesiredCapabilities capability1 = new DesiredCapabilities();
		Proxy proxy1 = new Proxy();
		proxy1.setHttpProxy("104.28.4.47");
		capability1.setCapability(CapabilityType.PROXY, proxy1);

		DesiredCapabilities capability2 = new DesiredCapabilities();
		Proxy proxy2 = new Proxy();
		proxy2.setHttpProxy("104.28.5.164");
		capability1.setCapability(CapabilityType.PROXY, proxy2);

		DesiredCapabilities capability3 = new DesiredCapabilities();
		Proxy proxy3 = new Proxy();
		proxy3.setHttpProxy("104.28.15.94");
		capability3.setCapability(CapabilityType.PROXY, proxy3);

		DesiredCapabilities capability4 = new DesiredCapabilities();
		Proxy proxy4 = new Proxy();
		proxy4.setHttpProxy("162.159.243.28");
		capability4.setCapability(CapabilityType.PROXY, proxy4);

		DesiredCapabilities capability5 = new DesiredCapabilities();
		Proxy proxy5 = new Proxy();
		proxy4.setHttpProxy("104.28.2.25");
		capability5.setCapability(CapabilityType.PROXY, proxy5);

		DesiredCapabilities capability6 = new DesiredCapabilities();
		Proxy proxy6 = new Proxy();
		proxy6.setHttpProxy("104.28.16.226");
		capability6.setCapability(CapabilityType.PROXY, proxy6);

		DesiredCapabilities capability7 = new DesiredCapabilities();
		Proxy proxy7 = new Proxy();
		proxy7.setHttpProxy("104.28.16.120");
		capability7.setCapability(CapabilityType.PROXY, proxy7);

		DesiredCapabilities capability8 = new DesiredCapabilities();
		Proxy proxy8 = new Proxy();
		proxy8.setHttpProxy("104.28.15.115");
		capability8.setCapability(CapabilityType.PROXY, proxy8);

		DesiredCapabilities capability9 = new DesiredCapabilities();
		Proxy proxy9 = new Proxy();
		proxy9.setHttpProxy("104.28.2.9");
		capability9.setCapability(CapabilityType.PROXY, proxy9);

		DesiredCapabilities capability10 = new DesiredCapabilities();
		Proxy proxy10 = new Proxy();
		proxy10.setHttpProxy("104.28.16.226");
		capability10.setCapability(CapabilityType.PROXY, proxy10);

		WebDriver driver1 = new FirefoxDriver();
		WebDriver driver2 = new FirefoxDriver(capability1);
		WebDriver driver3 = new FirefoxDriver(capability2);
		WebDriver driver4 = new FirefoxDriver(capability3);
		WebDriver driver5 = new FirefoxDriver(capability4);
		WebDriver driver6 = new FirefoxDriver(capability6);
		WebDriver driver7 = new FirefoxDriver(capability7);
		WebDriver driver8 = new FirefoxDriver(capability8);
		WebDriver driver9 = new FirefoxDriver(capability9);
		WebDriver driver10 = new FirefoxDriver(capability10);

		List<WebDriver> drivers = new ArrayList<WebDriver>();
		drivers.add(driver1);
		drivers.add(driver2);
		drivers.add(driver3);
		drivers.add(driver4);
		drivers.add(driver5);
		drivers.add(driver6);
		drivers.add(driver7);
		drivers.add(driver8);
		drivers.add(driver9);
		drivers.add(driver10);

		Rocket thread1 = new Rocket();
		Rocket thread2 = new Rocket();
		Rocket thread3 = new Rocket();
		Rocket thread4 = new Rocket();
		Rocket thread5 = new Rocket();
		Rocket thread6 = new Rocket();
		Rocket thread7 = new Rocket();
		Rocket thread8 = new Rocket();
		Rocket thread9 = new Rocket();
		Rocket thread10 = new Rocket();

		List<Rocket> rockets = new ArrayList<Rocket>();
		rockets.add(thread1);
		rockets.add(thread2);
		rockets.add(thread3);
		rockets.add(thread4);
		rockets.add(thread5);
		rockets.add(thread6);
		rockets.add(thread7);
		rockets.add(thread8);
		rockets.add(thread9);
		rockets.add(thread10);



		for(int i=0;i<list.size();i += 10) {
			String[] links = {list.get(i), list.get(i+1), list.get(i+1), list.get(i+2), list.get(i+3), list.get(i+4), list.get(i+5), list.get(i+6), list.get(i+7), list.get(i+8), list.get(i+9)};
			List<Thread> threads = new ArrayList<Thread>();

			for(int j=0;j< drivers.size();j++) {
				System.out.println((i+(j+1)) + " " + links[j]);
				drivers.get(j).manage().deleteAllCookies();
				rockets.get(j).setDriver(drivers.get(j));
				rockets.get(j).setLink(links[j]);
				threads.add(new Thread(rockets.get(j)));
			}

			ExecutorService executor = Executors.newFixedThreadPool(10);
			for(Thread instance : threads) {
				executor.execute(instance);
			}
			executor.shutdown();

			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(WebDriver driver: drivers) {
			driver.close();
		}
	}

	public void countryPage(WebDriver driver, String page) {
		driver.get(page);
		Document doc = Jsoup.parse(driver.getPageSource());

		Elements block = doc.select(".stateContainer");
		Elements links = block.select("a");

		for(Element link : links) {
			String linkUrl = link.attr("href");
			WebElement linkElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='"+ linkUrl + "']")));
			linkElement.click();

			statePage(driver);
		}
	}

	public void statePage(WebDriver driver) {
		WebElement selectElementInsuranceFor = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".logo")));
		if(selectElementInsuranceFor.isDisplayed())
			System.out.println("ok");

		Document doc = Jsoup.parse(driver.getPageSource());

		Elements block = doc.select(".city_letter_block");
		Elements links = block.select("a");

		PrintWriter writer = null;
		for(Element link : links) {
			try {
				writer = new PrintWriter(new FileWriter(outputFileLinks, true));
				writer.println("https://www.travelers.com" + link.attr("href"));
				System.out.println("https://www.travelers.com" + link.attr("href"));
			} catch(IOException ex) {
				ex.printStackTrace();
			} finally {
				if(writer != null)
					writer.close();
			}
		}


		driver.navigate().back();
	}

	public synchronized void selectInsuranceType(WebDriver driver) {
		WebElement selectElementInsuranceFor = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("insurance_type")));
		Select selectInsuranceFor = new Select(selectElementInsuranceFor);
		selectInsuranceFor.selectByValue("B");
	}

	public synchronized void selectLob(WebDriver driver) {
		WebElement selectElementLineOfBusiness = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("lob")));
		Select selectLineOfBusiness = new Select(selectElementLineOfBusiness);
		selectLineOfBusiness.selectByVisibleText("Ocean Marine");
	}

	public synchronized void clickSearchButton(WebDriver driver) {
		WebElement searchElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("search_button")));
		searchElement.click();
	}

	public synchronized void findAgentPage(WebDriver driver, String page) {
		driver.get(page);
		selectInsuranceType(driver);
		selectLob(driver);
		clickSearchButton(driver);

		boolean go = true;
		int num = 5;
		while(go) {
			for (int i = 0; i < 10; i++) { // for each 10 items on the page
				String cssAgentRow = "div.agent_row:nth-child(" + num + ") > span:nth-child(1) > a:nth-child(1)";
				try {
					WebElement st = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssAgentRow)));
					String link = st.getAttribute("href");

					System.out.println(" " + link);
					printItem(link, outputFileAgentPages);

					num++;
				} catch (Exception ex) {
					System.out.println("no more entries to parse");
					go = false;
					break;
				}
			}

			try {
				WebElement nextButton = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".page_next")));
				System.out.println("found next button!");

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", nextButton);
			} catch (Exception ex) {
				System.out.println("next button not found!");
				go = false;
			}
		}
		System.out.println("no more pages to parse for this location");
	}

	public synchronized void printItem(String line, String outputFile) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(outputFile, true));
			writer.println(line);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}



	public void agentPage(WebDriver driver, String url) {
		String cssName = ".main > h2:nth-child(1)";
		String cssWebsite = ".agency-url-div";
		String cssProducts = ".products-list";
		String cssAgentCenter = ".agent-page-center";

		driver.get(url);

		WebElement eName = driver.findElement(By.cssSelector(cssName));
		String name = eName.getText();
		System.out.println(name);

		WebElement eWebsite = driver.findElement(By.cssSelector(cssWebsite));
		String website = eWebsite.getText();
		System.out.println(website);

		/*
			Parse the address lines available
		 */
		WebElement agentPageCenter = driver.findElement(By.cssSelector(cssAgentCenter));
		String[] agentPageCenterLines = agentPageCenter.getText().split("\n");
		System.out.println("\nAddress Lines available count: " + agentPageCenterLines.length);

		String telephone = "N/A";
		String fax = "N/A";
		String email = "N/A";
		String address = "N/A";
		StringBuilder sbAddress = new StringBuilder();
		boolean getDirectionsSeen = false;
		for(int j=0;j<agentPageCenterLines.length;j++) {
			String line = agentPageCenterLines[j];
			System.out.println((j+1) + " " + line);

			if(line.contains("Get Directions"))
				getDirectionsSeen = true;

			if(!getDirectionsSeen) {
				sbAddress.append(line + " ");
			}

			if(line.contains("Phone:")) {
				String[] tokens = line.split("Phone:");
				telephone = tokens[1].trim();
			}

			if(line.contains("Fax:")) {
				String[] tokens = line.split("Fax:");
				fax = tokens[1].trim();
			}

			if(line.contains("@")) {
				email = line.trim();
			}
		}
		if(sbAddress.length() > 1)
			address = sbAddress.toString();

		System.out.println();
		System.out.println("address is " + address);
		System.out.println("telephone is " + telephone);
		System.out.println("fax is " + fax);
		System.out.println("email is " + email);




		/*
			Parse the products offered
		 */
		WebElement eProducts = driver.findElement(By.cssSelector(cssProducts));
		String[] prods = eProducts.getText().split("\n");
		System.out.println("\nProducts offered count: " + prods.length);

		StringBuilder sbProducts = new StringBuilder();
		for(int i=0;i<prods.length;i++) {
			System.out.println((i+1) + " " + prods[i]);
			sbProducts.append(prods[i]);

			if(i < prods.length-1) {
				sbProducts.append(", ");
			}
		}
		String products = sbProducts.toString();

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(outputFile, true));
			writer.println(name + ";" + email + ";" +  website + ";" + telephone + ";" + fax + ";" + address + ";" + products + ";" + url);
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			if(writer != null)
				writer.close();
		}











	}

}
