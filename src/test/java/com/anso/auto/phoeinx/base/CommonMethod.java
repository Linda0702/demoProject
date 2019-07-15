package com.anso.auto.phoeinx.base;

import com.anso.auto.phoeinx.util.ExcelUtil;
import org.testng.annotations.DataProvider;

public class CommonMethod extends BaseTester {
    /**
     * author：Linda
     * date：2019/06/25
     * desc:封装一些常用的操作
     */

    //登录系统操作
    public void login(String name,String pwd){
        to("loginUrl");//打开网页
        maximize();
        refresh();
        assertElementEditable("登录账号名");
        type(name,"登录账号名");
        type(pwd,"登录密码");
        assertElementEditable("登录按钮");
        click("登录按钮");
    }
    //
    public int getAmount(String keyword){
        int amount=getElements(keyword).size();
        return amount;
    }
    @DataProvider
    public Object[][] getData1(){
        Object[][] datas = ExcelUtil.readExcel("ChangSha_datas/system.xlsx",1);
        return datas;
    }
}
