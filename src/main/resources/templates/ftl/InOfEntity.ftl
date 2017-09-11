package com.generateproject.jy.entity;
<#if arryClassName??>
import java.util.*;
</#if>
import java.util.Date;
/**
* @author: ly
* @version: 1.0
*/
public class ${className}{

<#list property as p>
    /**
    *${p.propertyDoc}
    */
    private String ${p.propertyNameDown};

</#list>
<#if arryClassName??>
    /**
    *数组列表
    */
    private List<${arryClassName}> info;
</#if>


<#list property as p>
    public String get${p.propertyNameUp}(){
        return ${p.propertyNameDown};
    }

    public void set${p.propertyNameUp}(String ${p.propertyNameDown}){
        this.${p.propertyNameDown}= ${p.propertyNameDown};
    }

</#list>

<#if arryClassName??>
    public List<${arryClassName}> getInfo(){
    return list;
    }

    public void setInfo(List<${arryClassName}> list){
    this.list = list;
    }
</#if>
}