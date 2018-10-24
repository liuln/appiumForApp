package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import java.io.*;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver){
        super(driver);
    }

}
