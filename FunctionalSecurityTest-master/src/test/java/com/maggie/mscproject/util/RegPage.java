package com.maggie.mscproject.util;

import com.maggie.mscproject.test.StepDefAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegPage {

    private WebDriver driver;

    @FindBy(id = "emailControl")
    @CacheLookup
    private WebElement emailField;

    @FindBy(id = "passwordControl")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(id = "repeatPasswordControl")
    @CacheLookup
    private WebElement repeatPasswordField;

    @FindBy(xpath = "  //*[@id=\"mat-select-2\"]")
    @CacheLookup
    private WebElement dropDownSelection;

    @FindBy(id = "securityAnswerControl")
    @CacheLookup
    private WebElement securityAnswer;

    @FindBy(id = "registerButton")
    @CacheLookup
    private WebElement registerBtn;

    @FindBy(xpath = "/html/body/div[1]/div/a")
    @CacheLookup
    private WebElement cookies;

    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")
    @CacheLookup
    private WebElement welcomeDismiss;






    public RegPage() {
        this.driver = StepDefAll.getDriver();
        PageFactory.initElements(driver, this);
    }
    public void allowCookies () {
        cookies.click();
    }

    public void dismissWelcome () {
        welcomeDismiss.click();
    }
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterPasswordAgain(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void enterRepeatPassword(String rePassword) {
        repeatPasswordField.sendKeys(rePassword);
    }
    public void setDropDownSelection(int  index  ) {
        Select dropdown = new Select(dropDownSelection);
        dropdown.selectByIndex(index);
    }
    public void enterSecurityAnswer(String answer) {
        repeatPasswordField.sendKeys(answer);
    }
    public void clickRegister() {
        registerBtn.click();
    }

}
