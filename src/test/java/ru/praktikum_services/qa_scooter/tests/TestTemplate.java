package ru.praktikum_services.qa_scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.praktikum_services.qa_scooter.driverfactory.WebDriverFactory;
import java.time.Duration;

public class TestTemplate {

    public static final String SAMOKAT_URL = "https://qa-scooter.praktikum-services.ru/";
    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.driverSelect(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
        driver.get(SAMOKAT_URL);
    }

    @After
    public void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }
}
