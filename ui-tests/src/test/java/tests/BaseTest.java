package tests;

import config.UiConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;
import utils.WebDriverFactory;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String browser = UiConfig.getBrowser();
        boolean headless = UiConfig.isHeadless();

        DriverManager.setDriver(WebDriverFactory.createDriver(
                browser,
                headless,
                UiConfig.getRemoteUrl()
        ));

        if (!headless) {
            getDriver().manage().window().maximize();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
