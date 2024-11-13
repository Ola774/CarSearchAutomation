package com.carSearch.Pages;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


import com.carSearch.waits.CustomWaits;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VehicleDetailsPage extends BasePage{
    private static final Logger log = LoggerFactory.getLogger(VehicleDetailsPage.class);

    public VehicleDetailsPage(WebDriver driver) {
        super(driver);
    }

    CustomWaits customWaits = new CustomWaits(this.driver);

    @FindBy(css = "div[class='d-none d-sm-block mb-4 ng-tns-c2261452767-1 ng-star-inserted'] div[class='details-vrm ng-star-inserted']")
    WebElement websiteDisplayedCarRegNumber;

    @FindBy(css = "div[class='position-relative overflow-hidden ng-tns-c2261452767-1'] div[class='d-table'] div:nth-child(1) div:nth-child(2)")
    WebElement manufacturer;

    @FindBy(css = "div[class='position-relative overflow-hidden ng-tns-c2261452767-1'] div[class='d-table'] div:nth-child(2) div:nth-child(2)")
    WebElement model;

    @FindBy(css = "div[class='position-relative overflow-hidden ng-tns-c2261452767-1'] div[class='d-table'] div:nth-child(3) div:nth-child(2)")
    WebElement year;

    @FindBy(xpath = "//h1[contains(text(),\"Sorry, we couldn't find your car\")]")
    WebElement carDetailsNotPresent;

    @FindBy(css = "#btn-back")
    WebElement backbutton;


    public Map<String, String> getCarDetails(String carReg) {

        Map<String, String> carDetails = new HashMap<>();
        customWaits.waitForElement(websiteDisplayedCarRegNumber, Duration.ofSeconds(15));
        carDetails.put("Manufacturer", manufacturer.getText());
        carDetails.put("Model", model.getText());
        carDetails.put("Year", year.getText());

        System.out.println("Car Details for " + carReg + ": " + carDetails);
        return carDetails;
    }

    public boolean carDetailsRetrievedSuccessfully(){
        boolean value = false;
        try {
            if (isElementDisplayed(carDetailsNotPresent)) {
                System.out.println("Car details not found.");
                navigateToHomePage();
                return value;
            }
            else if (isElementDisplayed(websiteDisplayedCarRegNumber)) {
                System.out.println("Car details retrieved successfully.");
                return value = true;
            }
        } catch (Exception e) {
            log.error("carDetailsRetrievedSuccessfully exception: ", e);
        }
        return value;
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void navigateBack(){
        backbutton.click();
    }
}

