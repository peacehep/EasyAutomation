package util;

import org.junit.Test;

import static com.easy.automation.util.GlobalSetting.*;

public class GlobalSettingTest {
    @Test
    public void test(){
        System.out.println("CURRENT_PLATFORM = "+CURRENT_PLATFORM);
        System.out.println("CURRENT_PLATFORM_VERSION = "+CURRENT_PLATFORM_VERSION);
        System.out.println("CURRENT_PLATFORM_ARCH = "+CURRENT_PLATFORM_ARCH);
        System.out.println("CHROME_DRIVER_PATH = "+CHROME_DRIVER_PATH);
        System.out.println("BASE_URL = "+BASE_URL);
        System.out.println("ELEMENT_TIMEOUT = "+ELEMENT_TIMEOUT);
        System.out.println("ELEMENT_SLEEP_TIME = "+ELEMENT_SLEEP_TIME);
        System.out.println("ELEMENT_LONG_SLEEP_TIME = "+ELEMENT_LONG_SLEEP_TIME);



    }
}
