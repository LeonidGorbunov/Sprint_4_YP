package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


public class SamokatMainPage {

    private final WebDriver driver;

    //локаторы
    //куки попапа
    private final By cookieConfirmationButton = By.id("rcc-confirm-button");
    //шаблон для текстовых заголовков аккордеона
    private final String accordionTextHeadingTemplate = "//div[contains(@class, 'accordion__button') and contains(text(), '%s')]";
    //шаблон текстовых блоков ответов аккордеона
    private final String accordionTextPanelTemplate = "//div[contains(@class, 'accordion__button') and contains(text(), '%s')]/ancestor::div[contains(@class, 'accordion__item')]//div[contains(@class, 'accordion__panel')]";
    //верхняя кнопка Заказать
    private final By topOrderButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[normalize-space(text())='Заказать']");
    //нижняя кнопка Заказать
    private final By bottomOrderButton = By.xpath("//div[contains(@class, 'Home_ThirdPart')]//button[normalize-space(text())='Заказать']");
    //логитип Яндекс
    private final By yandexLogo = By.xpath("//a[contains(@class, 'Header_LogoYandex')]");
    //кнопка Статус заказа
    private final By orderStatusButton = By.xpath("//button[contains(@class, 'Header_Link') and normalize-space(text())='Статус заказа']");
    //поле Введите номер заказа
    private final By orderNumberField = By.xpath("//input[contains(@placeholder, 'Введите номер заказа')]");
    //кнопка Go!
    private final By goButton = By.xpath("//button[contains(@class, 'Header_Button') and normalize-space(text())='Go!']");

    public SamokatMainPage (WebDriver driver) {
        this.driver = driver;
    }

    public void clickAccordionHeading(String accordionQuestionText) {

        //локатор для текстовых заголовков аккордеона
        String questionXpath = String.format(accordionTextHeadingTemplate, accordionQuestionText);
        By questionLocator = By.xpath(questionXpath);

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(questionLocator))
                .click();
    }

    public String getAccordionPanelText(String accordionQuestionText) {

        //локатор для текстовых блоков аккордеона
        String panelXpath = String.format(accordionTextPanelTemplate, accordionQuestionText);
        By panelLocator = By.xpath(panelXpath);

        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(panelLocator))
                .getText();
    }

    public void scrollToAccordion(String accordionQuestionText) {

        //локатор для текстовых заголовков аккордеона
        String questionXpath = String.format(accordionTextHeadingTemplate, accordionQuestionText);
        By questionLocator = By.xpath(questionXpath);

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(questionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
    }

    public void cookieConfirmationButtonClick () {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(cookieConfirmationButton))
                    .click();
        } catch (Exception e) {
            System.out.println("Лог: Попап куки не появился или уже был закрыт.");
        }
    }

    public void clickTopOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton))
                .click();
    }

    public void clickBottomOrderButton() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        element.click();
    }

    public void yandexLogoClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(yandexLogo))
                .click();
    }

    public void orderStatusButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(orderStatusButton))
                .click();
    }

    public void enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(orderNumberField))
                .sendKeys(orderNumber);
    }

    public void goButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(goButton))
                .click();
    }

    public void clickYandexLogoAndSwitchToIt() {
        String samokatWindow = driver.getWindowHandle();
        yandexLogoClick();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!samokatWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}