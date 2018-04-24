package com.easy.automation.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.easy.automation.util.GlobalSetting.TEST_DATA_PATH;

public class BaseExcelData implements IData{

    public Object[][] getData(String moduleName, String testcaseName) {
        return getData(moduleName, testcaseName, 0);
    }

    public Object[][] getData(String moduleName, String testcaseName, int rowNum) {
        Object[][] data;

        data = addList(moduleName,testcaseName,rowNum);

        return data;
    }

    private Object[][] addList(String moduleName, String testcaseName, int rowNum) {

        ArrayList<String> list = new ArrayList();
        Object[][] data = null;

        try {
            InputStream is = new FileInputStream(TEST_DATA_PATH+ testcaseName+ ".xlsx");
            XSSFWorkbook excelWBook = new XSSFWorkbook(is);
            XSSFSheet excelWSheet = excelWBook.getSheet(moduleName);

            int rowTotal = excelWSheet.getLastRowNum();
            int colTotal = excelWSheet.getRow(0).getPhysicalNumberOfCells();

            if(excelWSheet !=null){
                for(int i=1;i<=rowTotal;i++){
                    for(int j=0;j<colTotal;j++){
                        XSSFRow row = excelWSheet.getRow(i);
                        XSSFCell cell = row.getCell(j);
                        DataFormatter formatter = new DataFormatter();
                        String text = formatter.formatCellValue(cell);
                        list.add(text);
                    }
                }
            }

            if(rowNum==0){
                data = new Object[rowTotal][colTotal];
                int k=0;
                for(int i=0;i<rowTotal;i++){
                    for(int j=0;j<colTotal;j++){
                        data[i][j]=list.get(k++);
                    }
                }
            }else{
                int k=0;
                data = new Object[rowNum][colTotal];
                for(int i=0;i<rowNum;i++){
                    for(int j=0;j<colTotal;j++)
                    {
                        data[i][j]=list.get(k++);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;


    }


}
