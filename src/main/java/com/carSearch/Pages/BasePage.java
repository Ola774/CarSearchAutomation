package com.carSearch.Pages;

import com.carSearch.waits.CustomWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookieButton;

    @FindBy(css = "#onetrust-banner-sdk")
    WebElement cookieBanner;


    public void clickAcceptCookieButton(){
        CustomWaits customWaits = new CustomWaits(this.driver);
        customWaits.waitForElement(cookieBanner, Duration.ofSeconds(15));
        driver.switchTo().activeElement();
        acceptCookieButton.click();
        driver.switchTo().activeElement();
    }

    public HomePage navigateToHomePage(){
        driver.get("https://www.webuyanycar.com");
        return PageFactory.initElements(driver,HomePage.class);
    }
}

