package pages;

import config.UiConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.StepExecutor;

import java.util.function.Supplier;

public abstract class BasePage {

    protected static final String BASE_URL = UiConfig.getBaseUrl();

    protected final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, UiConfig.getTimeout());
    }

    private static void pageStep(String name, Runnable pageAction) {
        StepExecutor.step(name, pageAction);
    }

    private <T> T pageStep(String name, Supplier<T> call) {
        return StepExecutor.step(name, call);
    }

    protected void openUrl(String url, String action) {
        pageStep(action, () -> driver.get(url));
    }

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

    protected void clickElement(By element, String action) {
        pageStep(action, () -> wait.until(ExpectedConditions.elementToBeClickable(element)).click());
    }

    protected void clickElementWithJavaScript(By element, String action) {
        pageStep(action, () -> {
            var clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableElement);
        });
    }

    protected void submitForm(By element, String action) {
        pageStep(action, () -> wait.until(ExpectedConditions.presenceOfElementLocated(element)).submit());
    }

    protected String getText(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText());
    }

    protected boolean isDisplayed(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed());
    }

    protected boolean isAbsent(By element, String action) {
        return pageStep(action, () -> wait.until(ExpectedConditions.invisibilityOfElementLocated(element)));
    }

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
