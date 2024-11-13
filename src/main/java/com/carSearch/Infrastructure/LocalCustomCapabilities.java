package com.carSearch.Infrastructure;

import com.carSearch.DataReaderEngine.ConfigTestPropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class LocalCustomCapabilities {
    private WebDriver driver;
    private final String propertyHeadless = System.getProperty("headless");
    private boolean headless = false;


    public WebDriver createLocalBrowser() {
        String propertyBrowser = System.getProperty("browser");
        String browser = ConfigTestPropertiesReader.getValue("Browser.Type");
        if (propertyBrowser != null) {
            browser = propertyBrowser;
        }
        if (browser.contains("chrome")) {
            getChromeDriver();
        } else if (browser.contains("firefox")) {
            getFirefoxDriver();
        }
        return driver;
    }

    public void getFirefoxDriver() {
        if (propertyHeadless != null) {
            headless = Boolean.parseBoolean(propertyHeadless);
        }
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        driver = new FirefoxDriver(options);
    }

    public void getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (propertyHeadless != null) {
            headless = Boolean.parseBoolean(propertyHeadless);
        }
        options.setHeadless(headless);
        options.addArguments("--disable-infobars");
        options.addArguments("--incognito");
        options.addArguments("--enable-javascript");
        options.addArguments("--disable-websecurity");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximised");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--fast-start");
        options.addArguments("--javascript-harmony");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        driver = new ChromeDriver(options);
    }

}

