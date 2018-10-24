package cases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import javafx.scene.input.KeyCode;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.GoodsPage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GoodsTest extends BaseTest {
    private String scanTxt = "com.idmission.apps.merchantpos:addItem/sku";
    private String skuSearch ="com.idmission.apps.merchantpos:addItem/search_item";
    private String skuName ="com.idmission.apps.merchantpos:productListItem/item";

    AndroidDriver deiver;
    public GoodsTest(AndroidDriver driver){
        this.deiver = driver;
    }

    //通过sku查找商品信息
    public void findInfoBySKUNo(String skuno,String goodsName,AndroidDriver driver){
        GoodsPage goods = new GoodsPage(driver);
        goods.findEleByIDAndTypeText(scanTxt,skuno);
        //捕获弹出框
        //if(goods.waitElmPresentBy(By.partialLinkText(skuno),10))
            //goods.findElementBy(By.partialLinkText(skuno)).click();
        //点击回车键（KEYCODE_ENTER），也即66,
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        goods.sendKeyEvent(66);
        String actSKUName = goods.findElmById(skuName).getText();
        Assert.assertEquals(actSKUName,goodsName);
    }
}
