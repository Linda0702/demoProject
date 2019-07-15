package com.anso.auto.phoeinx.testcase;

import com.anso.auto.phoeinx.base.CommonMethod;
import com.anso.auto.phoeinx.util.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CS_CloudPlatform_SystemSetting extends CommonMethod {
    /**
     * author：Linda
     * date：2019/06/25
     * 本模块为对长沙水务，云平台系统设置的自动化测试用例
     */
    @Test(dataProvider = "getDatas1")
    public void adduser(String name,String psw,String code){
        //login(name,psw,code);//登录系统
        assertElementDisplayed("云平台子系统");
        click("云平台子系统");
        assertElementDisplayed("菜单展开按钮");
        click("菜单展开按钮");
        wait(3000);
        assertElementDisplayed("系统设置菜单");
        click("系统设置菜单");
        wait(1000);
        assertElementDisplayed("用户管理菜单");
        click("用户管理菜单");//进入云平台-系统设置-用户管理模块
        wait(1000);
        assertElementDisplayed("用户列表行");
        getAmount("用户列表行");//获取第一页展示的用户数
        wait(1000);
        click("分页下拉选");
        assertElementDisplayed("30条/页");//选择每页分页条数/30
        String exceptResult=getText("30条/页");//获取选项的分页条数
        click("30条/页");//点击选择每页分30条展示
        wait(1000);
        Assert.assertEquals(getAmount("用户列表行")+"条/页",exceptResult);
    }
    @DataProvider
    public Object[][] getDatas1(){
        Object[][] datas = ExcelUtil.readExcel("/ChangSha_datas/system.xlsx",1);
        return datas;
    }
}
