package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Locale;
import java.util.Optional;

/**
 * Creates configured web driver instances.
 */
public final class WebDriverFactory {

    /**
     * Prevents instantiation of this utility class.
     */
    private WebDriverFactory() {
    }

    /**
     * Creates driver.
     *
     * @param browser   the browser value
     * @param headless  the headless value
     * @param remoteUrl the remote url value
     * @return the operation result
     */
    public static WebDriver createDriver(
            String browser,
            boolean headless,
            Optional<String> remoteUrl
    ) {
        String normalizedBrowser = browser.toLowerCase(Locale.ROOT);
        Capabilities options = createOptions(normalizedBrowser, headless);

        if (remoteUrl.isPresent()) {
            return createRemoteDriver(remoteUrl.get(), options);
        }

        return createLocalDriver(normalizedBrowser, options);
    }

    /**
     * Creates options.
     *
     * @param browser  the browser value
     * @param headless the headless value
     * @return the operation result
     */
    private static Capabilities createOptions(String browser, boolean headless) {
        return switch (browser) {
            case "chrome" -> createChromeOptions(headless);
            case "firefox" -> createFirefoxOptions(headless);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser '" + browser + "'. Supported values: chrome, firefox"
            );
        };
    }

    /**
     * Creates chrome options.
     *
     * @param headless the headless value
     * @return the operation result
     */
    private static ChromeOptions createChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /**
     * Creates firefox options.
     *
     * @param headless the headless value
     * @return the operation result
     */
    private static FirefoxOptions createFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return options;
    }

    /**
     * Creates local driver.
     *
     * @param browser the browser value
     * @param options the options value
     * @return the operation result
     */
    private static WebDriver createLocalDriver(String browser, Capabilities options) {
        if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver((FirefoxOptions) options);
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver((ChromeOptions) options);
    }

    /**
     * Creates remote driver.
     *
     * @param remoteUrl the remote url value
     * @param options   the options value
     * @return the operation result
     */
    private static WebDriver createRemoteDriver(String remoteUrl, Capabilities options) {
        try {
            return new RemoteWebDriver(URI.create(remoteUrl).toURL(), options);
        } catch (IllegalArgumentException | MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid Selenium remote URL: " + remoteUrl, exception);
        }
    }
}
