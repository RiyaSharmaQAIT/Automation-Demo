package com.qait.sakurai.automation.tests;

import static com.qait.automation.utils.YamlReader.getData;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

/**
 *
 * @author prashantshukla
 *
 */
public class Sakurai_UAT_Automation_Demo_CreateClass_Test {

	TestSessionInitiator test;
	String appUrl = null;

	@BeforeClass
	public void start_test_session() {
		test = new TestSessionInitiator();
		appUrl = getData("app_url");
	}

	@Test
	public void Test01_Launch_Sakurai_Application() {
		test.launchApplication(appUrl, getData("users.http_auth.username"),
				getData("users.http_auth.password"));
		test.landingpage.verifyUserIsOnLandingPage();
	}

	@Test
	public void Test02_Login_To_The_Application() {
		test.landingpage.clickSignInMenuLink();
		test.loginpage.verifyUserIsOnLoginPage();
		test.loginpage.selectSubject(getData("users.subject"));
		test.loginpage.enterUserCredentials(
				getData("users.instructor.username"),
				getData("users.instructor.password"));
		test.myclasspage.verifyUserIsOnMyClassesPage();
	}

	@Test
	public void Test03_Instructor_Is_Able_To_Open_Create_Class_Page(){
		test.myclasspage.instructorStartsCreatingAClass();
		test.createclass.verifyUserIsOnCreateClassPage();
	}
	
	@Test
	public void Test04_Instructor_Creates_New_Class(){
		test.createclass.instructorSelectsProduct(getData("class.product"));
		test.createclass.instructorInputsClassName(getData("class.name"));
		test.createclass.instructorInputsTerm(getData("class.term"));
		test.createclass.instructorEntersStartAndEndDate(getData("class.date.start"), getData("class.date.end"));
		test.createclass.instructorSelectsSchool(getData("class.school"));
		test.createclass.instructorSubmitsCreateClassForm();
	}
	
	@Test
	public void Test04_User_Logs_Out_Of_the_Application(){
		test.loginheader.verifyUserNameIsDisplayed(getData("users.instructor.name"));
		test.loginheader.userSignsOutOfTheApplication();
		test.loginpage.verifyUserIsOnLoginPage();
	}
	
	@AfterClass(alwaysRun = true)
	public void stop_test_session() {
		test.closeBrowserSession();
	}
}
