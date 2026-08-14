package ru.praktikum_services.qa_scooter.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum_services.qa_scooter.driverfactory.WebDriverFactory;
import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class SamokatAndYandexLogoRedirectTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.driverSelect(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkSamokatLogoRedirect() {
        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickTopOrderButton();
        SamokatOrderFirstPage firstPage = new SamokatOrderFirstPage(driver);
        firstPage.samokatLogoClick();
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        assertEquals("Клик по логотипу Самокат не возвращает на главную страницу!", expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void checkYandexLogoRedirect() {
        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        String samokatWindow = driver.getWindowHandle();
        mainPage.yandexLogoClick();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!samokatWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("dzen.ru"));

        assertTrue("Нет перехода на страницу Дзена!", driver.getCurrentUrl().contains("dzen.ru"));
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}
