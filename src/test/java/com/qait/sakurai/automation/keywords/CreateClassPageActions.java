package com.qait.sakurai.automation.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class CreateClassPageActions extends GetPage {

	static String pageName = "CreateClassPage";

	public CreateClassPageActions(WebDriver driver) {
		super(driver, pageName);
	}

	public void verifyUserIsOnCreateClassPage() {
		verifyPageTitleExact();
		isElementDisplayed("txt_createClassHeading");
		verifyPageUrlContains("instructor/addEditClass/0");
		logMessage("Verified that the user is on the '" + pageName + "'!!!");
	}

	public void instructorSelectsProduct(String product) {
		element("btn_selectProduct").click();
		element("inp_selectProduct").click();
		element("inp_selectProduct").clear();
		element("inp_selectProduct").sendKeys(product);
		element("inp_selectProduct").sendKeys(Keys.ENTER);
		logMessage("[Info]: Instructor has selected the product '" + product +"'");
	}

	public void instructorInputsClassName(String classname) {
		element("inp_classname").click();
		element("inp_classname").clear();
		element("inp_classname").sendKeys(classname);
		logMessage("[Info]: The Instructor has chosen the classname as '" + classname + "'");
	}

	public void instructorInputsTerm(String term) {
		element("inp_term").click();
		element("inp_term").clear();
		element("inp_term").sendKeys(term);
	}

	public void instructorEntersStartAndEndDate(String startdate, String enddate) {
		element("cal_startDate").click();
		element("txt_date", startdate).click();
		element("cal_endDate").click();
		element("txt_date", enddate).click();
	}

	public void instructorSelectsSchool(String school) {
		element("btn_searchSchool").click();
		element("inp_searchSchool").sendKeys(school);
		waitforsearchingtodisaapear();
		element("inp_searchSchool").sendKeys(Keys.ENTER);
	}

	private void waitforsearchingtodisaapear() {
		try {
			System.out.println("Waiting for School Search to complete... ");
			int counter = 0;
			while (driver.findElement(By.className("select2-searching"))
					.isDisplayed()) {
				try {
					counter = counter +1;
					Thread.sleep(1000);
					logMessage("...");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void instructorSubmitsCreateClassForm() {
		element("btn_submit").click();
	}
}
