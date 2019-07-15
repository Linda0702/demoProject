package com.anso.auto.phoeinx.util;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

public class ExcelUtil {
    public static Object[][] readExcel(String excelPath,int sheetIndex){
        //创建一个数组做为解析出来的数据的容器,datas
        Object[][] datas = null;
        try {
            InputStream inp = ExcelUtil.class.getResourceAsStream(excelPath);//用类路径
            Workbook workbook = WorkbookFactory.create(inp);
            Sheet sheet = workbook.getSheetAt(sheetIndex-1);
            int lastRowNum = sheet.getLastRowNum();
            datas = new Object[lastRowNum][];//给datas赋值于一个二维数组，行数为excel的行数-1
            for(int i=1;i<=lastRowNum;i++){
                //循环到某一行
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                Object[] cellObject = new Object[lastCellNum];
                for(int j =0;j<lastCellNum;j++){
                    Cell cell = row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取cell为空时，创建为空白单元格
                    cell.setCellType(CellType.STRING);//设置单元格数据类型为字符串
                    String cellValue=cell.getStringCellValue();
                    cellObject[j]=cellValue;
                }
                datas[i-1]=cellObject; //datas是从下标为0开始赋值的，所以i-1，得到一个下标从0开始就存excel第二行数据的数组
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datas;//返回数据数组

    }

}

