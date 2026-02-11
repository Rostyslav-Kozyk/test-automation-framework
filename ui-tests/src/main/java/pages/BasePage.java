package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.StepExecutor;

import java.util.function.Supplier;

public abstract class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
        pageStep(action, () -> driver.findElement(element).sendKeys(value));
    }

    protected void clickElement(By element, String action) {
        pageStep(action, () -> driver.findElement(element).click());
    }

    protected String getText(By element, String action) {
        return pageStep(action, () -> driver.findElement(element).getText());
    }

    protected boolean isDisplayed(By element, String action) {
        return pageStep(action, () -> driver.findElement(element).isDisplayed());
    }
}
