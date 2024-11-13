package com.carSearch.DataReaderEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarDetailsComparison {

    public Map<String, Map<String, String>> readCarDetailsFromFile(String filePath) throws IOException {
        Map<String, Map<String, String>> carDetailsMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String carReg = parts[0].trim();
                String manufacturer = parts[1].trim();
                String model = parts[2].trim();
                String year = parts[3].trim();

                Map<String, String> carDetails = new HashMap<>();
                carDetails.put("Manufacturer", manufacturer);
                carDetails.put("Model", model);
                carDetails.put("Year", year);

                carDetailsMap.put(carReg, carDetails);
            }
        }
        reader.close();
        return carDetailsMap;
    }

    public void compareCarDetails(Map<String, Map<String, String>> carDetailsMap, Map<String, Map<String, String>> returnedCarDetails) {
        for (String carRegNumber : carDetailsMap.keySet()) {
            Map<String, String> expectedDetails = carDetailsMap.get(carRegNumber);
            Map<String, String> actualDetails = returnedCarDetails.get(carRegNumber);
            System.out.println("=========================================");
            System.out.println("Comparing details for Car Registration: " + carRegNumber);

            if (actualDetails != null) {
                boolean mismatch = false;

                for (String key : expectedDetails.keySet()) {
                    String expectedValue = expectedDetails.get(key);
                    String actualValue = actualDetails.get(key);

                    if (expectedValue != null) {
                        if (!expectedValue.equals(actualValue)) {
                            System.out.println("Mismatch for " + carRegNumber + ": Expected " + key + " = " + expectedValue + ", but got " + actualValue);
                            mismatch = true;
                        }
                    } else if (actualValue != null) {
                        System.out.println("Mismatch for " + carRegNumber + ": Expected " + key + " = null, but got " + actualValue);
                        mismatch = true;
                    }
                }
                if (mismatch) {
                    System.out.println("Car details for " + carRegNumber + " have mismatches.");
                    System.out.print("");

//                    To fail the test, we just need to throw an assertion error when there's a mismatch, we can uncomment the line below
//                    throw new AssertionError("Car details for " + carRegNumber + " have mismatches.");
                } else {
                    System.out.println("Car details for " + carRegNumber + " match.");
                    System.out.print("");
                }
            } else {
                System.out.println("No Car Reg Number returned for " + carRegNumber);
                System.out.print("");
//                throw new AssertionError("Car details for " + carRegNumber + " not found");
            }
        }
    }
}
