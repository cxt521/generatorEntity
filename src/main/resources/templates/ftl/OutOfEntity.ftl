package com.generateproject.jy.entity;

import java.util.Date;
/**
* @author: ly
* @version: 1.0
*/
public class ${arryClassName}{
<#list array as a>
    /**
    *${a.arrayDoc}
    */
    private String ${a.arrayNameDown};

</#list>
<#list array as a>

    public String get${a.arrayNameUp}(){
    return ${a.arrayNameDown};
    }

    public void set${a.arrayNameUp}(String ${a.arrayNameDown}){
    this.${a.arrayNameDown}= ${a.arrayNameDown};
    }

</#list>
}