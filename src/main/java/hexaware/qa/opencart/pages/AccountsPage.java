package hexaware.qa.opencart.pages;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hexaware.qa.opencart.utils.Constants;
import hexaware.qa.opencart.utils.ElementUtil;
import hexaware.qa.opencart.utils.JavaScriptUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;
	// 1. By locator

	private By search = By.name("search");
	private By logout = By.linkText("Logout");
	private By accountSectionHeader = By.xpath("//div[@id='content']/h2");
	private By searchIcon = By.cssSelector(" div#search span");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String accPageTitle() {
		return elementUtil.waitForTitleToBe(Constants.DEFAULT_TIME_OUT, Constants.ACC_PAGE_TITLE);
	}

	public boolean isLogOutLinkExist() {
		return elementUtil.doIsdisplayed(logout);
	}

	public boolean isSeachFieldExist() {
		return elementUtil.doIsdisplayed(search);
	}

	public List<String> getAccountSectionList() {
		List<WebElement> secList = elementUtil.getElements(accountSectionHeader);
		List<String> secHeaderList = new ArrayList<String>();

		for (WebElement e : secList) {
			secHeaderList.add(e.getText());
		}
		return secHeaderList;
	}

	public ResultPage doSearch(String produtName) {
		System.out.println("Product Name:" + produtName);
		elementUtil.doClear(search);
		elementUtil.doSendKey(search, produtName);
		elementUtil.doClick(searchIcon);
		return new ResultPage(driver);

	}
}
