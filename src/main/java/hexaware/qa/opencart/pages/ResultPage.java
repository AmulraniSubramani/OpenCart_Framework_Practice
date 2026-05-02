package hexaware.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hexaware.qa.opencart.utils.Constants;
import hexaware.qa.opencart.utils.ElementUtil;

public class ResultPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By secHeader = By.cssSelector("div#content h1");
	private By productList = By.cssSelector("div.caption a");

	public ResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getSearchHeaderName() {
		return elementUtil.doGetText(secHeader);
	}

	public int getSearchProductListCount() {
		return elementUtil.waitForElementsVisible(productList, Constants.DEFAULT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		List<WebElement> searchList = elementUtil.waitForElementsVisible(productList, 10);

		for (WebElement e : searchList) {
			String text = e.getText().trim();
			//System.out.println(" product name: " + text);
			if (text.equals(mainProductName)) {
				e.click();
				System.out.println("Clicked");
				break;
			}
		}

		return new ProductInfoPage(driver);
	}
}
