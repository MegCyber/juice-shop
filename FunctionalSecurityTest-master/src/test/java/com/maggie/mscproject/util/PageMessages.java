package com.maggie.mscproject.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public abstract class PageMessages {
    @FindBy(xpath = "/html/body/div[1]/div/a")
    @CacheLookup
    public WebElement cookies;

    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")
    @CacheLookup
     public WebElement welcomeDismiss;

    public void allowCookies() {
        cookies.click();
    }

    public void dismissWelcome () {
        welcomeDismiss.click();
    }
}
