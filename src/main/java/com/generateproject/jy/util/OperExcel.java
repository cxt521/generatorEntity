package com.generateproject.jy.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.security.krb5.internal.PAData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            // 自动判断excel的版本类型
            Workbook book = WorkbookFactory.create(new FileInputStream(path));

            // 遍历所有的excel中的sheet
            for (int i = 0; i < book.getNumberOfSheets(); i++) {

                Sheet sheet = book.getSheetAt(i);

                // 读取excel从第１行开始，开始坐标为０
                for (int j = 1; j < sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j);
                    List<String> rowList = new ArrayList<>();

                    for (int x = 0; x < row.getLastCellNum(); x++) {
                        Cell cell = row.getCell(x);
                        rowList.add(cell.getStringCellValue());
                    }
                    result.add(rowList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回excel内容
        return result;
    }


    /**
     *  读取excel第一行标题
     * @param path　文件路径
     * @return
     */
    public static List<String> readXlsxTitle(String path) {
        // 新建一个List用于存放excel的第一行(标题)
        List<String> list = new ArrayList<>();
        try {
            Workbook book = WorkbookFactory.create(new FileInputStream(path));

            // 获取sheet
            Sheet sheet =  book.getSheetAt(0);
            // 获取行数
            Row row = sheet.getRow(0);
            // 遍历第一行的单元格内容信息
            String result = null;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                result = row.getCell(i).getStringCellValue();
                list.add(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        // 返回excel第一行标题内容
        return list;
    }
}
