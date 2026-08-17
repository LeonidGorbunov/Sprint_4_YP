package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SamokatOrderFirstPageErrorMessagesTest extends TestTemplate {

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

}