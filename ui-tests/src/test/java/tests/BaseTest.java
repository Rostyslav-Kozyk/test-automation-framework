package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    protected static final String VALID_USERNAME = "standard_user";
    protected static final String VALID_PASSWORD = "secret_sauce";

    @BeforeClass(alwaysRun = true)
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

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
