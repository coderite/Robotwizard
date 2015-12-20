package GoogleCrawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by bittstream on 12/8/2015.
 */
public class SelHard {
	private String cssNext = "#pnnext > span:nth-child(2)";
	private String searchPhrase = "\"gmail.com\" inurl:linkedin.com mortgage loan officer";
	private String cssEnglishResults = "#_L8b > div:nth-child(1) > a:nth-child(3)";
	private String cssOmittedResults = "#ofr > i:nth-child(1) > a:nth-child(2)";
	private String cssCaptcha = "#captcha";
	private String outputFile = "search2.txt";

	public static void main(String[] args) throws Exception{
		SelHard app = new SelHard();
		app.start();
	}

	public void start() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com/ncr");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#lst-ib")).sendKeys(searchPhrase);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".lsb")).click();
		Thread.sleep(3000);

		Boolean isCaptchShown = driver.findElements(By.cssSelector(cssCaptcha)).size() > 0;
		if(isCaptchShown) {
			Thread.sleep(15000);
		}

		WebElement element = driver.findElement(By.cssSelector(cssEnglishResults));
		if(element.isDisplayed()) {
			element.click();
			Thread.sleep(2000);
		}

		for(int i=0;i<100;i++) {
			Thread.sleep(2000);
			if(i == 2){
				WebElement element2 = driver.findElement(By.cssSelector(cssOmittedResults));
				if (element2.isDisplayed()) {
					element2.click();
					Thread.sleep(2000);
				}
			}

			String source = driver.getPageSource();
			parsePage(source);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(cssNext)).click();

			Thread.sleep(3000);
			Boolean isCaptchaShown2 = driver.findElements(By.cssSelector(cssCaptcha)).size() > 0;
			if(isCaptchaShown2) {
				Thread.sleep(15000);
			}


		}
		driver.close();
	}

	public void parsePage(String source) {
		ArrayList<String> cList = new ArrayList<String>();
		ArrayList<String> tList = new ArrayList<String>();
		ArrayList<String> lList = new ArrayList<String>();

		Document doc = Jsoup.parse(source);
		System.out.println(doc.text());

		Elements contents = doc.select("span[class=st]");
		for (Element content : contents) {
			String text = content.text();
			cList.add(text);
		}

		Elements titles = doc.select("h3[class=r]");
		for(Element title : titles) {
			String header = title.text();
			String link = title.attr("a href");
			tList.add(header);
			lList.add(link);
		}

		for(int i=0;i<tList.size();i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(tList.get(i) + ";" + lList.get(i) + ";" + cList.get(i));
			String line = sb.toString();

			System.out.println(line);
			writeResult(line);
		}


	}

	public void writeResult (String line) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(outputFile, true));
			writer.println(line);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
