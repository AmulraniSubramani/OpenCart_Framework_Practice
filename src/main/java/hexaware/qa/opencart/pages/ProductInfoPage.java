package hexaware.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hexaware.qa.opencart.utils.Constants;
import hexaware.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By ProdmetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By ProdPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImageCount() {
		return elementUtil.waitForElementsVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductMetaData() {
		Map<String, String> prodMap = new HashMap<String, String>();
		String productName = elementUtil.doGetText(productHeader);
		prodMap.put("ProductName", productName);
		getProdMetaData(prodMap); 
		getProdPriceData(prodMap);
		return prodMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
//	£1,225.00
//	Ex Tax: £1,225.00

	private void getProdMetaData(Map<String, String> prodMap) {
		List<WebElement> metaList = elementUtil.getElements(ProdmetaData);
		for (WebElement e : metaList) {
			String metaTest = e.getText();
			String metaKey = metaTest.split(":")[0].trim();
			String metaValue = metaTest.split(":")[1].trim();
			prodMap.put(metaKey, metaValue);
		}
	}
	
		private void getProdPriceData(Map<String, String> prodMap) {
			List<WebElement> priceList = elementUtil.getElements(ProdPriceData);
			String actPrice=priceList.get(0).getText().trim();
			
			String exTaxPrice=priceList.get(1).getText().trim();

			
			prodMap.put("price", actPrice);
			
		//	String ExTaxPrice = exTaxPrice.split(":")[1];
			prodMap.put("exTaxPrice",exTaxPrice.split(":")[1]);


			
		}
	
	
	
		
	}

