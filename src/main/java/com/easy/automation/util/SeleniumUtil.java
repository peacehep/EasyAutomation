package com.easy.automation.util;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static com.easy.automation.util.GlobalSetting.ELEMENT_LONG_SLEEP_TIME;
import static com.easy.automation.util.GlobalSetting.ELEMENT_SLEEP_TIME;
import static com.easy.automation.util.GlobalSetting.ELEMENT_TIMEOUT;

public class SeleniumUtil {
    Logger log;

    WebDriver driver;
    WebDriverWait wait;

    public SeleniumUtil(WebDriver webDriver,Logger log){
        this.log = log;
        driver = webDriver;
        wait = new WebDriverWait(webDriver,ELEMENT_TIMEOUT);
    }

    /**
     * 切换到下一个窗口
     *
     * @return 当前窗口句柄
     */

    public String switchToNextWindow(){
        Set<String> allWindowHandles = driver.getWindowHandles();
        String curWindowHandle = driver.getWindowHandle();
        log.info("当前窗口句柄: "+curWindowHandle);
        log.info("窗口数量："+allWindowHandles.size());

        for(String s:allWindowHandles){
            if(s.equals(curWindowHandle)){
                log.info("当前窗口，继续");
                continue;
            }else {
                log.info("跳转到新窗口："+s);
                driver.switchTo().window(s);
            }
        }

        return curWindowHandle;

    }

    /**
     * 切换到指定窗口
     *
     * @param windowHandle
     */

    public void switchToPreWindow(String windowHandle){
        log.info("切换到指定窗口："+ windowHandle);
        driver.switchTo().window(windowHandle);
    }


    public void alertClickOK(){
        Alert alert = driver.switchTo().alert();
        log.info("点击弹出框确定按钮");
        alert.accept();

    }

    /**
     * 点击弹出框取消按钮
     */

    public void alertClickCancel() {

        Alert alert = driver.switchTo().alert();

        log.info("点击弹出框取消按钮");
        alert.dismiss();

    }

    /**
     * 跳入frame
     *
     * @param frameName
     */

    public void switchToFrame(String frameName) {
        log.info("切换到 " + frameName + " frame中");
        driver.switchTo().frame(frameName);
    }

    /**
     * 跳入frame
     *
     * @param frameWebElement
     */

    public void switchToFrame(WebElement frameWebElement) {
        log.info("切换到 frame 中");
        driver.switchTo().frame(frameWebElement);
    }

    /**
     * 跳入frame
     *
     * @param frameId
     */

    public void switchToFrame(int frameId) {
        log.info("切换到第" + frameId + "个frame 中");
        driver.switchTo().frame(frameId);
    }

    /**
     * 跳出frame，返回default Content
     */

    public void switchToDefaultContentFromFrame() {
        log.info("跳出frame");
        driver.switchTo().defaultContent();
    }

    /**
     * 清除输入框的数据
     *
     * @param textInput
     */

    public void clearText(WebElement textInput) {
        log.info("清除数据");
        textInput.click();
        textInput.clear();
    }

    /**
     * 短等待 - 5s
     */

    public void sleep() {
        try {
            log.info("等待" + ELEMENT_SLEEP_TIME + "ms");
            Thread.sleep(ELEMENT_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 长等待 - 10s
     */
    public void longSleep() {
        try {
            log.info("等待" + ELEMENT_LONG_SLEEP_TIME + "ms");

            Thread.sleep(ELEMENT_LONG_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待页面元素出现
     *
     * @param xpath
     */
    public void waitWebElementDisplay(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * 等待搜索结果出现
     *
     * @param searchCondition
     */
    public void waitSearchResult(String searchCondition) {
        String xpath = "//*[text() = '" + searchCondition + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        sleep();
    }

    /**
     * 等待元素消失
     *
     * @param xpath
     */

    public void waitElementInvisible(String xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Selenium 拖拽方法
     *
     * @param from
     * @param to
     */

    public void dragAndDrop(WebElement from, WebElement to) {

        log.info("进行拖拽");
        (new Actions(driver)).dragAndDrop(from, to).perform();
    }

    public String getTitle() {
        String title = driver.getTitle();
        log.info("当前窗口title是： " + title);
        return title;
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        log.info("当前URL是： " + url);
        return url;
    }

    /**
     * 检查指定的xpath是否已经在页面中显示，可用与检查弹出框是否出现。
     *
     * @param xpath
     * @return
     */

    public boolean isElementExisted(String xpath){
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        if(elementList.size()>0)
        {
            log.info("Web element 已存在");
            return true;
        }else{
            log.info("Web element 不存在");
            return false;
        }
    }


    public int getWindowNum(){
        Set<String> windowHandles = driver.getWindowHandles();
        return windowHandles.size();
    }


    public void keepOneWindow(){

        if(getWindowNum()>1){
            Set<String> allWindowHandles = driver.getWindowHandles();
            String curWindowHandle = driver.getWindowHandle();

            for(String s:allWindowHandles){
                if(s.equals(curWindowHandle)){
                    continue;
                }else {
                    driver.switchTo().window(s);
                    driver.close();
                }
            }

            driver.switchTo().window(curWindowHandle);

        }
    }










}
