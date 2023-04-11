package com.maggie.mscproject.test;

import com.maggie.mscproject.util.AlertPage;
import com.maggie.mscproject.util.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class StepDefXSS {

    private WebDriver driver = StepDefAll.getDriver();
    HomePage homePage;
    AlertPage alertPage;

    @Given("that I am on the Juice Shop home page")
    public void navigateToHomePage() {
        driver.get("http://localhost:3000/#/");
        homePage = new HomePage(driver);
        homePage.allowCookies();
        homePage.dismissWelcome();
    }

    @When("I search for a product with an XSS payload {string}")
    public void iSearchForAProductWithAnXSSPayload(String payload) throws InterruptedException {
        homePage.searchWebsite(payload);
        Thread.sleep(3000);
    }

    @Then("I should see an alert with the payload message")
    public void verifyAlertMessage() throws InterruptedException {
        alertPage = new AlertPage(driver);
        alertPage.confirmAlertMessage();
    }


}


