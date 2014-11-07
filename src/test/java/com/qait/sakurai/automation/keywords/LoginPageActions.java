package com.qait.sakurai.automation.keywords;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import static com.qait.automation.utils.YamlReader.getData;

public class LoginPageActions extends GetPage {

	public LoginPageActions(WebDriver driver) {
		super(driver, "LoginPage");
	}

	//INFO: using data from yaml file
	public void verifyUserIsOnLoginPage() {
		verifyPageTitleExact();
		isElementDisplayed("lnk_selectSubject", getData("users.subject"));
		verifyPageUrlContains("login/signin");
		logMessage("Verified that the user is on the Login Page!!!");
	}

	public void selectSubject(String subjectName) {
		isElementDisplayed("lnk_selectSubject", subjectName);
		element("lnk_selectSubject", subjectName).click();
		isElementDisplayed("inp_username");
		isElementDisplayed("inp_password");
	}
	
	public void enterUserCredentials(String username, String password) {
		logMessage("[Info]: Trying to enter username '" + username + "' and password '" + password + "'.");
		element("inp_username").clear();
		element("inp_username").sendKeys(username);
		element("inp_password").clear();
		element("inp_password").sendKeys(password);
		element("btn_submit").click();
	}

	public void verifyLoginErrorMessage(String errorElement, String errorText) {
		isElementDisplayed(errorElement);
		assertThat("FAILED: Error Message is not as expected", element(errorElement).getText(), containsString(errorText));
		logMessage("PASSED: Login error messages are as expected");

	}
}