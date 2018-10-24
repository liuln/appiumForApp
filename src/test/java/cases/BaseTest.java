package cases;

import io.appium.java_client.android.AndroidDriver;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.aspectj.weaver.ast.And;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.LoginPage;

import java.net.URL;

public class BaseTest {
    AndroidDriver driver;
    //构造函数
    public BaseTest(){

    }
    //带参数的构造函数
    public BaseTest(AndroidDriver driver){
        this.driver = driver;
    }

    @BeforeTest
    public void testSetUp(){
    }

    @AfterTest
    public void testTearDown() {
    }

    @BeforeClass
    public void classSetUp(){
    }

    @AfterClass
    public void classTearDown(){
    }
}