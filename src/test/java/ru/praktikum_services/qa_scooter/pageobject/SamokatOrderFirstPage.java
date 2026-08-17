package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SamokatOrderFirstPage {

    private final WebDriver driver;

    //локаторы
    //поле Имя
    private final By nameField = By.xpath("//input[contains(@placeholder, 'Имя')]");
    //поле Фамилия
    private final By surnameField = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    //поле Адрес: куда привезти заказ
    private final By addressField = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    //поле Станция метро
    private final By metroStationField = By.xpath("//input[contains(@placeholder, 'Станция метро')]");
    //шаблон локатора для выбора станции в выпадающем списке
    private final String metroStationTemplate = "//button[contains(@class, 'Order_SelectOption')]//div[contains(normalize-space(text()), '%s')]";
    //поле Телефон: на него позвонит курьер
    private final By phoneNumberField = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    //кнопка Далее
    private final By continueButton = By.xpath("//div[contains(@class, 'Order_Content')]//button[normalize-space(text())='Далее']");
    //логотип Самокат
    private final By samokatLogo = By.xpath("//a[contains(@class, 'Header_LogoScooter')]");
    //шаблон для ошибок полей формы
    private final String errorMessageTemplate = "//*[contains(normalize-space(text()), '%s')]";

    public SamokatOrderFirstPage (WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(nameField))
                .sendKeys(name);
    }

    public void enterSurname(String surname) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(surnameField))
                .sendKeys(surname);
    }

    public void enterAddress(String address) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(addressField))
                .sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        WebElement element  = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(metroStationField));
        element.click();
        element.sendKeys(stationName);

        //локатор для станции метро в выпадающем списке
        By stationDropDownLocator = By.xpath(String.format(metroStationTemplate, stationName));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(stationDropDownLocator))
                .click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(phoneNumberField))
                .sendKeys(phoneNumber);
    }

    public void continueButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(continueButton))
                .click();
    }

    public void samokatLogoClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(samokatLogo))
                .click();
    }

    public boolean isErrorMessageDisplayed (String expectedText) {

            //локатор для сообщения об ошибке валидации поля
            By errorMessageLocator = By.xpath(String.format(errorMessageTemplate, expectedText));

            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator))
                    .isDisplayed();
    }
}