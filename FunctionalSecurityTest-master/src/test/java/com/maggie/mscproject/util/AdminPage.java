package com.maggie.mscproject.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

    private WebDriver driver;

    @FindBy(xpath = "//app-administration/mat-card/h1")
    private WebElement adminDashboard;

    @FindBy(xpath = "//mat-row[6]//mat-cell[4]//button[contains(@class,'mat-button')]")
    private WebElement delComment;

    @FindBy(xpath = "//app-administration/mat-card/div/div[2]/div/mat-table/mat-row[6]")
    private WebElement customerFeedbackToBeDeleted;

    @FindBy(xpath = "//app-administration/mat-card/div/div[2]/div/mat-table")
    private WebElement customerFeedbacks;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAdminPageDisplayed() {
        return driver.getCurrentUrl().equals("https://juice-shop.herokuapp.com/#/administration")
                && adminDashboard.isDisplayed();
    }

    public boolean isCustomerFeedbackDisplayed() {
        return customerFeedbacks.isDisplayed();
    }
    public String customerFeedbacks() {
        return customerFeedbacks.getText();
    }

    public boolean isThisCustomerFeedbackDisplayed() {
        return customerFeedbackToBeDeleted.isDisplayed();
    }

    public String customerComment () {
        return customerFeedbackToBeDeleted.getText();
    }

    public void deleteCustomerFeedback() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", delComment);
    }
}



