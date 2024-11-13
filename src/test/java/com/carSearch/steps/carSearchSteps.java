package com.carSearch.steps;

import com.carSearch.DataReaderEngine.CarDetailsComparison;
import com.carSearch.DataReaderEngine.CarInputOutputData;
import com.carSearch.Pages.VehicleDetailsPage;
import com.carSearch.Support.WorldHelper;
import com.carSearch.Support.Utils;
import com.carSearch.Pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class carSearchSteps {
    WorldHelper worldHelper;
    private HomePage homePage;
    CarInputOutputData getData = new CarInputOutputData();
    CarDetailsComparison comparison = new CarDetailsComparison();
    private List<String> extractedCarDetails;

    public carSearchSteps(WorldHelper worldHelper) {
        this.worldHelper = worldHelper;
    }

    @Given("^I am on webuyanycar homepage$")
    public void iAmOnWebuyanycarHomepage() {
        homePage = worldHelper.getBasePage().navigateToHomePage();
    }

    @When("^I accept all cookies$")
    public void iAcceptAllCookies() {
        worldHelper.getBasePage().clickAcceptCookieButton();
    }

    @Then("^I extract the plate numbers$")
    public void iExtractThePlateNumbers() {
        extractedCarDetails = getData.getCarRegNumber();
        System.out.println("=========================================");
        System.out.println("       Extracted Car Details From Input File     ");
        System.out.println("=========================================\n");

        for (String plateNumber : extractedCarDetails) {
            System.out.printf("Car Plate: %-10s\n", plateNumber);
        }

    }

    @Then("^I fetch and compare vehicle details using the registration number$")
    public void iFetchAndCompareVehicleDetailsUsingTheRegistrationNumber() throws IOException {
        Map<String, Map<String, String>> carDetailsFromWebsite = new LinkedHashMap<>();


        Map<String, Map<String, String>> carOutput = comparison.readCarDetailsFromFile("car_output.txt");

        for (String plateNumber : extractedCarDetails) {
            System.out.println("=========================================");
            System.out.printf("Performing Search On The Website For Plate Number: %-10s\n", plateNumber);
            System.out.println("=========================================");

            homePage.enterRegNumber(plateNumber);
            homePage.enterCarMileage(String.valueOf(Utils.generateRandomMileage()));
            VehicleDetailsPage vehicleDetailsPage = homePage.clickGetMyCarEvaluationButton();
            Map<String, String> carDetails;

            if (vehicleDetailsPage.carDetailsRetrievedSuccessfully()) {
                carDetails = vehicleDetailsPage.getCarDetails(plateNumber);
                System.out.printf("Retrieved details for %s:\n", plateNumber);
                carDetails.forEach((key, value) -> System.out.printf("   %-15s : %s\n", key, value));
                vehicleDetailsPage.navigateBack();
                homePage.clearRegAndMileageTextFields();
            } else {
                homePage.clearRegAndMileageTextFields();
                carDetails = new HashMap<>();
                carDetails.put("Manufacturer", null);
                carDetails.put("Model", null);
                carDetails.put("Year", null);
                System.out.println("Skipping " + plateNumber + " as car details are unavailable.");
            }
            carDetailsFromWebsite.put(plateNumber, carDetails);
        }

        System.out.println("=========================================");
        System.out.println("          Car Details Before Comparison         ");
        System.out.println("=========================================\n");

        System.out.println("Details from Output File:");
        System.out.println("-------------------------");
        System.out.println(carOutput);
        System.out.println("\n");

        System.out.println("Details from Website:");
        System.out.println("---------------------");
        System.out.println(carDetailsFromWebsite);
        System.out.println("\n");

        CarDetailsComparison comparison = new CarDetailsComparison();
        comparison.compareCarDetails(carDetailsFromWebsite, carOutput);
    }
}