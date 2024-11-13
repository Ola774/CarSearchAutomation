package com.carSearch.Infrastructure;
import com.carSearch.DataReaderEngine.ConfigTestPropertiesReader;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class BrowserFactory{

    public static WebDriver driver;

    public static WebDriver initialiseAndOpenBrowser() throws Exception {
        String placeOfExecution = ConfigTestPropertiesReader.getValue("execution.environment").toLowerCase();
        if (placeOfExecution.equalsIgnoreCase("local")) {
            LocalCustomCapabilities localBrowserManager = new LocalCustomCapabilities();
            driver = localBrowserManager.createLocalBrowser();

        } else {
            throw new IllegalStateException("there is no such environment");
        }
        prepareBrowser();

        return driver;
    }

    public static void prepareBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MICROSECONDS);
    }

    public void tearDownDriver() {
        if (driver != null)
            driver.quit();
    }
    }

