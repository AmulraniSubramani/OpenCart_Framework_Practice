package hexaware.qa.opencart.tests;

import java.lang.ref.SoftReference;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import hexaware.qa.opencart.base.BaseTest;
import hexaware.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUP() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void TC1_productHeaderTest() {
		resultPage = accountPage.doSearch("macbook");
		proInfoPage = resultPage.selectProduct("MacBook Air");
		String actualHeader = proInfoPage.getProductHeaderText();
		Assert.assertEquals(actualHeader, "MacBook Air");
	}

	@DataProvider
	public Object[][] getImageData() {
		return new Object[][] { { "macbook pro", "MacBook Pro", 4 }, { "macbook", "MacBook", 5 },
				{ "macbook air", "MacBook Air", 4 }, { "apple", "Apple Cinema 30\"", 6 } };
	}

	@Test(dataProvider = "getImageData")
	public void TC2_productImageCount(String searchProduct, String productName, int imageCount) {
		resultPage = accountPage.doSearch(searchProduct);
		proInfoPage = resultPage.selectProduct(productName);
		Assert.assertEquals(proInfoPage.getProductImageCount(), imageCount);

	}

	@Test
	public void TC3_prodMetaDataTest() {
		resultPage = accountPage.doSearch("macbook");
		proInfoPage = resultPage.selectProduct("MacBook Pro");
		Map<String, String> actProdMap = proInfoPage.getProductMetaData();
		actProdMap.forEach((k,v) ->System.out.println(k + ":" + v));
		softAssert.assertEquals(actProdMap.get("ProductName"), "MacBook Pro");
		softAssert.assertEquals(actProdMap.get("price"), "$2,000.00");
		softAssert.assertEquals(actProdMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdMap.get("Reward Points"), "800");
		softAssert.assertAll();

	}

}
