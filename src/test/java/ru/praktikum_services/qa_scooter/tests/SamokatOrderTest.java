package ru.praktikum_services.qa_scooter.tests;

import ru.praktikum_services.qa_scooter.pageobject.SamokatMainPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderFirstPage;
import ru.praktikum_services.qa_scooter.pageobject.SamokatOrderSecondPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SamokatOrderTest extends TestTemplate {

    private final String name;
    private final String surname;
    private final String address;
    private final String stationName;
    private final String phoneNumber;
    private final String dateSelect;
    private final String period;
    private final String color;
    private final String comments;


    public SamokatOrderTest(String name, String surname, String address, String stationName, String phoneNumber,
                            String dateSelect, String period, String color, String comments) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationName = stationName;
        this.phoneNumber = phoneNumber;
        this.dateSelect = dateSelect;
        this.period = period;
        this.color = color;
        this.comments = comments;
    }

    @Parameterized.Parameters(name = "Тест заказа: {0} ({3})")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Абырвалг", "Шариков", "Пречистенка, 24", "Кропоткинская", "79998887766", "25.08.2026", "сутки", "черный жемчуг", "Желаю, чтобы все!"},
                {"Гомер", "Симпсон", "Вечнозелёная аллея, 742", "Медведково", "79876543210", "13.09.2026", "трое суток", "серая безысходность", "Попытка — первый шаг к провалу!"},
        };
    }

    private void runOrderFlow() {

        SamokatOrderFirstPage firstPage = new SamokatOrderFirstPage(driver);
        firstPage.enterName(name);
        firstPage.enterSurname(surname);
        firstPage.enterAddress(address);
        firstPage.selectMetroStation(stationName);
        firstPage.enterPhoneNumber(phoneNumber);
        firstPage.continueButtonClick();

        SamokatOrderSecondPage secondPage = new SamokatOrderSecondPage(driver);
        secondPage.enterDeliveryDate(dateSelect);
        secondPage.selectRentalPeriod(period);
        secondPage.selectSamokatColor(color);
        secondPage.enterComments(comments);
        secondPage.orderButtonClick();
        secondPage.yesButtonClick();

        assertTrue("Модальное окно подтверждения заказа не появляется!", secondPage.isOrderCreatedSuccessfully());
    }

    @Test
    public void checkOrderFlowFromTopButton() {

        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickTopOrderButton();

        runOrderFlow();
    }

    @Test
    public void checkOrderFlowFromBottomButton() {

        SamokatMainPage mainPage = new SamokatMainPage(driver);
        mainPage.cookieConfirmationButtonClick();
        mainPage.clickBottomOrderButton();

        runOrderFlow();
    }

}
