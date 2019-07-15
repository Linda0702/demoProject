package com.anso.auto.phoeinx.util;

import com.anso.auto.phoeinx.base.BaseTester;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreeshotUtil {
    public static File takeSreenshot(String screenshotDir){
        //拿到driver
        WebDriver driver = BaseTester.driver;
        //强转换为截图接口类型
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //拿到截图文件对象
        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //获得毫秒值
        long time = new Date().getTime();
        String fileName = time + ".jpg";
        //创建一个文件对象：screenshotDir：截图目录
        File destFile = new File(screenshotDir + File.separator+fileName);
        try {
            FileUtils.copyFile(screenshotFile,destFile);
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
