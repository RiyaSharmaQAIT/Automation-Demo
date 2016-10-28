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
        test.launchApplication();
        test.landingpage.verifyUserIsOnLandingPage();
}



@AfterMethod
public void take_screenshot_on_failure(ITestResult result) {
        test.takescreenshot.takeScreenShotOnException(result);
}


@AfterClass(alwaysRun = true)
public void stop_test_session() {
        test.closeBrowserSession();
}
}
