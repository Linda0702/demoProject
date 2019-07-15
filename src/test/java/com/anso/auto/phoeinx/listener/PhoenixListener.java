package com.anso.auto.phoeinx.listener;

import com.anso.auto.phoeinx.util.PropertiesUtil;
import com.anso.auto.phoeinx.util.ScreeshotUtil;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;

/**
 * testng-->设计报表
 * @author Linda
 * 添加失败截图
 * 监听器是继承了testng的监听器接口，所以当测试用例执行的时候，会调用复写testng监听器的方法
 */
public class PhoenixListener extends TestListenerAdapter {
    //重写父类方法，当测试失败时进行截图，当测试成功时，没有调用复写方法，不会截图
    @Override
    public void onTestFailure(ITestResult tr){
        super.onTestFailure(tr);
        //得到driver
        //获得输出目录
        String outputDirectory = tr.getTestContext().getOutputDirectory();
        //截取到最后一个\的索引位置去
        String surefireDir = outputDirectory.substring(0,outputDirectory.lastIndexOf("\\"));
        //获得当前test的名称
        String testName = tr.getTestContext().getCurrentXmlTest().getName();
        //保存截图目录
        String screenshotDir = surefireDir+ File.separator+"screenshot"+File.separator+testName;
        //得到截图的目标文件
        File screenshotFile = ScreeshotUtil.takeSreenshot(screenshotDir);//测试失败时截图，成功时不会调用该方法，不会截图
        //得到截图文件的绝对路径
        String absolutepath=screenshotFile.getAbsolutePath();
        String oldStr = absolutepath.substring(0,absolutepath.indexOf("screenshot"));
        //在绝对路径中，用http://127.0.0.1:7777/ 替换上面的oldStr
        String baseUrl = PropertiesUtil.getUrl("baseUrl");
        //替换
        String tempUrl = absolutepath.replace(oldStr,baseUrl);
        String imgUrl = tempUrl.replace("\\","/");
        Reporter.log("<img src='"+imgUrl+"' hight='100' width='100'><a href='"+imgUrl+
        "' target='_blank'>点击查看大图</a></img>");
    }

}
