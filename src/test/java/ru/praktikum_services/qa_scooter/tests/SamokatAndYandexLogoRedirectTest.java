package ru.praktikum_services.qa_scooter.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class SamokatAndYandexLogoRedirectTest extends TestTemplate {

    @Test
    public void checkSamokatLogoRedirect() {

        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickTopOrderButton();

        SamokatOrderFirstPage firstPage = new SamokatOrderFirstPage(driver);
        firstPage.samokatLogoClick();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlToBe(TestTemplate.SAMOKAT_URL));

        assertEquals("Клик по логотипу Самокат не возвращает на главную страницу!", TestTemplate.SAMOKAT_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkYandexLogoRedirect() {

        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickYandexLogoAndSwitchToIt();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("dzen.ru"));

        assertTrue("Нет перехода на страницу Дзена!", driver.getCurrentUrl().contains("dzen.ru"));
    }

}
