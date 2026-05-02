package hexaware.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import hexaware.qa.opencart.base.BaseTest;
import hexaware.qa.opencart.pages.LoginPage;
import hexaware.qa.opencart.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Open cart application- Design login page")
@Story("US 101: Login page feature with some basic madules and functionalities")
public class LoginPageTest extends BaseTest {



//	@Test
//	public void Test() {
//		System.out.println("Hi");
//		String title = loginPage.getLoginPageTitle();
//		System.out.println("Actual title is :" + title);
//
//	}

	
	@Description("This is my login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC1_loginPageTitleTest() throws InterruptedException {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Actual title is :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("This is my login page url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC2_loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("Actual page url is :" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_VALUE));
	}

	@Description("This is forgot Password link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void TC3_forgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordExist());
	}
	
	@Description("This is Register link Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC4_registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}

	@Description("This is login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void TC_5loginTest() {

		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
}
