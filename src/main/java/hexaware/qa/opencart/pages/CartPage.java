package hexaware.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

	WebDriver driver;
	
	
	//Constructor
	
	public CartPage(WebDriver driver) {
	this.driver= driver;
	}
	
	//By locators
	
	By addToCart= By.id("addtoCart");
	public void togetTitle() {
		System.out.println("cart page title");
	}
}
