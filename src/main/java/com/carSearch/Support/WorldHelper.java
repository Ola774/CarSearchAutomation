package com.carSearch.Support;

import com.carSearch.Infrastructure.BrowserFactory;
import com.carSearch.Pages.BasePage;
import org.openqa.selenium.support.PageFactory;

public class WorldHelper extends BrowserFactory {
    private BasePage basePage;


    public BasePage getBasePage(){
        if (basePage != null) return basePage;
        return PageFactory.initElements(driver, BasePage.class);
    }
}
