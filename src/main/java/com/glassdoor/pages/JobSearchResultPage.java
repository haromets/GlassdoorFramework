package com.glassdoor.pages;

import com.glassdoor.base.TestBase;
import com.glassdoor.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class JobSearchResultPage extends TestBase {

    @FindBy(xpath = "//li[@class='jl selected']/div[@class='jobContainer']/child::a[1]")
    WebElement firstResultJobTitle;

    @FindBy(xpath = "//li[@class='jl selected']//span[@class='subtle loc']")
    WebElement firstResultLocation;

    @FindBy(xpath = "//li[@class='jl']")
    List<WebElement> vacancyList;

    @FindBy(xpath = "//span[@class='minor']")
    List<WebElement> postedTimeList;

    @FindBy(xpath = "//li[@class='jl selected']//div[@class='jobInfoItem jobEmpolyerName']")
    WebElement jobEmployerName;

    @FindBy(xpath = "//div[@class='employerName']")
    WebElement jobEmployerNameInDescription;

    @FindBy(xpath = "//div[@id='PrimaryDropdown']/ul/child::li[2]")
    WebElement lastDateSelect;

    @FindBy(xpath = "//div[@id='filter_fromAge']")
    WebElement postedTimeBtn;

    @FindBy(xpath = "//p[@class='jobsCount']")
    WebElement jobsCount;

    @FindBy(xpath = "//div[@data-test='SALRANGE']")
    WebElement salaryRangeBtn;

    @FindBy(xpath = "//div[@style='height: 100%;']")
    WebElement salaryRangeMoreThenHalf;

    @FindBy(xpath = "//input[@name='Include jobs with no salary data']")
    WebElement checkBoxSalary;

    @FindBy(xpath = "//button[@class='applybutton gd-btn gd-btn-link gradient gd-btn-2 gd-btn-sm']")
    WebElement ApplyBtn;

    @FindBy(xpath = "//div[@data-test='SALRANGE']/span[@class='label']")
    WebElement salaryRange;

    @FindBy(xpath = "//input[@type='range']")
    WebElement inputSalaryBtn;

    @FindBy(xpath = "//span[@class='salaryText']")
    List<WebElement>  estGlassdoorSalaryRange;


    public JobSearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.titleContains("Software Engineer Jobs in Chicago, IL | Glassdoor"));
        return driver.getTitle();
    }

    // verifying if the first vacancy contains the job title we are looking for
    public boolean verifyFirstResultJobTitle() {
        return firstResultJobTitle.getText().contains(TestUtil.JOB_TITLE);
    }

    // verifying is the first vacancy contains the location we are looking for
    public boolean verifyFirstResultLocation() {
        return firstResultLocation.getText().contains(TestUtil.JOB_LOCATION);
    }

    // verifying is the first five Employers Name the same Name in a job Description
    public boolean verifyJobEmployerName() {
        for (int i = 0; i < 5; i++) {
            vacancyList.get(i).click();
            wait.until(ExpectedConditions.textToBePresentInElement(jobEmployerNameInDescription, jobEmployerName.getText()));
            if (!(jobEmployerNameInDescription.getText().contains(jobEmployerName.getText()))) {
                return false;
            }
        }
        return true;
    }

    //verifying the correspondence of the vacancy placement date set in the filter
    public boolean verifyPostedTime() {
        String firstJobsCount = jobsCount.getText();
        postedTimeBtn.click();
        lastDateSelect.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(jobsCount, firstJobsCount)));
        for (int i = 0; i < postedTimeList.size(); i++) {
            if(!(postedTimeList.get(i).getText().contains("24hr") || postedTimeList.get(i).getText().contains("1d"))){
                return false;
            }
        }
        return true;
    }

    //verifying the correspondence estimate salary set in the filter
    public boolean verifySalaryRange(){
        String firstJobsCount = jobsCount.getText();
        salaryRangeBtn.click();
        Action seriesOfActions = actions
                .moveToElement(inputSalaryBtn)
                .dragAndDrop(inputSalaryBtn, salaryRangeMoreThenHalf)
                .build();
        seriesOfActions.perform() ;
        checkBoxSalary.click();
        ApplyBtn.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(jobsCount, firstJobsCount)));
        int [] salaryRangeArray = getIntegerArrayFromString(salaryRange);
        for(int i=0; i<estGlassdoorSalaryRange.size(); i++){
            int [] estSalaryRangeArray = getIntegerArrayFromString(estGlassdoorSalaryRange.get(i));
            if(estSalaryRangeArray[1]<salaryRangeArray[0] || estSalaryRangeArray[0]>salaryRangeArray[1]){
                return false;
            }
        }
        return true;
    }

    public int[] getIntegerArrayFromString(WebElement webElement){
        String[] stringArray = webElement.getText().split("-");
        int[] integerArray = new int[stringArray.length];
        for(int i=0; i<stringArray.length; i++){
            integerArray[i]=Integer.parseInt(stringArray[i].replaceAll("[\\D]", ""));
        }
        return integerArray;
    }

}
