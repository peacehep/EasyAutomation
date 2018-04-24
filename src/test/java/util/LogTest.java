package util;

import org.junit.Test;

public class LogTest extends CommonTest{

    @Test
    public void test1(){
        log.info("info-info");
        log.info("info-info");
        test2();
    }

    public void test2(){

        log.info("info-test2");
        log.info("info-test2");
        log.info("info-test2");
        log.info("info-test2");
    }
}
