package com.glassdoor.pages;

import com.glassdoor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {


    @FindBy (xpath = "//a[@aria-label='Glassdoor']")
    WebElement logo;

    @FindBy (xpath = "//input[@id='sc.keyword']")
    WebElement inputJobTitle;

    @FindBy (xpath = "//input[@id='sc.location']")
    WebElement inputLocation;

    @FindBy (xpath = "//button[@id='HeroSearchButton']")
    WebElement searchBtn;

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public boolean verifyDisplayedLogo(){
        return logo.isDisplayed();
    }

    public JobSearchResultPage getSearchResult(String jobTitle, String location){
        inputJobTitle.sendKeys(jobTitle);
        inputLocation.clear();
        inputLocation.sendKeys(location);
        searchBtn.click();
        return new JobSearchResultPage();
    }


}
