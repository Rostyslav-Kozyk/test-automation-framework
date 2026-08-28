package tests;

import config.UiConfig;
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
        String browser = UiConfig.getBrowser();
        boolean headless = UiConfig.isHeadless();

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
