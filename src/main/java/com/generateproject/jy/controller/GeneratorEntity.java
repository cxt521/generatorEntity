package com.generateproject.jy.controller;

import com.generateproject.jy.util.OperExcel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * @file:CreateJava.java
 * @author: 张春杰、刘海洋
 * @date: 2017-09-04-16:20
 * @version: 1.0
 * @projectName: generatorEntity
 */
@Service
public class GeneratorEntity {

    /**
     *属性：Configuration
     *时间:2017/9/4
     *功能描述:freemarker的config
     **/
    private Configuration _config;

    /**
     *属性：Properties
     *时间:2017/9/4
     *功能描述:获取全局配置文件
     **/
    private Properties properties= loadProperties();

    /**
     *方法名：readExcelContent
     *时间:2017/9/4
     *功能描述:读取excel内容
     **/
    private List<List<String>> readExcelContent(String path){
        return OperExcel.readXlsxContent(path);
    }

    /**
     *方法名：BeforeDoExcute
     *时间:2017/9/4
     *功能描述：excel数据流的准备及验证操作
     **/
    private Boolean BeforeDoExcute(){
        return true;
    }

    /**
     *方法名：doExcute
     *时间:2017/9/4
     *功能描述:操作主函数
     **/
    public void doExcute(){
        if (BeforeDoExcute()){
            List<List<String>> lists = readExcelContent(properties.getProperty("excelpath"));
            init(lists);
        }
        
    }

    /**
     *方法名：
     *时间:2017/9/4
     *功能描述:初始化freemarker
     **/
    private void init(List<List<String>> lists){
        Template temp = null;
        _config = new Configuration();
        if (properties.getProperty("freeMakerbasePath")!=null){
            File loadTemplateFile = new File(properties.getProperty("freeMakerbasePath"));
            try {
                _config.setDirectoryForTemplateLoading(loadTemplateFile);
                Map<String,Object> map = convertMap(lists);
                genateCode(temp,map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *方法名：genateCode
     *时间:2017/9/4
     *功能描述：将数据模型导入模板
     **/
    private void genateCode(Template temp,Map<String,Object> map) throws IOException {
        String dir = loadProperties().getProperty("saveDir");
        if (!new File(dir).exists()){
            new File(dir).mkdirs();
        }
        Writer outOfInside = null;
        Writer outOfExternal = null;
        try {
            String fileName = dir + toUpString(map.get("className").toString()) + ".java";
            if (map.get("array")!=null){
                String arrayFileName = dir + toUpString(map.get("arryClassName").toString()) + ".java";
                outOfInside = new OutputStreamWriter(new FileOutputStream(arrayFileName), "utf-8");
                temp = _config.getTemplate("OutOfEntity.ftl");
                temp.process(map,outOfInside);
            }
            outOfExternal = new OutputStreamWriter(new FileOutputStream(fileName), "utf-8");
            temp = _config.getTemplate("InOfEntity.ftl");
            temp.process(map,outOfExternal);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        } finally {
            assert outOfInside != null;
            outOfInside.close();
            assert outOfExternal != null;
            outOfExternal.close();
        }
    }

    /**
     *方法名：loadProperties
     *时间:2017/9/4
     *功能描述:获取相关配置文件
     **/
    private Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader cl = getClass().getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }

    /**
     *方法名：convertMap
     *时间:2017/9/4
     *功能描述:转化list
     **/
    private Map<String,Object> convertMap(List<List<String>> lists){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> propertyList = new ArrayList<>();
        List<Map<String,Object>> arrayList = new ArrayList<>();
        for (List<String> list:lists){
            map.put("className",toUpString(list.get(1)));
            map.put("arryClassName",toUpString(list.get(1))+list.get(2));
            if (list.get(0).equals("property")){
                Map<String,Object> propertyMap = new HashMap();
                propertyMap.put("propertyNameUp",list.get(3));
                propertyMap.put("propertyNameDown",toDownString(list.get(3)));
                propertyMap.put("propertyDoc",list.get(6)+" "+list.get(4));
                propertyList.add(propertyMap);
            }else {
                Map<String,Object> propertyMap = new HashMap();
                propertyMap.put("arrayNameUp",list.get(3));
                propertyMap.put("array",list.get(2));
                propertyMap.put("arrayNameDown",toDownString(list.get(3)));
                propertyMap.put("arrayDoc",list.get(6)+" "+list.get(4));
                arrayList.add(propertyMap);
            }
        }
        map.put("property",propertyList);
        map.put("array",arrayList);
        return map;
    }

    /**
     *方法名：toUpString
     *时间:2017/9/4
     *功能描述:转化首字母大写
     **/
    public static String toUpString(String className) {
        char[] cs = className.toCharArray();
        if (cs[0] >= 97){
            cs[0] -= 32;
        }
        String ClassName = String.valueOf(cs);
        return ClassName;
    }

    /**
     *方法名：toUpString
     *时间:2017/9/4
     *功能描述:转化首字母小写
     **/
    public static String toDownString(String className) {
        char[] cs = className.toCharArray();
        if (cs[0] < 97){
            cs[0] += 32;
        }
        String ClassName = String.valueOf(cs);
        return ClassName;
    }
}
