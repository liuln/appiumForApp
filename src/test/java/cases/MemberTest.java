package cases;

import io.appium.java_client.android.AndroidDriver;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class MemberTest extends BaseTest{
    //All resource ids in Member Page
    private String addBtn = "com.idmission.apps.merchantpos:id/addKhataBtn";
    private String addTxtTitle ="com.idmission.apps.merchantpos:id/tvActivityTitle";
    private String inputOTPTitle ="com.idmission.apps.merchantpos:id/tvActivityTitle";
    private String txtName ="com.idmission.apps.merchantpos:addKhata/etName";
    private String txtPhone ="com.idmission.apps.merchantpos:addKhata/etPhone";
    private String txtEmail ="com.idmission.apps.merchantpos:addKhata/etEmail";
    private String genderChooseName ="选择";
    private String removeDOB = "com.idmission.apps.merchantpos:addKhata/removeDOB";
    private String birthdayBtn ="com.idmission.apps.merchantpos:addKhata/btnDOB";
    private String doneBtn ="com.idmission.apps.merchantpos:id/btnDone";
    private String listViewId ="android:id/list";
    private String menuListId ="com.idmission.apps.merchantpos:id/mpos_menu_list";
    private String memberListTitle ="com.idmission.apps.merchantpos:id/tvActivityTitle";
    private String menuListXpath ="//android.widget.ListView/android.widget.LinearLayout/android.widget.TextView[@text='会员管理']";
    //find the members in the sales page
    private String searchPhoneIcon ="com.idmission.apps.merchantpos:id/serachIcon";
    private String phoneTxt = "com.idmission.apps.merchantpos:id/etKhataNo";
    private String memberPopList = "com.idmission.apps.merchantpos:id/lvKhataSearch";
    private String phoneShow = "com.idmission.apps.merchantpos:id/tvCustPhone";

    AndroidDriver driver;
    public MemberTest(AndroidDriver driver){
        this.driver =driver;
    }
    //销售页面通过电话号码查找会员
    public void findMemberByTel(String phone,AndroidDriver driver){
        MemberPage member = new MemberPage(driver);
        //点击搜索会员按钮（放大镜按钮）
        member.findEleByIDAndTypeText(searchPhoneIcon,phone);
        //判断输入电话号码的文本框是否存在，若存在就输入号码
        if(member.waitElmPresent(phoneTxt,10)){
            member.findEleByIDAndTypeText(phoneTxt,phone);
            if(member.waitElmPresent(memberPopList,10)){
                WebElement element = member.findElmById(memberPopList);
                List<WebElement> childElms = element.findElements(By.className("android.widget.LinearLayout"));
                childElms.get(0).click();
            }
        }
        //判断会员姓名和积分是否显现
        String actPhone = member.findElmById(phoneShow).getText();
        Assert.assertEquals(actPhone,phone);
    }

}
