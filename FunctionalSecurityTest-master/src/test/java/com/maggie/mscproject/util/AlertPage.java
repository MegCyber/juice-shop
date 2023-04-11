package com.maggie.mscproject.util;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertPage {
    private WebDriver driver;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmAlertMessage() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Thread.sleep(3000);
        alert.accept();
        Assert.assertEquals(alertMessage, "xss");
    }
}

