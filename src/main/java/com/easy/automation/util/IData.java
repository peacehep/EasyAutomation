package com.easy.automation.util;

public interface IData {
    Object[][] getData(String testcaseName,String dataFile);
    Object[][] getData(String testcaseName,String dataFile,int rowNum);
}
