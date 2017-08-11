package com.happy3w.autobuy.driver;

import org.openqa.selenium.WebDriver;

public class RemoteDriver {
    private static RemoteDriver instance = new RemoteDriver();

    public static RemoteDriver getInstance() {
        return instance;
    }

    public WebDriver getDriver(int i, String chrome) {
        return null;
    }
}
