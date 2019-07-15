package com.anso.auto.phoeinx.base;

/**
 * 原始定位器
 * @author Linda
 * @date 2019/05/11
 * @desc
 * @email
 */
public class Locator {
    private String by;//定位方式
    private String value;//定位值
    private String desc;//描述

    public Locator(String by, String value, String desc) {
        this.by = by;
        this.value = value;
        this.desc = desc;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBy() {
        return by;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
    @Override
    public String toString(){
        return "Locator [by="+by+",value="+value+",desc"+desc+"]";
    }



}
