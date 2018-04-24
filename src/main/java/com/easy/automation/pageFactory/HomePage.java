package com.easy.automation.pageFactory;

import com.easy.automation.uimap.HomePageUI;
import com.easy.automation.util.SeleniumUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    /**
     * ALl elements are identified by @FindBy annotation
     */

    WebDriver driver;
    Logger log;
    SeleniumUtil seleniumUtil;

    @FindBy(xpath = HomePageUI.xpath_login_link)
    WebElement loginLink;

    @FindBy(xpath = HomePageUI.xpath_notification_icon)
    WebElement notificationIcon;


    public HomePage(WebDriver driver,Logger log,SeleniumUtil seleniumUtil){
        this.driver = driver;
        this.log = log;
        PageFactory.initElements(driver,this);
        this.seleniumUtil = seleniumUtil;
    }

    //click login button
    public void clickLoginLink(){
        log.info("点击登录链接");
        loginLink.click();
    }

    //to verify whether login successfully
    public boolean isLoginSuccess(){
        log.info("校验是否登录成功");
        return seleniumUtil.isElementExisted(HomePageUI.xpath_notification_icon);
    }
}
