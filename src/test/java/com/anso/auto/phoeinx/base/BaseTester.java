package com.anso.auto.phoeinx.base;

import com.anso.auto.phoeinx.util.LocatorUtil;
import com.anso.auto.phoeinx.util.PropertiesUtil;
import com.anso.auto.phoeinx.util.seleniumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTester {
    public static WebDriver driver;
    private static Logger logger = Logger.getLogger(BaseTester.class);
    @BeforeSuite
    @Parameters(value = {"browserType","driverPath","seleVersion","browserEXEPath"})
    public void beforeSuite(String browserType,String driverPath,String seleVersion,String browserEXEPath){
        driver = seleniumUtil.getWebDriver(browserType,driverPath,seleVersion,browserEXEPath);

    }
    /**
     * 设置等待元素方法,指定等待时间
     */
    public WebElement getElement( final By by,long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {//匿名内部类
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }
    /**
     * 方法重载，默认等待时间2s方法
     * @throws InterruptedException
     */
    public WebElement getElement(final By by){
        //调用getElement方法，默认等待时间2s
        return getElement(by,2);
    }

    /**
     * 设置等待元素方法,指定等待时间
     */
    public List<WebElement> getElements(String keyword, long timeOutInSeconds){
        HashMap<String,Locator> locatorMap = LocatorUtil.getPageMapByPageName(this.getClass().getName());
        Locator locator = locatorMap.get(keyword);
        //定位方式
        String byStr = locator.getBy();
        //定位的值
        String value = locator.getValue();
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        List<WebElement> elements = wait.until(new ExpectedCondition<List>() {
            @Nullable
            @Override
            public List apply(@Nullable WebDriver driver) {
                By by = null;
                try {
                    //拿到字节码对象
                    Class<By> clazz = By.class;
                    //获取方法名:id,name,className...
                    Method byMethod = clazz.getMethod(byStr,String.class);
                    //调用这个by方法
                    by = (By)byMethod.invoke(null,value);//得到的是一个by对象
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    System.out.println("请输入正确方法");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return driver.findElements(by);
            }
        });
        return elements;
    }
    /**
     * @throws InterruptedException
     */
    public List<WebElement> getElements(String keyword){
        //调用getElement方法，默认等待时间2s
        return getElements(keyword,2);
    }

    public WebElement getElement(String keyword,long timeOutInSecond){
        HashMap<String,Locator> locatorMap = LocatorUtil.getPageMapByPageName(this.getClass().getName());
        Locator locator = locatorMap.get(keyword);
        //定位方式
        String byStr = locator.getBy();
        //定位的值
        String value = locator.getValue();
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSecond);
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver driver) {
                By by = null;
                try {
                    //拿到字节码对象
                    Class<By> clazz = By.class;
                    //获取方法名:id,name,className...
                    Method byMethod = clazz.getMethod(byStr,String.class);
                    //调用这个by方法
                    by = (By)byMethod.invoke(null,value);//得到的是一个by对象
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    System.out.println("请输入正确方法");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return driver.findElement(by);
            }
        });
        return element;
    }
    public WebElement getElement(String keyword){
        return getElement(keyword,5);
    }

    /**
     * @param content 要输入的内容
     * @param keyword 元素的关键描述
     * @throws InterruptedException
     */
    public void type(String content,String keyword){
        logger.info("往["+keyword+"]输入内容:["+content+"]");
        getElement(keyword).sendKeys(content);
    }

    /**
     * 单击元素
     * @param keyword 元素的关键字描述
     */
    public void click(String keyword){
        logger.info("点击元素["+keyword+"]");
        getElement(keyword).click();
    }

    /**
     * @param keyword 元素的关键字描述
     * @return 返回元素的文本
     */
    public String getText(String keyword){
        return getElement(keyword).getText();
    }

    /**
     * 到某个页面去，baseUrl拼接后面路由-->某个url的key
     * @param urlKey
     */
    public void to(String urlKey){
        driver.get(PropertiesUtil.getUrl("baseUrl")+PropertiesUtil.getUrl(urlKey));
    }

    /**
     * 向前一页
     */
    public void foward(){
        driver.navigate().forward();
    }

    /**
     * 回退上一页
     */
    public void back(){
        driver.navigate().back();
    }

    /**
     * 刷新
     */
    public void refresh(){
        driver.navigate().refresh();
    }

    /**
     * 最大化窗口
     */
    public void maximize(){
        driver.manage().window().maximize();
    }
    /**
     * @param keyword
     * @param exceptedText
     */
    //assertTextPresent：断言页面元素文本值是否复合预期值
    public void assertTextPresent(String keyword,String exceptedText){
        String actualText = getText(keyword);
        Assert.assertEquals(actualText,exceptedText);
    }
    //assertPartialTextPresent:断言页面元素文本值包含某文本
    public void assertPartialTextPresent(String keyword,String exceptedText){
        String actualText = getText(keyword);
        Assert.assertTrue(actualText.contains(exceptedText));
    }
    //assertElementEditable:断言页面元素是否可编辑
    public void assertElementEditable(String keyword){
        boolean isEabled = getElement(keyword).isEnabled();
        Assert.assertTrue(isEabled);
    }
    //assertElementEditable:断言页面元素是否可编辑
    public void assertElementDisplayed(String keyword){
        boolean isDisplayed = getElement(keyword).isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    public void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterSuite
    public void afterSuite()throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }

}
