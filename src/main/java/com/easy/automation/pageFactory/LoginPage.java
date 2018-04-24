package com.easy.automation.pageFactory;

import com.easy.automation.uimap.LoginPageUI;
import com.easy.automation.util.SeleniumUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    Logger log;
    SeleniumUtil seleniumUtil;

    @FindBy(id = LoginPageUI.id_username_textField)
    WebElement username;

    @FindBy(id = LoginPageUI.id_password_textField)
    WebElement password;

    @FindBy(name = LoginPageUI.name_login_btn)
    WebElement loginBtn;


    public LoginPage(WebDriver driver,Logger log,SeleniumUtil seleniumUtil)
    {
        this.driver = driver;
        this.log=log;
        PageFactory.initElements(driver, this);
        this.seleniumUtil = seleniumUtil;
    }

    public void setUsername(String sUsername){
        log.info("输入用户名："+sUsername);
        username.sendKeys(sUsername);
    }

    public void setPassword(String sPassword){
        log.info("输入密码："+sPassword);
        password.sendKeys(sPassword);
    }

    public void clickLoginBtn(){
        log.info("点击登录按钮");
        loginBtn.click();
    }

    public void login(String sUsername,String sPassword){
        setUsername(sUsername);
        setPassword(sPassword);
        clickLoginBtn();
    }




}
