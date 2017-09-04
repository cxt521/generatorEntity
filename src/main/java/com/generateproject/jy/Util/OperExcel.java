package com.generateproject.jy.Util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @file:OperExcel.java
 * @author: 张春杰、刘海洋
 * @date: 2017-09-04-14:47
 * @version: 1.0
 * @projectName: generatorEntity
 */

public class OperExcel {


    /**
     *方法名：OperExcel
     *时间:2017/9/4
     *功能描述:读取excel内容
     **/
    public static List<List<String>> readXlsxContent(String path) {

        List<List<String>> result = new ArrayList<List<String>>();

        try {
            InputStream is = new FileInputStream(path);
            // XSSFWorkbook 表示整个excel
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            // 循环每一页，并处理当前页
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet sheet = workbook.getSheetAt(numSheet); // 表示每一页

                if (sheet == null) {
                    continue; // 结束本次循环　
                }

                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    XSSFRow row = sheet.getRow(rowNum); // 表示每一行

                    int minCellNum = row.getFirstCellNum();
                    int maxCellNum = row.getLastCellNum();

                    List<String> rowList = new ArrayList<String>();

                    //　遍历行，获取每个cell元素
                    for (int cellIndex = minCellNum; cellIndex < maxCellNum; cellIndex++) {
                        XSSFCell cell = row.getCell(cellIndex);

                        if (cell == null) {
                            continue;
                        }
                        rowList.add(cell.toString());
                    }
                    result.add(rowList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);

        return result;
    }
}
