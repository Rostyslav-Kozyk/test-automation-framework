package pages;

import config.UiConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.StepExecutor;

import java.util.function.Supplier;

/**
 * Models the base page and its available interactions.
 */
public abstract class BasePage {

    protected static final String BASE_URL = UiConfig.getBaseUrl();

    protected final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Creates a new {@code BasePage} instance.
     *
     * @param driver the driver value
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, UiConfig.getTimeout());
    }

    /**
     * Executes the page step operation.
     *
     * @param name       the name value
     * @param pageAction the page action value
     */
    private static void pageStep(String name, Runnable pageAction) {
        StepExecutor.step(name, pageAction);
    }

    /**
     * Executes the page step operation.
     *
     * @param name the name value
     * @param call the call value
     * @return the operation result
     */
    private <T> T pageStep(String name, Supplier<T> call) {
        return StepExecutor.step(name, call);
    }

    /**
     * Opens url.
     *
     * @param url    the url value
     * @param action the action value
     */
    protected void openUrl(String url, String action) {
        pageStep(action, () -> driver.get(url));
    }

    /**
     * Fills in field with provided text.
     *
     * @param element the element value
     * @param value   the text value
     * @param action  the action value
     */
    protected void fillInField(By element, String value, String action) {
        pageStep(action, () -> {
            var input = wait.until(ExpectedConditions.elementToBeClickable(element));
            input.clear();
            input.sendKeys(value);

            if (!value.equals(input.getDomProperty("value"))) {
                ((JavascriptExecutor) driver).executeScript("""
                        const input = arguments[0];
                        const value = arguments[1];
                        const valueSetter = Object.getOwnPropertyDescriptor(
                            HTMLInputElement.prototype,
                            'value'
                        ).set;
                        valueSetter.call(input, value);
                        input.dispatchEvent(new InputEvent('input', {
                            bubbles: true,
                            inputType: 'insertText',
                            data: value
                        }));
                        input.dispatchEvent(new Event('change', { bubbles: true }));
                        """, input, value);
            }

            wait.withMessage(() -> String.format(
                            "Expected field %s to contain '%s', but its current value is '%s'",
                            element,
                            value,
                            driver.findElement(element).getDomProperty("value")
                    ))
                    .until(ExpectedConditions.textToBePresentInElementValue(element, value));
        });
    }

    /**
     * Clicks element.
     *
     * @param element the element value
     * @param action  the action value
     */
    protected void clickElement(By element, String action) {
        pageStep(action, () -> wait.until(ExpectedConditions.elementToBeClickable(element)).click());
    }

    /**
     * Clicks element with JavaScript.
     *
     * @param element the element value
     * @param action  the action value
     */
    protected void clickElementWithJavaScript(By element, String action) {
        pageStep(action, () -> {
            var clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableElement);
        });
    }

    /**
     * Submits form.
     *
     * @param element the element value
     * @param action  the action value
     */
    protected void submitForm(By element, String action) {
        pageStep(action, () -> wait.until(ExpectedConditions.presenceOfElementLocated(element)).submit());
    }

    /**
     * Returns text from Web element.
     *
     * @param element the element value
     * @param action  the action value
     * @return the text
     */
    protected String getText(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText());
    }

    /**
     * Checks whether Web element is displayed.
     *
     * @param element the element value
     * @param action  the action value
     * @return the boolean value
     */
    protected boolean isDisplayed(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed());
    }

    /**
     * Checks whether Web element is absent.
     *
     * @param element the element value
     * @param action  the action value
     * @return the boolean value
     */
    protected boolean isAbsent(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.invisibilityOfElementLocated(element)));
    }

    /**
     * Waits until URL contains expected part.
     *
     * @param urlPart the url part value
     * @param action  the action value
     */
    protected void waitForUrlContaining(String urlPart, String action) {
        pageStep(action, () -> wait
                .withMessage(() -> String.format(
                        "Expected URL to contain '%s', but current URL is '%s'",
                        urlPart,
                        driver.getCurrentUrl()
                ))
                .until(ExpectedConditions.urlContains(urlPart)));
    }
}
