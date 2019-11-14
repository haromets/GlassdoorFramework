package com.glassdoor.tests;

import com.glassdoor.base.TestBase;
import com.glassdoor.pages.LoginPage;
import com.glassdoor.util.TestUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class LoginTest extends TestBase {
    LoginPage loginPage;

    public LoginTest(){
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }


    @Test
    public void testInvalidAccount2() {
        Assert.assertTrue(loginPage.isAuthFailedInvalidNameInvalidPas(TestUtil.INVALID_USER_NAME, TestUtil.INVALID_PASSWORD));
    }

    @Test
    public void testInvalidAccount() {
        Assert.assertTrue(loginPage.isAuthFailedValidNameInvalidPas(TestUtil.VALID_USER_NAME, TestUtil.INVALID_PASSWORD));
    }

    @AfterMethod
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(prop.getProperty("screen.path") + result.getName() + ".png"));
                System.out.println(prop.getProperty("screen.path") + result.getName() + ".png");
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }
}
