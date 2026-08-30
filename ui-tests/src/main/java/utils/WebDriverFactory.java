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

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

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

    private static Capabilities createOptions(String browser, boolean headless) {
        return switch (browser) {
            case "chrome" -> createChromeOptions(headless);
            case "firefox" -> createFirefoxOptions(headless);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser '" + browser + "'. Supported values: chrome, firefox"
            );
        };
    }

    private static ChromeOptions createChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    private static FirefoxOptions createFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return options;
    }

    private static WebDriver createLocalDriver(String browser, Capabilities options) {
        if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver((FirefoxOptions) options);
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver((ChromeOptions) options);
    }

    private static WebDriver createRemoteDriver(String remoteUrl, Capabilities options) {
        try {
            return new RemoteWebDriver(URI.create(remoteUrl).toURL(), options);
        } catch (IllegalArgumentException | MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid Selenium remote URL: " + remoteUrl, exception);
        }
    }
}
