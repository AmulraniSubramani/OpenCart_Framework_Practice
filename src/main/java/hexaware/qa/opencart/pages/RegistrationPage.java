package hexaware.qa.opencart.pages;

import java.lang.annotation.ElementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hexaware.qa.opencart.utils.Constants;
import hexaware.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By subsribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subsribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agree = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");

	private By sucessMsg = By.cssSelector("div#content h1");

	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public boolean registration(String firstName, String lastName, String email, String telephone, String password,
			String subScribe) {

		fillRegForm(firstName, lastName, email, telephone, password);
		selectSubscribtionOption(subScribe);
		selectAgreementAndContinue();
		return getRegistrationStatus();

	}

	private boolean getRegistrationStatus() {
		String mesg = elementUtil.doGetText(sucessMsg);
		if (mesg.contains(Constants.REGISTER_SUCESS_MESSAGE)) {
			elementUtil.doClick(logout);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

	private void selectAgreementAndContinue() {
		elementUtil.doClick(agree);
		elementUtil.doClick(continueBtn);

	}

	private void selectSubscribtionOption(String subScribe) {
		if (subScribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subsribeYes);
		} else {
			elementUtil.doClick(subsribeNo);
		}
	}

	private void fillRegForm(String firstName, String lastName, String email, String telephone, String password) {
		elementUtil.doSendKey(this.firstName, firstName);
		elementUtil.doSendKey(this.lastName, lastName);
		elementUtil.doSendKey(emailID, email);
		elementUtil.doSendKey(telePhone, telephone);
		elementUtil.doSendKey(this.password, password);
		elementUtil.doSendKey(confirmPassword, password);

	}

}
