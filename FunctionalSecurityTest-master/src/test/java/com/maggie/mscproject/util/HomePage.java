package com.maggie.mscproject.util;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class HomePage extends PageMessages{
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"mat-menu-panel-0\"]/div/button[1]/span")
    private WebElement accountDetail;

    @FindBy(tagName = "a")
    private WebElement navTag1;

    @FindBy(tagName = "mat-list-item")
    private WebElement navTag2;

    @FindBy(xpath = "//*[@id=\"navbarAccount\"]/span[1]/i")
    private WebElement accountIcon;

    @FindBy(xpath = "/html/body/app-root/div/mat-sidenav-container/mat-sidenav/div/sidenav/mat-nav-list/div")
    private WebElement accountNav;

    @FindBy(id = "searchQuery")
    private WebElement searchBtn;
    @FindBy(id = "mat-input-0")
    private WebElement searchField;



    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")
    @CacheLookup
    private WebElement welcomeDismiss;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void openUrl () {
        driver.get("https://juice-shop.herokuapp.com/#/");
    }
    public void allowCookies () {
        HomePage homePage = new HomePage(driver);
        homePage.cookies.click();
       // cookies.click();
    }

    public void dismissWelcome () {
        welcomeDismiss.click();
    }

    public void searchWebsite (String keyword) throws InterruptedException {
        searchBtn.click();
        Thread.sleep(3000);
        searchField.sendKeys(keyword);
        Thread.sleep(1000);
        //searchField.submit();
        searchField.sendKeys(Keys.ENTER);
        //searchBtn.submit();
    }


    public void validateHomePage() {
        accountIcon.click();
        //Assertions.assertTrue(accountDetail.isDisplayed() && accountNav.isDisplayed());
    }

    public void checkDetails(String details) {
        Assertions.assertTrue(accountDetail.getText().contains(details));
    }
}

