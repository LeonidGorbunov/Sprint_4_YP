package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SamokatOrderSecondPage {

    private final WebDriver driver;

    //локаторы
    //поле Когда привезти самокат
    private final By deliveryDateField = By.xpath("//input[contains(@placeholder, 'Когда привезти самокат')]");
    //поле Срок аренды
    private final By rentalPeriodField = By.xpath("//div[contains(normalize-space(text()), 'Срок аренды')]");
    //шаблон для поля выпадающего списка срока аренды
    private final String rentalPeriodTemplate = "//div[contains(@class, 'Dropdown-option') and contains(normalize-space(text()), '%s')]";
    //чекбокс Черный жемчуг
    private final By checkboxBlackPearl = By.id("black");
    //чекбокс Серая безысходность
    private final By checkboxGrayHopelessness = By.id("grey");
    //поле Комментарий для курьера
    private final By commentField = By.xpath("//input[contains(@placeholder, 'Комментарий')]");
    //кнопка Заказать (под формой Про аренду)
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[normalize-space(text())='Заказать']");
    //кнопка Да (модального окна Хотите оформить заказ?)
    private final By yesButton = By.xpath("//div[contains(@class, 'Order_Modal')]//button[normalize-space(text())='Да']");
    //модальное окно Заказ оформлен
    private final By orderApproved = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(normalize-space(text()), 'Заказ оформлен')]");

    public SamokatOrderSecondPage (WebDriver driver) {
        this.driver = driver;
    }

    public void enterDeliveryDate(String dateSelect) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(deliveryDateField))
                .sendKeys(dateSelect, Keys.ENTER);
    }

    public void selectRentalPeriod(String period) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodField))
                .click();

        //локатор для поля выпадающего списка срока аренды
        By rentalPeriodSelectionLocator = By.xpath(String.format(rentalPeriodTemplate, period));

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodSelectionLocator))
                .click();
    }

    public void selectSamokatColor(String color) {
        switch (color.toLowerCase()) {
            case "черный жемчуг":
                new WebDriverWait(driver, Duration.ofSeconds(15))
                        .until(ExpectedConditions.elementToBeClickable(checkboxBlackPearl))
                        .click();
                break;
            case "серая безысходность":
                new WebDriverWait(driver, Duration.ofSeconds(15))
                        .until(ExpectedConditions.elementToBeClickable(checkboxGrayHopelessness))
                        .click();
                break;
            default:
                throw new IllegalArgumentException("Такого чекбокса нет: " + color);
        }
    }

    public void enterComments(String comments) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(commentField))
                .sendKeys(comments);
    }

    public void orderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(orderButton))
                .click();
    }

    public void yesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(yesButton))
                .click();
    }

    public boolean isOrderCreatedSuccessfully() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderApproved))
                    .isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Лог: Модальное окно подтверждения заказа не появилось за 10 секунд ожидания");
            return false;
        }
    }
}
