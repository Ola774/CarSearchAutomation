package com.carSearch.DataReaderEngine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarInputOutputData {
    ArrayList<String> carRegNumber = new ArrayList<>();
    HashMap<String, String[]> details = new HashMap<>();
    public String carInputFileContent;

    public CarInputOutputData() {
        try {
            carInputFileContent = new String(Files.readAllBytes(Paths.get("car_input.txt")));
            carRegNumber = extractCarNumbers(carInputFileContent);
        } catch (IOException e) {
            System.out.println("Error reading car input file: " + e.getMessage());
        }
    }

    public ArrayList<String> extractCarNumbers(String text) {
        ArrayList<String> carNumbers = new ArrayList<>();
        String regex = "\\b[A-Z]{2}\\d{2} ?[A-Z]{3}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            carNumbers.add(matcher.group().replaceAll("\\s", ""));
        }

        return carNumbers;
    }

    public ArrayList<String> getCarRegNumber() {
        return carRegNumber;
    }

}
