package com.easy.automation.util;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static com.easy.automation.util.GlobalSetting.*;
import static com.easy.automation.util.GlobalSetting.BASE_URL;

public class CommonTest {
    protected WebDriver driver;
    protected Logger log = LogManager.getLogger(this.getClass());
    protected SeleniumUtil seleUtil;
    private TestCaseSetup testCaseSetup;

    @BeforeClass(alwaysRun = true)
    public void setup(){
        testCaseSetup = new TestCaseSetup();
        driver = testCaseSetup.iniDriver();
        seleUtil = new SeleniumUtil(driver,log);

        log.info("###########################################################");
        log.info("当前的操作系统是：[" + CURRENT_PLATFORM + " " + CURRENT_PLATFORM_ARCH + "]");
        log.info("启动测试浏览器为：[" + TEST_BROWSER + "]");
        log.info("测试开始");

        driver.manage().window().maximize();
        seleUtil.keepOneWindow();
        driver.manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        log.info("输入网址："+BASE_URL);
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result){
        if(!result.isSuccess())
        {
            new FailedTestCaseReport().catchExceptions(result,driver);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
