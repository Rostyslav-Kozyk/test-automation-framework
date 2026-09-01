package utils;

import org.openqa.selenium.WebDriver;

/**
 * Provides driver manager functionality.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /**
     * Prevents instantiation of this utility class.
     */
    private DriverManager() {
    }

    /**
     * Sets driver.
     *
     * @param driver the driver value
     */
    public static void setDriver(WebDriver driver) {
        if (DRIVER.get() != null) {
            throw new IllegalStateException("WebDriver is already initialized for this thread");
        }
        DRIVER.set(driver);
    }

    /**
     * Returns driver.
     *
     * @return the driver
     */
    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    /**
     * Quits driver.
     */
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
