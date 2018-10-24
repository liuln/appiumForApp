package cases;

import io.appium.java_client.android.AndroidDriver;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import java.util.concurrent.*;

import okhttp3.internal.Internal;
import org.aspectj.weaver.ast.And;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.*;
import sun.rmi.runtime.Log;

import java.sql.DriverManager;

public class LoginTest extends BaseTest{
    //All resource ids in Login Page
    private String loginid = "com.idmission.apps.merchantpos:login_screen/loginId";
    private String password = "com.idmission.apps.merchantpos:login_screen/password";
    private String keepmeloggedin = "com.idmission.apps.merchantpos:id/keep_me_logged_in_checkbox";
    private String loginbtn = "com.idmission.apps.merchantpos:login_screen/loginButton";
    //登录ID后的文本框元素的resouce id
    private String loginId_menu = "com.idmission.apps.merchantpos:id/mpos_login_id";
    //销售页面中点入左侧菜单的按钮
    private String menuIcon_sale = "com.idmission.apps.merchantpos:id/mpos_menu";
    //账户积分低的提示页面中的元素
    private String msgOKBtn ="com.idmission.apps.merchantpos:confirmDialog/alertOk";
    //退出登录
    private String exitBtn ="com.idmission.apps.merchantpos:login_screen/exitButton";
    //注销登录
    private String menuListXpath ="//android.widget.ListView/android.widget.LinearLayout/android.widget.TextView[@text='注销']";
    private String exitOK ="com.idmission.apps.merchantpos:confirmDialog/ok";

    AndroidDriver driver;
    public LoginTest(AndroidDriver driver){
        this.driver =driver;
    }
   // private LoginPage pLogin = new LoginPage(driver);

    //输入用户名和密码，选中保持登录，然后点击登录按钮
    public void loginWithKeepOn(String userId, String pwd,AndroidDriver driver) throws InternalException {
      try {
          LoginPage pLogin = new LoginPage(driver);
          if (pLogin != null) {
              pLogin.findEleByIDAndTypeText(loginid, userId);
              pLogin.findEleByIDAndTypeText(password, pwd);
              pLogin.findEleByIDAndClick(keepmeloggedin);
              pLogin.findEleByIDAndClick(loginbtn);

              //隐式等待5秒，等待验证的过程和下载库存结束
              //driver.manage().timeouts().implicitlyWait(5,TimeUnit.MINUTES);
              //Thread.sleep(30000);
              //等待弹出的账户积分低的提示页面出现并点击确定
              if (pLogin.waitElmPresent(msgOKBtn,1000))
                  pLogin.findEleByIDAndClick(msgOKBtn);

             /* //接受弹出的账户积分低的提示框，不是alert是正常的andriod页面
              if(loginPage.m_isAlertExisted() == alertLowBalText){
                  loginPage.m_acceptAlert();
              }*/

              //等待销售页面并点击按钮打开菜单页面找到登录ID
              if(pLogin.waitElmPresent(menuIcon_sale, 300)){
                  pLogin.findEleByIDAndClick(menuIcon_sale);
              }
              //判断登录ID是否正确
              String assertTxt = pLogin.findElmById(loginId_menu).getText();
              Assert.assertEquals(assertTxt, userId);
          }
      }catch (Exception e){
                  e.printStackTrace();
              }
      }

    //@Test(groups = {"logintest"},priority = 2)
      //登录页面点击退出登录
    public void exitSystem(AndroidDriver driver) throws InternalException {
        try {
            LoginPage pLogin = new LoginPage(driver);
            if (pLogin != null) {
                pLogin.findEleByIDAndClick(exitBtn);
                if(pLogin.waitElmPresent(exitOK,30))
                    pLogin.findEleByIDAndClick(exitOK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   // @Test(groups = {"logintest"},priority = 3)
    //注销用户登录
    public void logoutSystem(AndroidDriver driver) throws InternalException {
        try {
            LoginPage pLogin = new LoginPage(driver);
            if (pLogin != null) {
                //等待销售页面并点击按钮打开菜单页面找到登录ID
                if(pLogin.waitElmPresent(menuIcon_sale, 300)){
                    pLogin.findEleByIDAndClick(menuIcon_sale);
                }
                pLogin.findElmByXpath(menuListXpath).click();
                if(pLogin.waitElmPresent(exitOK,10))
                    pLogin.findEleByIDAndClick(exitOK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }

