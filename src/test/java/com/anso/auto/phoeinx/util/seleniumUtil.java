package com.anso.auto.phoeinx.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * selenium工具类
 * @author Linda
 * @date 15/04/2019
 * @desc
 * @email
 */
public class seleniumUtil {
    /**
     *根据配置创建webdriver对象
     * @param browserType
     * @param driverPath
     * @param seleVersion
     * @param browserEXEPath
     * @return
     */
    public static final String IE_BROWSER_NAME = "ie";
    public static final String FIREFOX_BROWSER_NAME = "firefox";
    public static final String CHROME_BROWSER_NAME = "chrome";
    public static WebDriver getWebDriver(String browserType,String driverPath,String seleVersion,String browserEXEPath){
        if(IE_BROWSER_NAME.equalsIgnoreCase(browserType)){
            //TODO IE有待完成，没下载驱动
            System.setProperty("webdriver.ie.driver",driverPath);//webdriver.ie.driver需要用service常量替换
            DesiredCapabilities capabilities = new DesiredCapabilities();
        }else if(CHROME_BROWSER_NAME.equalsIgnoreCase(browserType)){
            return getChromeDriver(driverPath);
        }
        else if(FIREFOX_BROWSER_NAME.equalsIgnoreCase(browserType)){
            return getFirefoxDriver(driverPath, seleVersion, browserEXEPath);
        }
        return null;
    }

    private static WebDriver getFirefoxDriver(String driverPath, String seleVersion, String browserEXEPath) {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY,browserEXEPath);//第二个参数为火狐执行文件的路径
        if("3.x".equalsIgnoreCase(seleVersion)){
            //TODO
            System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,driverPath);
        }
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver(String driverPath) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,driverPath);//防止webdriver.chrome.driver名字改变，而采用property名
        return new ChromeDriver();
    }
}
