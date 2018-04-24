package com.easy.automation.util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


import static com.easy.automation.util.GlobalSetting.*;

public class TestCaseSetup {
    WebDriver driver;

    public TestCaseSetup(){
    }

    public WebDriver iniDriver(){

        if(TEST_BROWSER.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type", "--ignore-certificate-errors");
            System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
            driver = new ChromeDriver(options);
        }else if(TEST_BROWSER.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver",IE_DRIVER_PATH);
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
}
