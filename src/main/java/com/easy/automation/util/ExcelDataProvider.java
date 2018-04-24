package com.easy.automation.util;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class ExcelDataProvider {

    @DataProvider(name="default")
    public static Object[][] getDataForExl(Method m) {
        String testcaseName = m.getDeclaringClass().getSimpleName();
        return new BaseExcelData().getData(m.getName(),testcaseName);
    }
}
