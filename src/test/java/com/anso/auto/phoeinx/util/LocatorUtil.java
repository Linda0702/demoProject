package com.anso.auto.phoeinx.util;

import com.anso.auto.phoeinx.base.Locator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;

public class LocatorUtil {
    /**
     * 所有UI信息都在一个xml中间
     * -->解析出来
     */
    private  static HashMap<String,HashMap<String,Locator>> pagesMap = new HashMap<String,HashMap<String,Locator>>();
    static {
        loadUiInfo();
    }
    public static HashMap<String,Locator>getPageMapByPageName(String name){
        return pagesMap.get(name);
    }
    public static Locator getLocator(String pageName,String desc){
        return pagesMap.get(pageName).get(desc);
    }
    public static void loadUiInfo(){
        SAXReader reader = new SAXReader();
        try {
            //得到文档
            Document document=reader.read(LocatorUtil.class.getResourceAsStream("/Locators/locators.xml"));
            //得到root标签
            Element rootElement = document.getRootElement();
            //得到所有子标签,page -->List
            List<Element> pageElements = rootElement.elements();
            //遍历page
            for(Element page:pageElements){
                String pageName = page.attributeValue("name");//得到page名称
                //得到当前元素的所有信息
                List<Element> locatorElements = page.elements("locator");
                //map保存当前遍历页面的定位信息,保存的这些map还应该用map容器装起来
                HashMap<String,Locator> locatorHashMap = new HashMap<String,Locator>();
                for(Element locatorElement : locatorElements){
                    //得到定位方式、值、desc
                    String by=locatorElement.attributeValue("by");
                    String value =locatorElement.attributeValue("value");
                    String desc=locatorElement.attributeValue("desc");
                    //解析出来后包装到另一个数据载体-->方便Java代码进行操作-->对象
                    Locator locator = new Locator(by,value,desc);
                    locatorHashMap.put(desc,locator);
                }
                pagesMap.put(pageName,locatorHashMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
