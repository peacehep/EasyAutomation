package com.easy.automation.util;

import java.io.IOException;
import java.util.Properties;

public class GlobalSetting {

    public static final Properties prop = getProperties();
    // 系统相关参数
    public static final String CURRENT_PLATFORM = System.getProperty("os.name");
    public static final String CURRENT_PLATFORM_VERSION = System.getProperty("os.version");
    public static final String CURRENT_PLATFORM_ARCH = System.getProperty("os.arch");

    public static final String CHROME_DRIVER_PATH = prop.getProperty("chromeDriverPath");
    public static final String IE_DRIVER_PATH = prop.getProperty("IEDriverPath");


    public static final String BASE_URL = prop.getProperty("baseURL");
    public static final String TEST_DATA_PATH = prop.getProperty("testDataPath");
    public static final String SCREENSHOT_PATH = prop.getProperty("screenshotPath");
    public static final String SCREENSHOT_URL = prop.getProperty("screenshotUrl");





    public static final int ELEMENT_TIMEOUT = Integer.parseInt(prop.getProperty("timeout"));
    public static final int ELEMENT_SLEEP_TIME = Integer.parseInt(prop.getProperty("sleepTime"));
    public static final int ELEMENT_LONG_SLEEP_TIME = Integer.parseInt(prop.getProperty("longSleepTime"));

    // 浏览器
    public static final String TEST_BROWSER = prop.getProperty("browser");



    public static Properties getProperties(){
        Properties prop = new Properties();

        try {
            prop.load(GlobalSetting.class.getClassLoader().getResourceAsStream("prop.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }
}
