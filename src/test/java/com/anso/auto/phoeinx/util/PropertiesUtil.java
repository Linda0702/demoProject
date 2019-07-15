package com.anso.auto.phoeinx.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    /**
     *属性文件工具类
     * @author Linda
     */
    static Properties urlPropperties = new Properties();
    static Properties jdbcProperties = new Properties();
    static {
        loadUrlProperties();
    }
    private static void loadUrlProperties(){
        try {
            urlPropperties.load(PropertiesUtil.class.getResourceAsStream("/url.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void loadJDBCProperties(){
        //TODO
    }
    /**
     * 根据指定的key获得相应的url
     * @param key
     * @return
     */
    public static String getUrl(String key){
        return urlPropperties.getProperty(key);
    }
    public static void getJDBC(){
        //TODO
    }
}
