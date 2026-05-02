package hexaware.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import hexaware.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highLight)) {
			jsUtil.flash(ele);
		}
		return ele;

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKey(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}
	
	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public void doActionSendkeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();

	}

	public boolean doIsdisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public boolean doIsSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public String doGetAttributeValue(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean checkElementDisabled(By locator, String attrName) {
		String attrValue = doGetAttributeValue(locator, attrName);
		if (attrValue.equals("true")) {
			return true;
		}
		return false;
	}

	public void doClinkLink(By locator, String linkValue) {
		List<WebElement> linkList = getElements(locator);
		System.out.println("Total link in the page: " + linkList.size());
		for (WebElement e : linkList) {
			String linkName = e.getText();
			if (linkName.trim().equals(linkValue)) {
				e.click();
				break;
			}
		}
	}

	public boolean doCheckElementDisplayed(By locator) {
		if (getElements(locator).size() == 1)
			return true;
		return false;
	}

	public boolean doCheckElementDisplayed(By locator, int eleOccurance) {
		if (getElements(locator).size() == eleOccurance)
			return true;
		return false;
	}

	// ************************************drop down utils**************************

	public void doSelectByIndex(By locator, int index) {
		Select sel = new Select(getElement(locator));
		sel.selectByIndex(index);
	}

	public boolean doSelectByVisibleText(By locator, String visibleText) {
		Select sel = new Select(getElement(locator));
		sel.selectByVisibleText(visibleText);
		return isDropDownValueSelected(sel, visibleText);

	}

	public boolean doSelectByValue(By locator, String value) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue(value);
		return isDropDownValueSelected(sel, value);
	}

	public boolean isDropDownValueSelected(Select select, String expectedValue) {
		if (select.getFirstSelectedOption().getText().contains(expectedValue)) {
			return true;
		}
		return false;
	}

	public void doSelectDropDown(By locator, String value) {
		Select sel = new Select(getElement(locator));
		List<WebElement> optionsList = sel.getOptions();
		iterateDropDownSelect(optionsList, value);
	}

	public void selectDropDownWithoutSelect(By locator, String value) {
		List<WebElement> countryList = getElements(locator);
		iterateDropDownSelect(countryList, value);

	}

	private void iterateDropDownSelect(List<WebElement> optionList, String value) {
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// ***********************************Actions class
	// utils******************************

	public void parentChildMenuHandle(By parent, By child) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent)).build().perform();
		doClick(child);
	}

	public int rightClickItemCount(By rightClick, By items) {
		return doRightClickList(rightClick, items).size();
	}

	/**
	 * This is the method to clickOnRightClickItem by passing rightclick By locator,
	 * item By locator, and the value which u want click
	 * 
	 * @param rightClick
	 * @param items
	 * @param value
	 */
	public void clickOnRightClickItem(By rightClick, By items, String value) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick)).build().perform();
		List<WebElement> menuList = getElements(items);
		for (WebElement e : menuList) {
			if (e.getText().equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * This is the method to used to get list of items by right the element, by
	 * passing rightclick By locator
	 * 
	 * @param rightclick
	 * @param items
	 * @return
	 */
	public List<String> doRightClickList(By rightclick, By items) {
		List<String> itemValueList = new ArrayList<String>();

		Actions act = new Actions(driver);
		act.contextClick(getElement(rightclick)).build().perform();
		List<WebElement> menuList = getElements(items);
		System.out.println(menuList);

		for (WebElement e : menuList) {
			String text = e.getText();
			itemValueList.add(text);
		}

		return itemValueList;
	}

	/****************************************
	 * Wait utils
	 ***********************************/

//	public WebElement waitForElementPresent(By locator, Duration i) {
//		WebDriverWait wait = new WebDriverWait(driver, i);
//		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
//
//	public WebElement waitForElementPresent(By locator, Duration i, Duration intervalTime) {
//		WebDriverWait wait = new WebDriverWait(driver, i, intervalTime);
//		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
//
//	public Alert waitJSAlert(long timeOut) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		return wait.until(ExpectedConditions.alertIsPresent());
//
//	}

	public Alert waitJSAlert(long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void acceptAlert(long timeout) {
		waitJSAlert(timeout).accept();
	}

	public void dismissAlert(long timeout) {
		waitJSAlert(timeout).dismiss();
	}

	public void sendKeysAlert(long timeout, String value) {
		waitJSAlert(timeout).sendKeys(value);
	}

	public String getTextAlert(long timeout) {
		Alert alt = waitJSAlert(timeout);
		String text = alt.getText();
		alt.accept();
		return text;
	}

	public boolean waitForUrlContains(String fraction, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.urlContains(fraction));

	}

	public boolean waitForUrlToBe(String urlToBe, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.urlToBe(urlToBe));

	}

	public String waitForTitleContain(long timeout, String Titelfraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleContains(Titelfraction))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForTitleToBe(long timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}

	public void WaitForFrameUsingIDOrName(String frameIDOrName, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
	}

	public void WaitForFrameUsingIndex(int frameIndex, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void WaitForFrameUsingLocator(By frameLocator, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void WaitForFrameUsingWebElement(WebElement frameElement, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public List<WebElement> waitForElementPresence(By footerList, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(footerList));
	}

	public List<String> getElementsTextList(By footerList, int timeOut) {
		List<WebElement> elelist = waitForElementPresence(footerList, timeOut);
		List<String> eleValue = new ArrayList<String>();
		for (WebElement e : elelist) {
			eleValue.add(e.getText());
		}
		return eleValue;
	}

	public void printElementsTextList(By footerList, int timeOut) {
		List<WebElement> elelist = waitForElementPresence(footerList, timeOut);
		for (WebElement e : elelist) {
			System.out.println(e.getText());
		}
	}

	public List<String> getVisibleElementsTextList(By footerList, int timeOut) {
		List<WebElement> elelist = waitForElementsVisible(footerList, timeOut);
		List<String> eleValue = new ArrayList<String>();
		for (WebElement e : elelist) {
			eleValue.add(e.getText());
		}
		return eleValue;
	}

	public List<WebElement> waitForElementsVisible(By footerList, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerList));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPresent(By locator, long timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An Expecpation for checkin that an element is present on the page and
	 * visible. Visibility means that the element is not only displayed but also has
	 * a height and width that is greater than 0.
	 * 
	 * @param timeOut
	 * @param lcoator
	 * @return
	 */
	public WebElement waitForElementVisible(By lcoator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(lcoator));
	}

	public WebElement waitForElementToBeClikable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, int timeOut) {
		waitForElementToBeClikable(locator, timeOut).click();
	}

	public WebElement waitForElementPrsentWithWebDriverWait(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPrsentWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public Alert waitForAlertPresentWithFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_FRAME_MSG).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoAlertPresentException.class);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public WebDriver waitForFrametPresentWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_FRAME_MSG).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchFrameException.class);
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

}
