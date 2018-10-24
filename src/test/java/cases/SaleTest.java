package cases;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import pages.*;

public class SaleTest extends BaseTest {
    //All resource ids in Sale Page
    private String mountTxt ="com.idmission.apps.merchantpos:saleLayout/etAmountWithoutItems";
    private String discountTxt ="com.idmission.apps.merchantpos:addOfferScreen/discount";
    private String addGoods ="com.idmission.apps.merchantpos:productListItem/qtyPlusBtn";
    private String minusGoods="com.idmission.apps.merchantpos:productListItem/qtyMinusBtn";
    private String cardCount ="com.idmission.apps.merchantpos:id/tv_bo_of_items";
    private String totalPrice = "com.idmission.apps.merchantpos:id/tv_total_price_of_items";
    private String caculateBtn = "com.idmission.apps.merchantpos:id/btn_done";
    private String cashAmount = "com.idmission.apps.merchantpos:saleLayout/cash_amount_value";
    private String payBtn ="com.idmission.apps.merchantpos:id/tv_done_btn_label";
    private String paiedTitle ="com.idmission.apps.merchantpos:id/tvActivityTitle";
    private String payMethod ="com.idmission.apps.merchantpos:id/paymentMethod";

    AndroidDriver driver;
    public SaleTest(AndroidDriver driver){
        this.driver = driver;
    }

    // 增加商品数量的结算，前提条件是已经有至少一个商品在购物车中
    public void addAmountCaculate(int addTimes, AndroidDriver driver){
        SalePage salePage = new SalePage(driver);
        String  tAmount = salePage.findElmById(cardCount).getText();
        double amount = Double.parseDouble(tAmount);
        String tPrice = salePage.findElmById(totalPrice).getText();
        double iPrice = Double.parseDouble(tPrice);
        double actAmount =0.0;
        double actPrice = 0.0;
        //添加商品数量的测试
        int i =1;
        while(i <= addTimes){
            salePage.findEleByIDAndClick(addGoods);
            actAmount = Double.parseDouble(salePage.findElmById(cardCount).getText());
            actPrice = Double.parseDouble(salePage.findElmById(totalPrice).getText());
            Assert.assertEquals(actAmount,amount*(i+1));
            Assert.assertEquals(actPrice,iPrice*(i+1));
            i++;
        }
        //点击结算按钮
        salePage.findEleByIDAndClick(caculateBtn);
        salePage.waitElmPresent(cashAmount,10);
        double actCash = Double.parseDouble(salePage.findElmById(cashAmount).getText());
        Assert.assertEquals(actCash,actPrice);
    }

    //减少商品数量的结算

    // 改变商品单价的测试

    //输入折扣比例的结算

    //完成支付,前提是已经完成了结算之后支付按钮已经出现了
    public void finishCashPay(AndroidDriver driver){
        SalePage salePage = new SalePage(driver);
        salePage.waitElmPresent(payBtn,10);
        NumberFormat nbf = NumberFormat.getInstance();
        String cashPaying = salePage.findElmById(totalPrice).getText();
        salePage.findEleByIDAndClick(payBtn);
        String title = salePage.findElmById(paiedTitle).getText();
        Assert.assertEquals(title,"交易摘要");
        String strPaied = salePage.findElmById(payMethod).getText();
        String expPaied = "现金: "+ cashPaying + " CNY";
        Assert.assertEquals(strPaied,expPaied);
        salePage.findElmByName("已完成").click();
    }
}
