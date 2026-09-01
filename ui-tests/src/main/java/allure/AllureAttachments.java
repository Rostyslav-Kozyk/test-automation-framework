package allure;

import io.qameta.allure.Allure;

import java.io.InputStream;

/**
 * Provides allure attachments functionality.
 */
public class AllureAttachments {

    /**
     * Attaches text.
     *
     * @param name    the name value
     * @param content the content value
     */
    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    /**
     * Attaches page.
     *
     * @param name    the name value
     * @param content the content value
     */
    public static void attachPage(String name, String content) {
        Allure.addAttachment(name, "text/html", content, ".html");
    }

    /**
     * Attaches image.
     *
     * @param name    the name value
     * @param content the content value
     */
    public static void attachImage(String name, InputStream content) {
        Allure.addAttachment(name, "image/png", content, ".png");
    }
}
