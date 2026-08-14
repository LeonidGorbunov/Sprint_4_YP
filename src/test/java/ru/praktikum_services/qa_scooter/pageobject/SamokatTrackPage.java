package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SamokatTrackPage {

    private final WebDriver driver;

    //локаторы
    //картинка-заглушка Такого заказа нет
    private final By imgNotFound = By.xpath("//img[@alt='Not found']");

    public SamokatTrackPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isImgNotFoundDisplayed () {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(imgNotFound))
                .isDisplayed();
    }
}
