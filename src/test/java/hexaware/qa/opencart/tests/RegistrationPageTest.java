package hexaware.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hexaware.qa.opencart.base.BaseTest;
import hexaware.qa.opencart.utils.Constants;
import hexaware.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	// getting data only from data provider without excelsheet

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "Varnika", "Pandiyan", "varnika@gmail.com", "458652", "password@123", "Yes" }
//
//		};
//	}
//
//	@Test(dataProvider = "getData")
//	public void TC1_RegistrationTest(String firstName, String lastName, String emailId, String phoneNo, String password,
//			String subscribe) {
//		Assert.assertTrue(registrationPage.registration(firstName, lastName, emailId, phoneNo, password, subscribe));
//	}

	
	public String getRandomNumber() {
		Random random= new Random();
		String email= "testautomation"+random.nextInt(5000)+"@gmail.com";
		
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;

	}

	@Test(dataProvider = "getRegTestData")
	public void TC1_RegistrationTest(String firstName, String lastName,  String phoneNo, String password,
			String subscribe) {
		Assert.assertTrue(registrationPage.registration(firstName, lastName, getRandomNumber(), phoneNo, password, subscribe));
	}

}
