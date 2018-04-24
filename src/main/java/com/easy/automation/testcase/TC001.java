package com.easy.automation.testcase;

import com.easy.automation.util.CommonTest;
import com.easy.automation.util.ExcelDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001 extends CommonTest {
    @Test(dataProvider = "default"
            ,dataProviderClass = ExcelDataProvider.class
            ,description = "测试用例-方法1"
            ,priority = 1)
    public void test1(String a,String b){
        log.info(a+b);
        Assert.assertEquals(a,b);
    }

    @Test(dependsOnMethods = "test1"
            , description = "测试用例-方法2"
            ,priority = 2,alwaysRun = true)
    public void test2(){
        seleUtil.switchToNextWindow();
    }
}
