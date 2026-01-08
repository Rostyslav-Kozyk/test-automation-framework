package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        driver = WebDriverFactory.createDriver(browser, headless);

        if (!headless) {
            driver.manage().window().maximize();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
