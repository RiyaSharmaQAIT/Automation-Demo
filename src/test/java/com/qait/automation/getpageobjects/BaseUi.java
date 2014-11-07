/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.DataReadWrite.getProperty;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import com.qait.automation.utils.SeleniumWait;

/**
 * 
 * @author prashantshukla
 */
public class BaseUi {

	WebDriver driver;
	protected SeleniumWait wait;
	private String pageName;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty(
				"Config.properties", "timeout")));
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	public void logMessage(String message) {
		Reporter.log(message, true);
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle);
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
		} catch (TimeoutException ex) {
			Assert.fail("FAILED: PageTitle for " + pageName
					+ " is not exactly: '" + expectedPagetitle
					+ "'!!!\n instead it is :- " + driver.getTitle());
		}
		logMessage("PASSED: PageTitle for " + pageName + " is exactly: '"
				+ expectedPagetitle + "'");
	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 * 
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {

		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("FAILED: As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '"
					+ expectedPagetitle + "'.");
		}
		String actualPageTitle = getPageTitle().trim();
		logMessage("PASSED: PageTitle for " + actualPageTitle + " contains: '"
				+ expectedPagetitle + "'.");
	}

	protected void verifyPageUrlContains(String pageUrlPart){
		String currentUrl = getCurrentURL();
		assertThat("FAILED: wrong page. Url does not contain expected value", currentUrl, containsString(pageUrlPart));
		logMessage("PASSED: Current Page url '" + currentUrl + "' is expected!!!");
	}
	
	protected WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	protected void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	protected void handleAlert() {
		try {
			switchToAlert().accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
		}
	}

	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected void selectProvidedTextFromDropDown(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}

	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	protected void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element
					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}

}