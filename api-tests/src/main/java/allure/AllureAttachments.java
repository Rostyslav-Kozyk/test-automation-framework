package allure;

import io.qameta.allure.Allure;

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
     * Attaches json.
     *
     * @param name the name value
     * @param json the json value
     */
    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }
}
