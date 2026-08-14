package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.driverfactory.WebDriverFactory;
import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

@RunWith(Parameterized.class)
public class SamokatOrderFirstPageErrorMessagesTest {

    private WebDriver driver;
    private final String expectedText;


    public SamokatOrderFirstPageErrorMessagesTest(String expectedText) {
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters(name = "Тест ошибки: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Введите корректное имя"},
                {"Введите корректную фамилию"},
                {"Введите корректный адрес"},
                {"Выберите станцию"},
                {"Введите корректный номер"},
        };
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.driverSelect(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkSamokatOrderFirstPageErrorMessages() {
        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickTopOrderButton();
        SamokatOrderFirstPage firstPage = new SamokatOrderFirstPage(driver);
        firstPage.enterAddress("1");
        firstPage.continueButtonClick();

        assertTrue("Сообщение об ошибке '" + expectedText + "' не отобразилось!", firstPage.isErrorMessageDisplayed(expectedText));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

/*Вариант без параметризации, экономящий время и ресурсы, но не дающий атомарность

package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.driverfactory.WebDriverFactory;
import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class SamokatOrderFirstPageErrorMessagesTest {

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
    public void checkOrderFirstPageErrorMessages() {
        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickTopOrderButton();
        SamokatOrderFirstPage firstPage = new SamokatOrderFirstPage(driver);
        firstPage.enterAddress("1");
        firstPage.continueButtonClick();

        assertTrue("Ошибка 'Введите корректное имя' не отобразилась!", firstPage.isErrorMessageDisplayed("Введите корректное имя"));
        assertTrue("Ошибка 'Введите корректную фамилию!' не отобразилась!", firstPage.isErrorMessageDisplayed("Введите корректную фамилию"));
        assertTrue("Ошибка 'Введите корректный адрес' не отобразилась!", firstPage.isErrorMessageDisplayed("Введите корректный адрес"));
        assertTrue("Ошибка 'Выберите станцию' не отобразилась!", firstPage.isErrorMessageDisplayed("Выберите станцию"));
        assertTrue("Ошибка 'Введите корректный номер' не отобразилась!", firstPage.isErrorMessageDisplayed("Введите корректный номер"));
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}

 */