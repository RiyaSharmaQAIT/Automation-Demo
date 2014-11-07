package com.qait.sakurai.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class MyClassesPageActions extends GetPage {

	static String pageName = "MyClassesPage";
	private String pageUrlPart = "instructor/class";
	
	public MyClassesPageActions(WebDriver driver) {
		super(driver, pageName);
	}

	public void verifyUserIsOnMyClassesPage(){
		isElementDisplayed("txt_pageHeader");
		verifyPageUrlContains(this.pageUrlPart);
		logMessage("[Info]: Verified User is on My Classes Page!!! ");
	}
	
	public void instructorStartsCreatingAClass(){
		element("lnk_addClass").click();
		logMessage("[INFO]: Instructor starts creating class");
	}
}
