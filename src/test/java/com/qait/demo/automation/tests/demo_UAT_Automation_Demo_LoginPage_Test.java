package com.qait.demo.automation.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import com.qait.automation.TestSessionInitiator;
import org.testng.ITestResult;

/**
 *
 * @author prashantshukla
 *
 */
public class demo_UAT_Automation_Demo_LoginPage_Test {

TestSessionInitiator test;

@BeforeClass
public void start_test_session() {
        test = new TestSessionInitiator("demo_UAT_Automation_Demo_LoginPage_Test");
}

@Test
public void Test01_Launch_demo_Application() {
        test.launchApplication();
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
