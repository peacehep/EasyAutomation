package com.easy.automation.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.easy.automation.util.GlobalSetting.SCREENSHOT_PATH;
import static com.easy.automation.util.GlobalSetting.SCREENSHOT_URL;

public class FailedTestCaseReport {

    public void catchExceptions(ITestResult result, WebDriver driver){
        String methodName = result.getName();
        String picName = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date())+methodName;

        String picPath = SCREENSHOT_PATH + picName + ".png";
        String picUrl = SCREENSHOT_URL + picName + ".png";

        try {
            File inFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //创建流文件读入与写出类
            FileInputStream inStream = new FileInputStream(inFile);
            FileOutputStream outStream = new FileOutputStream(picPath);
            //通过available方法取得流的最大字符数
            byte[] inOutb = new byte[inStream.available()];
            inStream.read(inOutb);  //读入流,保存在byte数组
            outStream.write(inOutb);  //写出流,保存在文件newFace.gif中
            inStream.close();
            outStream.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Reporter.setCurrentTestResult(result);
        Reporter.log(new File(picPath).getAbsolutePath());
        Reporter.log("<img src='" + picUrl + "' onclick='window.open(\"" + picUrl + "\")" + "' hight='200' width='200'/>");

    }
}
