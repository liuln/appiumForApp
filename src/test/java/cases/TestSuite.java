package cases;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MemberPage;

import java.net.URL;

public class TestSuite {
    //设置公共的andriodDriver
    private AndroidDriver driver;
    private void setDriver(){
        try{
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("platformName","Android");//应用系统
            cap.setCapability("deviceName","0123456789ABCDEF");//设备号
            cap.setCapability("platformVersion","6.0");//版本号
            cap.setCapability("appPackage","com.idmission.apps.merchantpos");//包名
            cap.setCapability("appActivity","com.idmission.apps.merchantpos.ui.Splash");//当前活动的应用
            cap.setCapability("appWaitActivity","com.idmission.apps.merchantpos.ui.LogInActivity");//等待的应用
            cap.setCapability("autoGrantPermissions",true);
            cap.setCapability("unicodeKeyboard",true);//是使用unicode编码方式发送字符串
            cap.setCapability("resetKeyboard",true);//隐藏键盘
            // cap.setCapability("appActivity",".ui.khata.KhataListMainActivity");
            // cap.setCapability("sessionOverride", true);
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),cap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public AndroidDriver getDriver(){
        return this.driver;
    }

    LoginTest loginTest = new LoginTest(driver);
    MemberTest memTest = new MemberTest(driver);
    GoodsTest goodsTest = new GoodsTest(driver);
    SaleTest saleTest = new SaleTest(driver);

    @Parameters({"username","password"})
    @BeforeSuite
    public void suiteSetUp(String userId,String pwd){
        setDriver();
        //loginTest = new LoginTest(driver);
        loginTest.loginWithKeepOn(userId,pwd,driver);
    }
    @AfterSuite
    public void suiteTearDown(){
        //loginTest.logoutSystem(driver);
        //loginTest.exitSystem(driver);
    }

    @Test
    public void memberTest(){
        //memTest = new MemberTest(driver);
        memTest.findMemberByTel("18600321026",driver);
    }

    @Test
    public void goodsTest(){
        //goodsTest = new GoodsTest(driver);
        goodsTest.findInfoBySKUNo("6923644281168","未来星营养果汁酸奶饮品（草莓味） [奶制品]",driver);
    }
    @Test
    public void smokeTest(){
        memTest.findMemberByTel("18600321026",driver);
        goodsTest.findInfoBySKUNo("6923644281168","未来星营养果汁酸奶饮品（草莓味） [奶制品]",driver);
        saleTest.addAmountCaculate(3,driver);
        saleTest.finishCashPay(driver);
    }
}
