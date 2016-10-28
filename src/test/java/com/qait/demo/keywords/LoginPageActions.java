package com.qait.demo.keywords;

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
		
	}
}