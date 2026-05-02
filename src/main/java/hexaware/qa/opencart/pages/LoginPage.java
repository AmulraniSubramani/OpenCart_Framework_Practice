package hexaware.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hexaware.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
		
	// 1. By locator or OR (object repository)
	private By emailID = By.id("input-email");
	private By pwd= By.id("input-password");
	private By submit = By.xpath("//input[@value='Login']");
	private By forgotPassword= By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	
	//2. page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		elementUtil= new ElementUtil(driver);
	}
	
	
	//3. page action/method/features
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	@Step("Getting login page url....")
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}
	
	@Step("Getting forgot password exist.....")
	public boolean isForgotPasswordExist() {
		return elementUtil.doIsdisplayed(forgotPassword);
	}
	
	@Step("Register link exist or not ....")
	public boolean isRegisterLinkExist() {
		return elementUtil.doIsdisplayed(registerLink); 
	}
	
	@Step("Login with username : {0} and password {1}")
	public AccountsPage doLogin(String userName,String password) {
		elementUtil.doSendKey(emailID, userName);
		elementUtil.doSendKey(pwd, password);
		elementUtil.doClick(submit);
		return new AccountsPage(driver);
		
	}
	
	@Step("Navigating to register page....")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
