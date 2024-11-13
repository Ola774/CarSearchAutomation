package com.carSearch.Pages;

import com.carSearch.waits.CustomWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    CustomWaits customWaits = new CustomWaits(this.driver);

    @FindBy( css = "#vehicleReg")
    WebElement regNumberTextField;

    @FindBy( css = "#Mileage")
    WebElement carMileageTextField;

    @FindBy( css = "#btn-go")
    WebElement myCarEvaluationButton;


    public void enterRegNumber(String regNumber){

        try {
            customWaits.waitForElement(regNumberTextField, Duration.ofSeconds(10));
            regNumberTextField.clear();
            regNumberTextField.sendKeys(regNumber);
        } catch (Exception e) {
            regNumberTextField = driver.findElement(By.id("element-id"));
            regNumberTextField.clear();
            regNumberTextField.sendKeys(regNumber);

        }
    }
    public void enterCarMileage(String carMileage){
        carMileageTextField.clear();
        carMileageTextField.sendKeys(carMileage);
    }

    public VehicleDetailsPage clickGetMyCarEvaluationButton(){
        myCarEvaluationButton.click();
        return PageFactory.initElements(driver,VehicleDetailsPage.class);
    }

    public void clearRegAndMileageTextFields(){
        customWaits.waitForElement(regNumberTextField, Duration.ofSeconds(10));
        regNumberTextField.clear();
        carMileageTextField.clear();
    }
}
