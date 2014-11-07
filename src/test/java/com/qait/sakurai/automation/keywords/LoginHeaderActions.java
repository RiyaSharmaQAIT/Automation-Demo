package com.qait.sakurai.automation.keywords;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

import static com.qait.automation.utils.YamlReader.getData;
import com.qait.automation.getpageobjects.GetPage;

public class LoginHeaderActions extends GetPage {

	static String pageName = "LoginHeader";

	public LoginHeaderActions(WebDriver driver) {
		super(driver, pageName);
	}

	public void verifyUserNameIsDisplayed(String usersName) {
		assertThat("FAILED: user's name is not right in the Login Header",
				element("drpdwn_username").getText(), startsWith(usersName));
		logMessage("PASSED: Verfied correct user name '" + usersName
				+ "'is displayed in the login header");
	}

	public void userSignsOutOfTheApplication() {
		hover(element("drpdwn_username"));
		element("drpdwn_username").click();
		logMessage("[Info]: User clicks on the username in Login Header.");
		executeJavascript("document.getElementsByClassName('dropdown-menu')[0].style='display:block'");
		logMessage(element("lnk_signOut").getText());
		driver.get(getData("app_url") + "#/login/signin");
	}
}
