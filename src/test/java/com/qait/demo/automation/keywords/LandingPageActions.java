package com.qait.demo.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class LandingPageActions extends GetPage {

	public LandingPageActions(WebDriver driver) {
		super(driver, "LandingPage");
	}

	public void verifyUserIsOnLandingPage() {
		verifyPageTitleExact();
		logMessage("Verified that the user is on the Login Page!!!");
	}
	
	public void clickSignInMenuLink() {
		element("lnk_SignIn").click();
	}
	
	public void clickMenuLink(String linktext){
		element("lnk_Menu", linktext).click();
	}



}