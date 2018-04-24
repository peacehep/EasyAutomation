package util;

import com.easy.automation.util.BaseExcelData;
import org.junit.Test;


public class BaseExcelDataTest {
    @Test
    public void test1(){
        BaseExcelData data = new BaseExcelData();
        Object[][] dataArr = data.getData("test1","TC001",1);

        for(int i=0;i<dataArr.length;i++){
            for(int j=0;j<dataArr[i].length;j++)
                System.out.println(dataArr[i][j]);
        }
    }

    @Test
    public void test2(){
        BaseExcelData data = new BaseExcelData();
        Object[][] dataArr = data.getData("testdata","testdata1");

        for(int i=0;i<dataArr.length;i++){
            for(int j=0;j<dataArr[i].length;j++)
                System.out.println(dataArr[i][j]);
        }
    }
}
