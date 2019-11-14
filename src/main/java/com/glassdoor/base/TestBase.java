package com.glassdoor.base;

import com.glassdoor.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    public static Actions actions;


    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream file =
                    new FileInputStream(System.getProperty("user.dir")+"/src//main//resources//props.properties");
            prop.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",TestUtil.getPath(browserName));
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("FF")) {
            System.setProperty("webdriver.gecko.driver", TestUtil.getPath(browserName));
            driver = new FirefoxDriver();
        }
        wait = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
    }
}
