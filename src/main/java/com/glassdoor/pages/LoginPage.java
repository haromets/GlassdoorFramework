package com.glassdoor.pages;

import com.glassdoor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends TestBase {

    @FindBy(xpath = "//a[contains(text(),' Sign In')]")
    WebElement signIn;

    @FindBy(name = "username")
    WebElement userNameEl;

    @FindBy(name = "password")
    WebElement passwordEl;

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    WebElement signInSecondBtn;

    @FindBy(xpath = "//span[@class='description']/div")
    WebElement loginFailedMsg;

    @FindBy(xpath = "//div[contains(text(),'The username and password you specified are invalid.  Please try again.')]")
    WebElement failedMsg;

    @FindBy(xpath = "//div[contains(text(),'You seem to be having some trouble remembering your password... Please check your email for a password reset link!')]")
    WebElement failedMsgInvalidPassword;


    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public HomePage authenticate(String userName, String password){
        signIn.click();
        userNameEl.sendKeys(userName);
        passwordEl.sendKeys(password);
        signInSecondBtn.click();
        return new HomePage();
    }

    public boolean isAuthFailedValidNameInvalidPas(String userName, String password){
       return isAuthFailed(failedMsg, failedMsgInvalidPassword, userName, password);
    }

    public boolean isAuthFailedInvalidNameInvalidPas(String userName, String password){
        return isAuthFailed(failedMsg, failedMsgInvalidPassword, userName, password);
    }

    public boolean isAuthFailed(WebElement webElement1, WebElement webElement2, String userName, String password){
        signIn.click();
        userNameEl.sendKeys(userName);
        passwordEl.sendKeys(password);
        signInSecondBtn.click();
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(webElement1), ExpectedConditions.visibilityOf(webElement2)));
        return (webElement1.isDisplayed() || webElement2.isDisplayed());
    }
}
