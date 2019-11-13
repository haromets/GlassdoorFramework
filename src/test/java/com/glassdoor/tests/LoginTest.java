package com.glassdoor.tests;

import com.glassdoor.base.TestBase;
import com.glassdoor.pages.LoginPage;
import com.glassdoor.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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


//    @Test
//    public void testInvalidAccount2() {
//        Assert.assertTrue(loginPage.isAuthFailedInvalidNameInvalidPas(TestUtil.INVALID_USER_NAME, TestUtil.INVALID_PASSWORD));
//    }

    @Test
    public void testInvalidAccount() {
        Assert.assertTrue(loginPage.isAuthFailedValidNameInvalidPas(TestUtil.VALID_USER_NAME, TestUtil.INVALID_PASSWORD));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
