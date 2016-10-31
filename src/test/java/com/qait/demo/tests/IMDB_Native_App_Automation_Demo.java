package com.qait.demo.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;


public class IMDB_Native_App_Automation_Demo{
  
  public WebDriver driver;
  
  @BeforeClass
  public void start_test_session(){
    
    DesiredCapabilities cap = new DesiredCapabilities();
    
    String appiumServerHostUrl = "http://127.0.0.1:4723/wd/hub";
    URL appiumServerHost = null;
    try {
        appiumServerHost = new URL(appiumServerHostUrl);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    
    cap.setCapability("appPackage", "com.imdb.mobile");
    cap.setCapability("appActivity", ".HomeActivity");
    
    driver = new RemoteWebDriver(appiumServerHost, cap);
  }
  
  @Test
  public void test(){
    
  }
  
  @AfterClass
  public void stop_test_session(){
    
  }
  
  
  
}