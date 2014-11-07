/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;
import static com.qait.automation.utils.DataReadWrite.getProperty;

import com.qait.automation.utils.CustomFunctions;
import com.qait.sakurai.automation.keywords.CreateClassPageActions;
import com.qait.sakurai.automation.keywords.LandingPageActions;
import com.qait.sakurai.automation.keywords.LoginHeaderActions;
import com.qait.sakurai.automation.keywords.LoginPageActions;
import com.qait.sakurai.automation.keywords.MyClassesPageActions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class TestSessionInitiator {

	protected WebDriver driver;
	private WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	public CustomFunctions customFunctions;

	/**
	 * Initiating the page objects
	 */
	public LandingPageActions landingpage;
	public LoginPageActions loginpage;
	public MyClassesPageActions myclasspage;
	public LoginHeaderActions loginheader;
	public CreateClassPageActions createclass;

	private void _initPage() {
		landingpage = new LandingPageActions(driver);
		loginpage = new LoginPageActions(driver);
		myclasspage = new MyClassesPageActions(driver);
		loginheader = new LoginHeaderActions(driver);
		createclass = new CreateClassPageActions(driver);
	}

	/**
	 * Page object Initiation done
	 */

	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
		testInitiator();
	}

	private void testInitiator() {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(
						Integer.parseInt(_getSessionConfig().get("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver",
				"seleniumserverhost", "timeout", "driverpath" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("app_url"));
	}

	/**
	 * Launches the application found at provided URL
	 * 
	 * @param applicationpath
	 */
	public void launchApplication(String applicationpath) {
		Reporter.log("The application url is :- " + applicationpath, true);
		Reporter.log(
				"The test browser is :- " + _getSessionConfig().get("browser"),
				true);
		driver.get(applicationpath);
	}

	/**
	 * This keyword is used to launch applications that use NTLM authentication
	 * to validate user
	 * 
	 * @param applicationpath
	 * @param authUser
	 * @param authPed
	 */
	public void launchApplication(String applicationpath, String authUser,
			String authPwd) {
		applicationpath = applicationpath.replace(
				"http://",
				"http://" + authUser.replaceAll("@", "%40") + ":"
						+ authPwd.replaceAll("@", "%40") + "@");
		Reporter.log("The application url is :- " + applicationpath, true);
		Reporter.log(
				"The test browser is :- " + _getSessionConfig().get("browser"),
				true);
		driver.get(applicationpath);
	}

	public void getURL(String url) {
		driver.manage().deleteAllCookies();
		driver.get(url);
	}

	/**
	 * The Test Session including the browser is closed
	 */
	public void closeBrowserSession() {
		driver.quit();
	}
}