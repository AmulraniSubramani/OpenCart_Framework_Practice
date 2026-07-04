package hexaware.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() { 

		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--no-sandbox");
		co.addArguments("--disable-dev-shm-usage");

		return co;
		// either we can add if condition

//		if(Boolean.parseBoolean(prop.getProperty("headless"))){
//			 co.addArguments("--headless");
//		}
//		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
//			 co.addArguments("--incognito");
//		}

	}

	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--incognito");
		fo.addArguments("--remote-allow-origins=*");
		fo.addArguments("--no-sandbox");
		fo.addArguments("--disable-dev-shm-usage");
		return fo;

	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			eo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			eo.addArguments("--incognito");
		eo.addArguments("--remote-allow-origins=*");
		eo.addArguments("--no-sandbox");
		eo.addArguments("--disable-dev-shm-usage");
		return eo;

	}
}