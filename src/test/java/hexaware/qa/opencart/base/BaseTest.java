package hexaware.qa.opencart.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import hexaware.qa.opencart.factory.DriverFactory;
import hexaware.qa.opencart.pages.AccountsPage;
import hexaware.qa.opencart.pages.LoginPage;
import hexaware.qa.opencart.pages.ProductInfoPage;
import hexaware.qa.opencart.pages.RegistrationPage;
import hexaware.qa.opencart.pages.ResultPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public LoginPage loginPage;
	public AccountsPage accountPage;
	public ResultPage resultPage;
	public ProductInfoPage proInfoPage;
	public RegistrationPage registrationPage;
	
	public SoftAssert softAssert;

	@BeforeTest
	public void setUp() {

		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
		softAssert= new SoftAssert();
		System.out.println("LoginPage initialized: " + (loginPage != null));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
