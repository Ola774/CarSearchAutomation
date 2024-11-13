package com.carSearch.waits;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CustomWaits {
    private final WebDriver driver;

    public CustomWaits(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(WebElement element, Duration timeout) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }
}
