package com.generateproject.jy.controller;

import com.generateproject.jy.Util.OperExcel;

import java.util.List;

/**
 * @file:CreateJava.java
 * @author: 张春杰、刘海洋
 * @date: 2017-09-04-16:20
 * @version: 1.0
 * @projectName: generatorEntity
 */

public class GeneratorEntity {

    /**
     *方法名：GeneratorEntity
     *时间:2017/9/4
     *功能描述:读取excel内容
     **/
    private List<List<String>> readExcelContent(String path){
        return OperExcel.readXlsxContent(path);
    }

    /**
     *方法名：GeneratorEntity
     *时间:2017/9/4
     *功能描述：excel数据流的准备及验证操作
     **/
    private Boolean BeforeDoExcute(){
        return true;
    }

    /**
     *方法名：GeneratorEntity
     *时间:2017/9/4
     *功能描述:操作主函数
     **/
    public void doExcute(){
        List<List<String>> lists = readExcelContent("");
    }
}
