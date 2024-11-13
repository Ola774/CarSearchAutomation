package com.carSearch.Hooks;

import com.carSearch.Infrastructure.BrowserFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

public class CucumberScenerioHooks {

    private BrowserFactory browserFactory;
    protected WebDriver driver;


    @Before
    public void startTest() throws Exception {
        this.browserFactory = new BrowserFactory();
        this.driver = BrowserFactory.initialiseAndOpenBrowser();
        }


   @After
    public void endTest() {
        browserFactory.tearDownDriver();
    }
}
