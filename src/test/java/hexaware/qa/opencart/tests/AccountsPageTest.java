package hexaware.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hexaware.qa.opencart.base.BaseTest;
import hexaware.qa.opencart.utils.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic 200: Open cart application- Design Account page")
@Story("US 201: Account page features with some basic modules and functionalities")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUP() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void TC1_accPageTileTest() {
		String title = accountPage.accPageTitle();
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}
	@Test
	public void TC2_accPageLogoutLinkTest() {
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}

	@Test
	public void TC3_accPageSearchTest() {
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}

	@Test
	public void TC4_accPageSecHeaderTest() {
		List<String> actSecList = accountPage.getAccountSectionList();
		System.out.println(actSecList);
		Assert.assertEquals(actSecList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);

	}
	
	@DataProvider
	public Object[][] productData() {
		return  new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Apple"},
			};
	}
	
	@Test(dataProvider = "productData")
	public void TC5_searchTest(String productName) {
	resultPage=	accountPage.doSearch(productName);
	Assert.assertTrue(resultPage.getSearchProductListCount() > 0 );
	}

	@Test
	public void TC6_selectProductTest() throws InterruptedException {
		resultPage= accountPage.doSearch("macbook");
		Thread.sleep(10000);
		System.out.println(resultPage.getSearchProductListCount());
		resultPage.selectProduct("MacBook Pro");
		}
}
