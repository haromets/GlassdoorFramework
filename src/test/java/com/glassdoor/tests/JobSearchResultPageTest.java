package com.glassdoor.tests;

import com.glassdoor.base.TestBase;
import com.glassdoor.pages.HomePage;
import com.glassdoor.pages.JobSearchResultPage;
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

public class JobSearchResultPageTest extends TestBase {
    private static final String title = "Software Engineer Jobs in Chicago, IL | Glassdoor";
    LoginPage loginPage;
    HomePage homePage;
    JobSearchResultPage jobSearchResultPage;

    public JobSearchResultPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.authenticate(TestUtil.VALID_USER_NAME, TestUtil.VALID_PASSWORD);
        jobSearchResultPage = homePage.getSearchResult(TestUtil.JOB_TITLE, TestUtil.JOB_LOCATION);
    }

    @Test
    public void testValidTitle() {
        Assert.assertEquals(jobSearchResultPage.getTitle(), title);
    }

    @Test
    public void TestFirstResultJobTitle() {
        Assert.assertTrue(jobSearchResultPage.verifyFirstResultJobTitle());
    }

    @Test
    public void testFirstResultJobTitleLocation() {
        Assert.assertTrue(jobSearchResultPage.verifyFirstResultLocation());
    }

    @Test
    public void testJobEmployerName() {
        Assert.assertTrue(jobSearchResultPage.verifyJobEmployerName());
    }

    @Test
    public void testTimePosted() {
        Assert.assertTrue(jobSearchResultPage.verifyPostedTime());
    }

    @Test
    public void testSalaryRange() {
        Assert.assertTrue(jobSearchResultPage.verifySalaryRange());
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
