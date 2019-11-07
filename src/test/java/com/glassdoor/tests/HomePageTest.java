package com.glassdoor.tests;

import com.glassdoor.base.TestBase;
import com.glassdoor.pages.HomePage;
import com.glassdoor.pages.LoginPage;
import com.glassdoor.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    private static final String title = "Glassdoor Job Search | Find the job that fits your life";
    LoginPage loginPage;
    HomePage homePage;

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.authenticate(TestUtil.VALID_USER_NAME, TestUtil.VALID_PASSWORD);
    }

    @Test
    public void testValidTitle() {
        Assert.assertEquals(homePage.getTitle(), title);
    }

    @Test
    public void testDisplayedText() {
        Assert.assertTrue(homePage.verifyDisplayedLogo());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}