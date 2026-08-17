package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatTrackPage;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class WrongOrderNumberStatusTest extends TestTemplate {

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

}
