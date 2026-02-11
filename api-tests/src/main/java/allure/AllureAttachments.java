package allure;

import io.qameta.allure.Allure;

public class AllureAttachments {

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }
}
