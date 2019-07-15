package com.anso.auto.phoeinx.testcase;

import com.anso.auto.phoeinx.base.CommonMethod;
import com.anso.auto.phoeinx.util.ExcelUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class QY_SCADA_BaseSetting extends CommonMethod {
    /**
     * author:Linda
     * date:2019/05/05
     * @param name
     * @param psw
     */
    @Test(dataProvider = "getDatas1")
    public void adddept(String name,String psw,String name1,String nick1,String name2,String nick2){
        login(name,psw);
        wait(1000);
        assertElementDisplayed("SCADA");
        click("SCADA");
        wait(2000);
        assertElementDisplayed("基础设置菜单");
        click("基础设置菜单");
        click("组织管理菜单");
        click("添加下级1");
        type("区域名称",name1);
        type("区域别名",nick1);
    }
    @DataProvider
    public Object[][] getDatas1(){
        Object[][] datas = ExcelUtil.readExcel("/QingYuan_datas/SCADA.xlsx",1);
        return datas;
    }

}
