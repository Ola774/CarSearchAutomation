package com.carSearch.DataReaderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigTestPropertiesReader {

    public static String getValue(String keyName) {

        String sysPath = System.getProperty("user.dir");
        String configTestPropertiesPath = "/src/main/resources/testConfig.properties";
        Properties config = new Properties();
        FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(sysPath+configTestPropertiesPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        try {
            config.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.getProperty(keyName);
    }

}
