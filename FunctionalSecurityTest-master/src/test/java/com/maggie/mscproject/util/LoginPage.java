package com.maggie.mscproject.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageMessages {

    private WebDriver driver;
    private HomePage homePage;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(css = "div.alert.alert-danger.ng-scope")
    private WebElement errorMessage;

    @FindBy(xpath = "/html/body/div[1]/div/a")
    @CacheLookup
    private WebElement cookies;

    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")
    @CacheLookup
    private WebElement welcomeDismiss;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void allowCookies () {
        cookies.click();
    }

    public void dismissWelcome () {
        welcomeDismiss.click();
    }
    public void navigateToLoginPage(String url) {
        driver.get(url);
    }

    public void enterDetails(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void verifyLoginPage() {
        homePage = new HomePage(driver);
        homePage.validateHomePage();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
