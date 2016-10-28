package com.qait.demo.automation.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qait.automation.utils.YamlReader.getData;

import com.qait.automation.TestSessionInitiator;

/**
 *
 * @author prashantshukla
 *
 */
public class demo_UAT_Automation_Demo_LoginPage_Test {

	TestSessionInitiator test;
	String appUrl = null;

	@BeforeClass
	public void start_test_session() {
		test = new TestSessionInitiator();
		appUrl = getData("app_url");
	}

	@Test
	public void Test01_Launch_demo_Application() {
		test.launchApplication(appUrl, getData("users.http_auth.username"),
				getData("users.http_auth.password"));
		test.landingpage.verifyUserIsOnLandingPage();
	}

	@Test
	public void Test02_Select_Subject_On_LoginPage() {
		test.landingpage.clickSignInMenuLink();
		test.loginpage.verifyUserIsOnLoginPage();
		test.loginpage.selectSubject(getData("users.subject"));
	}

	@Test
	public void Test03_Blank_UserName_Error_Message() {
		test.loginpage.enterUserCredentials("",
				getData("users.instructor.password"));
		test.loginpage.verifyLoginErrorMessage("txt_usernameError",
				"Please enter your email address.");
	}

	@Test
	public void Test04_Blank_Password_Error_Message() {
		test.loginpage.enterUserCredentials(
				getData("users.instructor.username"), "");
		test.loginpage.verifyLoginErrorMessage("txt_passwordError",
				"Please enter your password.");
	}

	@Test
	public void Test05_Blank_UserName_And_Password_Error_Message() {
		test.loginpage.enterUserCredentials("", "");
		test.loginpage.verifyLoginErrorMessage("txt_usernameError",
				"Please enter your email address.");
		test.loginpage.verifyLoginErrorMessage("txt_passwordError",
				"Please enter your password.");
	}

	@Test
	public void Test06_Invalid_UserName_Error_Message() {
		test.loginpage.enterUserCredentials("invalid.email.address", "password");
		test.loginpage.verifyLoginErrorMessage("txt_usernameError",
				"Please enter a valid email address.");
	}

	@Test
	public void Test08_Wrong_Password_Error_Message() {
		test.loginpage.enterUserCredentials(getData("users.instructor.username"), "wrongPassword");
		test.loginpage.verifyLoginErrorMessage("txt_invalidUserError",
				"Invalid username or password. Please try again.");
	}
	
	@Test
	public void Test09_Login_To_The_Application_With_Right_User_Credentials() {
		test.loginpage.enterUserCredentials(
				getData("users.instructor.username"),
				getData("users.instructor.password"));
		test.myclasspage.verifyUserIsOnMyClassesPage();
		test.loginheader.verifyUserNameIsDisplayed(getData("users.instructor.name"));
	}
	
	@Test
	public void Test10_User_Is_Able_To_LogOut_Of_The_Application(){
		test.loginheader.userSignsOutOfTheApplication();
		test.loginpage.verifyUserIsOnLoginPage();
	}

	@AfterClass(alwaysRun = true)
	public void stop_test_session() {
		test.closeBrowserSession();
	}
}
