package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SamokatOrderFirstPageErrorMessagesTestWithoutParam extends TestTemplate {

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

}