package pages;

import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.*;
import java.util.List;
import java.util.Date;
import cases.BaseTest;

import javax.swing.text.html.ListView;
import java.util.HashMap;

public class BasePage {

    AndroidDriver driver;

    public BasePage(){
    }

    public BasePage(AndroidDriver driver){
        this.driver = driver;
    }

    //时间转换函数
    public Date parseTime(String date){
        Date retdate;
        try{
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
            retdate =  dateformat.parse(date);
            return retdate;
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    //接受alert弹出框
    public String m_acceptAlert(){
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    //判断alert弹出框是否存在
    public String m_isAlertExisted(){
        try{
            return driver.switchTo().alert().getText();

        }catch(NoAlertPresentException e){
            return null;
        }
    }

    //判断元素是否存在
    public boolean isElementPresent(WebElement elmName, int timeout){
        try{
            WebDriverWait wait  = new WebDriverWait(driver,timeout);
            wait.until(ExpectedConditions.visibilityOf(elmName));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //在指定时间内等待元素出现
    public boolean waitElmPresent(String resId,long timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver input) {
                    return input.findElement(By.id(resId));
                }
            });
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //在指定时间内等待元素出现，自定义查询过滤条件
    public boolean waitElmPresentBy(By by,long timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver input) {
                    return input.findElement(by);
                }
            });
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //通过By来查找所有的元素
    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    //根据id查找元素
    public WebElement findElmById(String resId){
        return  driver.findElement(By.id(resId));
    }

    //根据id查找所有元素
    public List<WebElement> findElmsById(String resId){
        return driver.findElementsById(resId);
    }

    //根据text查找元素
    public WebElement findElmByName(String resName){
        return  driver.findElement(By.name(resName));
    }

    //根据xpath查找元素
    public WebElement findElmByXpath(String xPath){
        return driver.findElementByXPath(xPath);
    }

    //自定义查找条件
    public WebElement findElementBy(By by){
        return driver.findElement(by);
    }

    //找到元素并输入文本
    public  void findEleByIDAndTypeText(String m_id, String text){
        if(m_id !=null)
            findElmById(m_id).sendKeys(text);
    }

    //查找元素并点击
    public  void findEleByIDAndClick(String m_id) {
        if(m_id !=null)
            findElmById(m_id).click();
    }

    //点击下拉选择框中的某一个选项
    public void selectItemByIndex(String name, int index){
        Select selector = new Select(findElmByName(name));
        selector.selectByIndex(index);
    }

    //处理键盘事件
    public void sendKeyEvent(int keycode){
        driver.pressKeyCode(keycode);
    }

    public void waitForEleVisibilityOfID(String resId, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                return input.findElement(By.id(resId));
            }
        });
    }

    public void waitForImplicitly(int minutes){
        driver.manage().timeouts().implicitlyWait(minutes,TimeUnit.MINUTES);
    }

    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }


    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeFirstCarouselFromRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.2);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void performTapAction(WebElement elementToTap) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", (double) 360); // in pixels from left
        tapObject.put("y", (double) 170); // in pixels from top
        tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
        js.executeScript("mobile: tap", tapObject);
    }
}
