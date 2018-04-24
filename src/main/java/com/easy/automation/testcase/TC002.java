package com.easy.automation.testcase;

import com.easy.automation.pageFactory.HomePage;
import com.easy.automation.pageFactory.LoginPage;
import com.easy.automation.util.CommonTest;
import com.easy.automation.util.ExcelDataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TC002 extends CommonTest {

    // Page List
    HomePage homePageObj;
    LoginPage loginPageObj;

    @Test(priority = 1
            ,description = "TesterHome主页"
            ,dataProvider = "default"
            ,dataProviderClass = ExcelDataProvider.class)
    public void navigateToLoginPage(String expURL){
        homePageObj = new HomePage(driver,log,seleUtil);
        homePageObj.clickLoginLink();

        String url = seleUtil.getCurrentUrl();
        assertThat(url).isEqualTo(expURL);
    }

    @Test(dependsOnMethods = "navigateToLoginPage"
            ,priority = 2
            ,description = "登录"
            ,dataProvider = "default"
            ,dataProviderClass = ExcelDataProvider.class)
    public void login(String sUsername,String sPassword){
        loginPageObj = new LoginPage(driver,log,seleUtil);
        loginPageObj.login(sUsername,sPassword);
        boolean isLoginSuccess = homePageObj.isLoginSuccess();
        assertThat(isLoginSuccess).isEqualTo(true);
    }
}
