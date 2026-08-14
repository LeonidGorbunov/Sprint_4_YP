package ru.praktikum_services.qa_scooter.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver driverSelect (String browserName) {
        if (browserName == null) {
            throw new IllegalArgumentException("Укажите chrome или firefox");
        }
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
        }
    }
}