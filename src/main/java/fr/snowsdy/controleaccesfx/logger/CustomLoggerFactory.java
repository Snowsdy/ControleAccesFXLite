package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.*;

public class CustomLoggerFactory {
    private static CustomInfoLogger infoLogger;
    private static CustomWarningLogger warningLogger;
    private static CustomDangerLogger dangerLogger;

    private CustomLoggerFactory() {
    }

    public static AbstractCustomLogger getInfoLogger(String message) {
        if (infoLogger == null) {
            infoLogger = (CustomInfoLogger) CustomLoggerSingleton.getInstance(LOG_INFO, message);
        }
        return infoLogger;
    }

    public static AbstractCustomLogger getWarningLogger(String message) {
        if (warningLogger == null) {
            warningLogger = (CustomWarningLogger) CustomLoggerSingleton.getInstance(LOG_WARNING, message);
        }
        return warningLogger;
    }

    public static AbstractCustomLogger getDangerLogger(String message) {
        if (dangerLogger == null) {
            dangerLogger = (CustomDangerLogger) CustomLoggerSingleton.getInstance(LOG_DANGER, message);
        }
        return dangerLogger;
    }
}
