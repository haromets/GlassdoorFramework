package com.glassdoor.util;

import com.glassdoor.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;

public class TestUtil extends TestBase {

    public static long EXPLICIT_WAIT = 10;
    public static long IMPLICIT_WAIT = 10;
    public static String VALID_USER_NAME = "qa.test.jhon.smith@gmail.com";
    public static String INVALID_USER_NAME = "qa.invalid.name2@gmail.com";
    public static String VALID_PASSWORD = "12345ABC!";
    public static String INVALID_PASSWORD = "0000ABC!";
    public static String JOB_TITLE = "Software Engineer";
    public static String JOB_LOCATION = "Chicago, IL";


    public static String getPath(String browser)
    {
        String OS=System.getProperty("os.name");
        String driverPath=null;
        if(OS.toUpperCase().contains("WINDOWS"))
        {
            if(browser.toUpperCase().contains("CHROME"))
            {
                driverPath=".//src//main//resources//chromedriver.exe";
            }
            else if(browser.toUpperCase().contains("FF")||browser.toUpperCase().contains("FIRE"))
            {
                driverPath=".//src//main//resources//geckodriver.exe";

            }
        }
        else if(OS.toUpperCase().contains("MAC"))
        {
            if(browser.toUpperCase().contains("CHROME")) // for browser Version 78.
            {
                driverPath=".//src//main//resources//chromedriver";
            }
            else if(browser.toUpperCase().contains("FF")||browser.toUpperCase().contains("FIRE"))
            {
                driverPath=".//src//main//resources//geckodriver";
            }
        }
        return driverPath;
    }

    public static void screenShot(ITestResult result) {
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
    }
}
