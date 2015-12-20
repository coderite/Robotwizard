package Travellers;

import org.openqa.selenium.WebDriver;

/**
 * Created by bittstream on 12/18/2015.
 */
public class Rocket implements Runnable {
	private WebDriver driver;
	private String link;

	public void run() {
		new Travelers().findAgentPage(driver, link);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
