package hexaware.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Amulrani
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highLight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver on the basis of given name
	 * 
	 * @param browser
	 * @return this method will return driver
	 */

	public WebDriver initDriver(Properties prop) {

		String t = prop.getProperty("test").trim();
		System.out.println("Test value is :" + t);

		optionsManager = new OptionsManager(prop);
		highLight = prop.getProperty("highlight").trim();
		String browser = prop.getProperty("browser").trim();

		System.out.println("Browser name is :" + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); // tl hleps for pararrel execution

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

		} else {
			System.out.println("please pass the correct browser name :" + browser);
		}

//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
//		return driver;

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get(); // to get local copy of tldriver
	}

	/**
	 * This method used to initliaze the properties on the basis of given
	 * environment
	 * 
	 * @return method return prop
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment: " + env);

			try {
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				default:
					System.out.println(".....Please pass the right environment....." + env);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * 
	 * take screenshot
	 * @return 
	 */

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=  new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
