package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.driverfactory.WebDriverFactory;
import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatTrackPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class WrongOrderNumberStatusTest {

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
    public void checkWrongOrderNumberStatus() {
        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.orderStatusButtonClick();
        mainPage.enterOrderNumber("000000");
        mainPage.goButtonClick();
        SamokatTrackPage trackPage = new SamokatTrackPage(driver);

        assertTrue("Картинка-заглушка 'Такого заказа нет' не отобразилась!", trackPage.isImgNotFoundDisplayed());
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}
